package dungeonforge.factory;

import dungeonforge.core.Monster;
import dungeonforge.items.Chest;

import java.util.List;

public class TreasureRoomPopulator extends RoomPopulator {

    public TreasureRoomPopulator(ThemeKit theme) {
        super(theme);
    }

    @Override
    protected List<Monster> createEncounter(int depth) {
        // Exactly one guard protecting the treasure
        MonsterDef guardDef = theme.createMonster(depth);
        return List.of(new Monster(guardDef));
    }

    @Override
    protected Chest createChest(int depth) {
        Chest chest = new Chest("Treasure Chest");
        chest.add(theme.createLoot(depth));
        chest.add(theme.createLoot(depth));
        return chest;
    }
}