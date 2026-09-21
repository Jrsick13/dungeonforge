package dungeonforge.factory;

import dungeonforge.core.Monster;
import dungeonforge.items.Chest;

import java.util.ArrayList;
import java.util.List;

public class BossRoomPopulator extends RoomPopulator {

    public BossRoomPopulator(ThemeKit theme) {
        super(theme);
    }

    @Override
    protected List<Monster> createEncounter(int depth) {
        List<Monster> encounter = new ArrayList<>();

        // Add the boss
        MonsterDef bossDef = theme.createBoss(depth);
        encounter.add(new Monster(bossDef));

        // Add an escort/minion
        MonsterDef escortDef = theme.createMonster(depth);
        encounter.add(new Monster(escortDef));

        return encounter;
    }

    @Override
    protected Chest createChest(int depth) {
        Chest chest = new Chest("Treasure Chest");
        chest.add(theme.createLoot(depth));
        chest.add(theme.createLoot(depth));
        return chest;
    }
}