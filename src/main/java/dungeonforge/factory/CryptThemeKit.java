package dungeonforge.factory;

import dungeonforge.items.Item;

public class CryptThemeKit implements ThemeKit {
    @Override
    public String themeName() {
        return "Crypt";
    }

    @Override
    public MonsterDef createMonster(int depth) {
        return new MonsterDef("crypt_minion", "Crypt Ghoul", 20, 5, 10, "Crypt", false);
    }

    @Override
    public MonsterDef createBoss(int depth) {
        return new MonsterDef("crypt_boss", "Bone Tyrant", 50, 12, 30, "Crypt", true);
    }

    @Override
    public Item createLoot(int depth) {
        return null;
    }

    @Override
    public String createRoomFlavor() {
        return "The air is cold and smells of ancient dust and decay.";
    }
}