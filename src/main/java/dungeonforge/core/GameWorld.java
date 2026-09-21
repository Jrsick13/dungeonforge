package dungeonforge.core;

import dungeonforge.config.GameConfig;
import dungeonforge.factory.ThemeKit;
import dungeonforge.factory.ThemeRegistry;
import dungeonforge.factory.BossRoomPopulator;
import dungeonforge.factory.RoomPopulator;
import dungeonforge.factory.StandardRoomPopulator;
import dungeonforge.factory.TreasureRoomPopulator;

import java.util.ArrayList;
import java.util.List;

/**
 * WEEK 1 & 2 -- the world.
 * Refactored to use RoomPopulator factory methods for room generation.
 */

public class GameWorld {

    private final Player player;
    private final List<DungeonLevel> levels = new ArrayList<>();

    public GameWorld(Player player) {
        this.player = player;
        generate();
    }

    private void generate() {
        int dungeonDepth = GameConfig.getInstance().getInt("dungeonDepth", 3);
        int roomsPerLevel = GameConfig.getInstance().getInt("roomsPerLevel", 8);

        for (int d = 1; d <= dungeonDepth; d++) {
            // 1. Get the theme kit for this depth from the registry
            ThemeKit kit = ThemeRegistry.getKitForLevel(d);
            DungeonLevel level = new DungeonLevel(d, kit);

            for (int r = 0; r < roomsPerLevel; r++) {
                // 2. Instantiate the Room
                Room room = new Room("L" + d + "R" + r, kit);

                // 3. Select the correct RoomPopulator based on room position/type
                RoomPopulator populator;
                if (d == dungeonDepth && r == roomsPerLevel - 1) {
                    // The final room of the deepest level is the Boss room
                    populator = new BossRoomPopulator(kit);
                } else if (r == roomsPerLevel / 2) {
                    // Designate a middle room as the Treasure room
                    populator = new TreasureRoomPopulator(kit);
                } else {
                    // Everything else is a Standard room
                    populator = new StandardRoomPopulator(kit);
                }

                // 4. Delegate population to the Template Method pattern
                populator.populate(room, d);

                level.addRoom(room);
            }
            levels.add(level);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<DungeonLevel> getLevels() {
        return levels;
    }

    public int totalMonsters() {
        int n = 0;
        for (DungeonLevel l : levels) {
            for (Room r : l.getRooms()) {
                n += r.getMonsters().size();
            }
        }
        return n;
    }
}