package dungeonforge.core;

import java.util.ArrayList;
import java.util.List;
import dungeonforge.factory.ThemeKit;
import dungeonforge.items.Chest;
import dungeonforge.items.Item;

/**
 * WEEK 1 -- one room of the dungeon.
 * WEEK 3 (US-1.2) -- flavour text is picked from the one seeded source.
 */

public class Room {

    private final String id;
    private final ThemeKit themeKit;
    private String flavor;
    private final List<Monster> monsters = new ArrayList<>(); // Stores live runtime Monsters
    private final List<Item> floorItems = new ArrayList<>();
    private Chest chest;

    public Room(String id, ThemeKit themeKit) {
        this.id = id;
        this.themeKit = themeKit;
        this.flavor = themeKit != null ? themeKit.createRoomFlavor() : "A standard, empty room.";
    }

    public String getId()               { return id; }
    public ThemeKit getThemeKit()       { return themeKit; }
    public String getFlavor()           { return flavor; }
    public void setFlavor(String f)     { this.flavor = f; }
    public List<Monster> getMonsters()  { return monsters; }

    // Make sure this accepts the live Monster class:
    public void addMonster(Monster m)   { monsters.add(m); }

    public List<Item> getFloorItems()   { return floorItems; }
    public void addItem(Item i)         { floorItems.add(i); }
    public Chest getChest()             { return chest; }
    public void setChest(Chest c)       { this.chest = c; }
    public boolean hasChest()           { return chest != null && !chest.isEmpty(); }
}