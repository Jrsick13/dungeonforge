package dungeonforge.factory;

import dungeonforge.items.Item;

public class FrostThemeKit implements ThemeKit {
    private final MonsterFactory monsterFactory;

    public FrostThemeKit(MonsterFactory monsterFactory) {
        this.monsterFactory = monsterFactory;
    }

    @Override
    public MonsterFactory getMonsterFactory() {
        return monsterFactory;
    }

    @Override
    public String themeName() {
        return "Frost";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("frost_minion", "Ice Wraith", 20, 6, 12, "Frost", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("frost_boss", "Frost Sovereign", 50, 14, 35, "Frost", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "Bitter cold bites at the air, with frost coating the jagged stone walls.";
    }
}