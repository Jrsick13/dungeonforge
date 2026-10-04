package dungeonforge.core;

import dungeonforge.behavior.Action;
import dungeonforge.behavior.SkittishStrategy;
import dungeonforge.config.GameConfig;
import dungeonforge.events.EventBus;
import dungeonforge.events.EventType;
import dungeonforge.events.GameEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * WEEK 5 -- a minimal encounter resolver. New this week so that monsters have something to DO.
 */
public class Combat {

    private final EventBus bus;

    public Combat(EventBus bus) {
        this.bus = bus;
    }

    private static final int MAX_ROUNDS = 40;

    /** Returns true if the player survived the encounter. */
    public boolean fight(Player player, Room room, int depth) {
        if (!hasLiving(room)) return true;

        System.out.println("    ! " + room.getMonsters().size() + " hostile(s)");

        int round = 0;
        while (player.isAlive() && hasLiving(room) && round++ < MAX_ROUNDS) {
            playerActs(player, room);
            if (!hasLiving(room)) break;

            // --- monsters' turn ---
            for (Monster m : livingMonsters(room)) {
                checkForTacticsChange(m);
                monsterActs(m, player, room);
                if (!player.isAlive()) break;
            }
        }

        if (!player.isAlive()) {
            bus.publish(GameEvent.of(EventType.PLAYER_DIED));
            System.out.println("Game Over - Player Died");
            return false;
        }
        bus.publish(GameEvent.of(EventType.ROOM_CLEARED));
        System.out.println("Player survived - Room Cleared");
        return true;
    }

    private void playerActs(Player player, Room room) {
        // --- player's turn: hit the first thing still standing ---
        Monster target = firstLiving(room);
        if (target == null) return;

        int damage = player.getAttackPower();
        target.takeDamage(damage);
        bus.publish(GameEvent.of(EventType.DAMAGE_DEALT,
                "target", target.getName(),
                "amount", damage
        ));
        System.out.println("      you hit " + target.getName() + " for " + damage);
        if (!target.isAlive()) {
            player.addXp(target.getXpReward());
            player.addGold(target.getXpReward() * 2);
            bus.publish(GameEvent.of(EventType.MONSTER_DIED,
                    "name", target.getName(), "xp", target.getXpReward()));
            bus.publish(GameEvent.of(EventType.XP_GAINED, "amount", target.getName(), target.getXpReward()));
            bus.publish(GameEvent.of(EventType.GOLD_GAINED, "amount", target.getName(), target.getXpReward()));
            System.out.println("      " + target.getName() + " dies");
        }
    }

    private void checkForTacticsChange(Monster m) {
        double threshold = GameConfig.getInstance().getDouble("fleeThreshold");
        if (m.hpFraction() >= threshold) return;
        if (m.getStrategy() instanceof SkittishStrategy) return;

        String from = m.getStrategy().name();
        m.setStrategy(new SkittishStrategy());
        bus.publish(GameEvent.of(EventType.STRATEGY_CHANGED,
                "name", m.getName(), "from", from, "to", m.getStrategy().name()));
        System.out.println(m.getName() + " changed strategy from " + from + " to " + m.getStrategy().name());
    }

    private void monsterActs(Monster m, Player player, Room room) {
        if (m.getStrategy() == null) return;
        Action action = m.getStrategy().chooseAction(m, player, room);
        if (action == null) return;

        switch (action.getType()) {
            case ATTACK -> {
                int dmg = m.getAttackPower();
                player.takeDamage(dmg);
                bus.publish(GameEvent.of(EventType.DAMAGE_TAKEN,
                        "source", m.getName(), "amount", dmg, "flavor", action.getFlavor()));
            }
            case RANGED_ATTACK -> {
                int dmg = Math.max(1, (int) Math.round(m.getAttackPower() * 0.8));
                player.takeDamage(dmg);
                bus.publish(GameEvent.of(EventType.DAMAGE_TAKEN,
                        "source", m.getName(), "amount", dmg, "flavor", action.getFlavor()));
            }
            case FLEE -> {
                room.getMonsters().remove(m);
                bus.publish(GameEvent.of(EventType.MONSTER_FLED, "name", m.getName()));
            }
            case HEAL_ALLY -> {
                if (action.getTarget() != null) {
                    action.getTarget().heal(5);
                    bus.publish(GameEvent.of(EventType.MONSTER_HEALED,
                            "healer", m.getName(), "target", action.getTarget().getName()));
                }
            }
            case WAIT -> bus.publish(GameEvent.message(action.getFlavor()));
        }
    }

    private boolean hasLiving(Room room) { return firstLiving(room) != null; }

    private Monster firstLiving(Room room) {
        for (Monster m : room.getMonsters()) if (m.isAlive()) return m;
        return null;
    }

    private List<Monster> livingMonsters(Room room) {
        List<Monster> out = new ArrayList<>();
        for (Monster m : room.getMonsters()) if (m.isAlive()) out.add(m);
        return out;
    }

    /** Between rooms the player catches their breath. Tunable, so it lives in config. */
    public static void restAfterRoom(Player player) {
        if (!player.isAlive()) return;
        player.heal(GameConfig.getInstance().getInt("restHealPerRoom"));
    }
}