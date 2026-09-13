package dungeonforge;

import dungeonforge.config.GameConfig;
import dungeonforge.config.RandomSource;
import dungeonforge.core.DungeonLevel;
import dungeonforge.core.GameWorld;
import dungeonforge.core.Monster;
import dungeonforge.core.Player;
import dungeonforge.core.Room;

public final class Main {

    public static final String VERSION = "0.2.0";

    private Main() { }

    public static String banner() {
        return """
                =========================================
                        D U N G E O N F O R G E
                  A Head First Design Patterns project
                =========================================""";
    }

    public static void main(String[] args) {
        String playerName = "Delver";

        // Parse arguments safely
        for (String arg : args) {
            if (arg.startsWith("--seed=")) {
                try {
                    long seed = Long.parseLong(arg.substring("--seed=".length()));
                    RandomSource.getInstance().reseed(seed);
                } catch (NumberFormatException ignored) { }
            } else if (!arg.isBlank() && !arg.startsWith("-")) {
                playerName = arg;
            }
        }

        Player player = new Player(playerName);
        GameWorld world = new GameWorld(player);

        System.out.println(player.describe());
        System.out.println();

        for (DungeonLevel level : world.getLevels()) {
            System.out.println("-- Level " + level.getDepth() + " --");
            for (Room room : level.getRooms()) {
                StringBuilder line = new StringBuilder("  " + room.getId() + ": ");
                if (room.getMonsters().isEmpty()) {
                    line.append("(empty)");
                } else {
                    for (Monster m : room.getMonsters()) {
                        line.append(m.describe()).append("  ");
                    }
                }
                System.out.println(line.toString().trim());
            }
        }
        System.out.println();
        System.out.println("Total monsters: " + world.totalMonsters());
    }
}