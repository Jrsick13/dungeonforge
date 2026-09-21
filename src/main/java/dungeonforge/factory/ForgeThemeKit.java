package dungeonforge.factory;

import dungeonforge.items.Item;

public class ForgeThemeKit implements ThemeKit {
    @Override
    public String themeName() {
        return "Forge";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("forge_minion", "Magma Imp", 25, 7, 15, "Forge", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("forge_boss", "Forge Overlord", 60, 15, 40, "Forge", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "Radiant heat fills the room, glowing from rivers of molten slag.";
    }
}