package dungeonforge.core;

import dungeonforge.config.GameConfig;
import dungeonforge.config.RandomSource;

import java.util.ArrayList;
import java.util.List;

/**
 * WEEK 1 -- the world.
 *
 * Refactored (US-1.1 & US-1.2): Tunable dungeon parameters pull from GameConfig.
 * Randomness is routed through the central RandomSource Singleton.
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
        int maxMonstersPerRoom = GameConfig.getInstance().getInt("maxMonstersPerRoom", 2);

        for (int d = 1; d <= dungeonDepth; d++) {
            DungeonLevel level = new DungeonLevel(d);
            for (int r = 0; r < roomsPerLevel; r++) {
                Room room = new Room("L" + d + "R" + r); // 1. Calls RandomSource for room flavor
                int count = RandomSource.getInstance().nextInt(maxMonstersPerRoom + 1); // 2. Calls RandomSource for monster count
                for (int m = 0; m < count; m++) {
                    room.addMonster(spawn(d)); // 3. Calls RandomSource for species selection + 2 stat variance calls in Monster
                }
                level.addRoom(room);
            }
            levels.add(level);
        }
    }

    private Monster spawn(int depth) {
        String[] species = {"Skeleton", "Crypt Rat", "Wight", "Bone Priest"};
        String pick = species[RandomSource.getInstance().nextInt(species.length)];
        return new Monster(pick, 12 + depth * 4, 4 + depth, 6 + depth * 3);
    }

    public Player getPlayer()             { return player; }
    public List<DungeonLevel> getLevels() { return levels; }

    public int totalMonsters() {
        int n = 0;
        for (DungeonLevel l : levels) {
            for (Room r : l.getRooms()) n += r.getMonsters().size();
        }
        return n;
    }
}