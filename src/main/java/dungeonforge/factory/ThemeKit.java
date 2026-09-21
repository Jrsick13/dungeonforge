package dungeonforge.factory;

import dungeonforge.items.Item;

public interface ThemeKit {
    String themeName();
    MonsterDef createMonster(int depth);
    MonsterDef createBoss(int depth);
    Item createLoot(int depth); // Change Object to Item if your Item class is imported
    String createRoomFlavor();
}