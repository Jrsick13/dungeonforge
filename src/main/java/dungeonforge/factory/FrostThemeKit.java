package dungeonforge.factory;

import dungeonforge.items.Item;

public class FrostThemeKit implements ThemeKit {
    @Override
    public String themeName() {
        return "Frost";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("frost_minion", "Ice Wraith", 22, 6, 12, "Frost", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("frost_boss", "Glacial Wyrm", 55, 14, 35, "Frost", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "Sharp ice crystals line the walls, biting into the freezing air.";
    }
}