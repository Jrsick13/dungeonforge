package dungeonforge.factory;

import dungeonforge.core.Monster;
import dungeonforge.items.Chest; // Make sure this matches your project's chest import
import java.util.ArrayList;
import java.util.List;

public class BossRoomPopulator extends RoomPopulator {

    public BossRoomPopulator(ThemeKit theme) {
        super(theme);
    }

    @Override
    public String kind() {
        return "boss";
    }

    @Override
    protected List<Monster> createEncounter(int depth) {
        List<Monster> encounter = new ArrayList<>();

        // Add the boss
        MonsterDef bossDef = theme.createBoss(depth);
        encounter.add(theme.getMonsterFactory().create(bossDef.getId(), depth));

        // Add an escort/minion
        MonsterDef escortDef = theme.createMonster(depth);
        encounter.add(theme.getMonsterFactory().create(escortDef.getId(), depth));

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