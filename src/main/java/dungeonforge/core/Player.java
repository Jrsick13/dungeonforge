package dungeonforge.core;

import dungeonforge.config.GameConfig;

/**
 * WEEK 1 -- the player.
 *
 * Refactored (US-1.1): Tunable values are pulled dynamically from GameConfig
 * instead of being hardcoded literals.
 */
public class Player extends Entity {

    private int gold;
    private int xp;

    public Player(String name) {
        // Read starting HP, attack, and defense dynamically from GameConfig
        super(
                name,
                GameConfig.getInstance().getInt("playerStartingHp", 60),
                GameConfig.getInstance().getInt("playerStartingAtk", 8),
                GameConfig.getInstance().getInt("playerStartingDef", 2)
        );
    }

    public int getGold()          { return gold; }
    public int getXp()            { return xp; }
    public void addGold(int g)    { gold += g; }
    public void addXp(int x)      { xp += x; }

    /** Backpack capacity in kilograms pulled from GameConfig. */
    public double carryCapacity() {
        return GameConfig.getInstance().getInt("playerCarryCapacity", 60);
    }

    @Override
    public String describe() {
        return name + "  HP " + hp + "/" + maxHp
                + "  ATK " + attackPower + "  DEF " + defense
                + "  Gold " + gold + "  XP " + xp
                + "  Carry " + carryCapacity() + "kg";
    }
}