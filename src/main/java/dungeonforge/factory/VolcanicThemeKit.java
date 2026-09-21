package dungeonforge.factory;

import dungeonforge.items.Item;

public class VolcanicThemeKit implements ThemeKit {

    @Override
    public String themeName() {
        return "Volcanic Depths";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("volcanic_minion", "Magma Imp", 25 + (depth * 2), 8, 12, "Volcanic", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("volcanic_boss", "Molten Drake", 70 + (depth * 5), 18, 35, "Volcanic", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "Rivers of bubbling lava cast a dim, fiery orange glow across the scorched stone walls.";
    }
}