package dungeonforge.config;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class GameConfig {

    private static GameConfig instance;
    private final Map<String, Object> settings = new HashMap<>();

    private GameConfig() {
        // Fallback default values
        settings.put("playerStartingHp", 60.0);
        settings.put("playerStartingAttack", 8.0);
        settings.put("playerStartingDefense", 2.0);
        settings.put("carryCapacity", 60.0);
        settings.put("dungeonDepth", 3.0);
        settings.put("roomsPerLevel", 8.0);
        settings.put("maxMonstersPerRoom", 2.0);
        settings.put("seed", 20240409.0);

        // Load config from resources
        try (InputStream in = GameConfig.class.getResourceAsStream("/data/config.json")) {
            if (in != null) {
                String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                settings.putAll(Json.parseObject(text));
            }
        } catch (Exception e) {
            // Defaults stand if file reading fails
        }
    }

    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    public int getInt(String key, int defaultValue) {
        Object val = settings.get(key);
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        return defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        Object val = settings.get(key);
        if (val instanceof Number) {
            return ((Number) val).longValue();
        }
        return defaultValue;
    }

    /** Added to support MonsterFactory resource loading */
    public static String readResource(String resourceName) {
        String path = resourceName.startsWith("/") ? resourceName : "/data/" + resourceName;
        try (InputStream in = GameConfig.class.getResourceAsStream(path)) {
            if (in != null) {
                return new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception ignored) {}

        try (InputStream in = GameConfig.class.getResourceAsStream(resourceName.startsWith("/") ? resourceName : "/" + resourceName)) {
            if (in != null) {
                return new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception ignored) {}

        return null;
    }

    /** Reset method for isolated testing (US-1.3) */
    public static synchronized void resetForTests() {
        instance = null;
    }
}