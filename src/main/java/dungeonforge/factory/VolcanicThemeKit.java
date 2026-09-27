package dungeonforge.factory;

import dungeonforge.items.Item;

public class VolcanicThemeKit implements ThemeKit {
    private final MonsterFactory monsterFactory;

    public VolcanicThemeKit(MonsterFactory monsterFactory) {
        this.monsterFactory = monsterFactory;
    }

    @Override
    public MonsterFactory getMonsterFactory() {
        return monsterFactory;
    }

    @Override
    public String themeName() {
        return "Volcanic";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("volcanic_minion", "Magma Crawler", 30, 8, 18, "Volcanic", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("volcanic_boss", "Magma Tyrant", 70, 16, 45, "Volcanic", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "The ground trembles with the heartbeat of an underground inferno.";
    }
}