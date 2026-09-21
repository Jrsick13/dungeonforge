package dungeonforge.factory;

import dungeonforge.config.GameConfig;
import dungeonforge.config.Json;
import dungeonforge.core.Monster;
import dungeonforge.config.RandomSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MonsterFactory {
    private final Map<String, MonsterDef> blueprints = new LinkedHashMap<>();

    public MonsterFactory() {
        loadFrom("monsters.json");
    }

    private void loadFrom(String resourceName) {
        String text = GameConfig.readResource(resourceName);
        if (text == null) {
            System.err.println("[Factory] " + resourceName + " not found: no monsters registered");
            return;
        }
        Map<String, Object> root = Json.parseObject(text);
        for (Map.Entry<String, Object> e : root.entrySet()) {
            if (!(e.getValue() instanceof Map)) continue;

            Map<String, Object> m = (Map<String, Object>) e.getValue();

            register(e.getKey(), new MonsterDef(
                    e.getKey(),
                    str(m.get("name"), e.getKey()),
                    num(m.get("hp"), 10),
                    num(m.get("attack"), 1),
                    num(m.get("xp"), 5),
                    str(m.get("theme"), "dungeon"),
                    Boolean.TRUE.equals(m.get("boss"))
            ));
        }
    }

    public void register(String id, MonsterDef def) {
        blueprints.put(id, def);
    }

    public Monster create(String id, int depth) {
        MonsterDef def = blueprints.get(id);
        if (def == null) {
            def = new MonsterDef("fallback", "Glitch Goblin", 25, 4, 10, "unknown", false);
        }

        int hpVariance = RandomSource.getInstance().nextInt(5) - 2;
        int atkVariance = RandomSource.getInstance().nextInt(3) - 1;

        int scaledHp = def.getHp() + (depth - 1) * 5 + hpVariance;
        int scaledAtk = def.getAttack() + (depth - 1) * 2 + atkVariance;

        return new Monster(def.getName(), scaledHp, scaledAtk, def.getXp());
    }

    public List<String> idsForTheme(String theme) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, MonsterDef> entry : blueprints.entrySet()) {
            if (entry.getValue().getTheme().equalsIgnoreCase(theme) && !entry.getValue().isBoss()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public String bossIdForTheme(String theme) {
        for (Map.Entry<String, MonsterDef> entry : blueprints.entrySet()) {
            if (entry.getValue().getTheme().equalsIgnoreCase(theme) && entry.getValue().isBoss()) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static String str(Object o, String fallback) {
        return o instanceof String ? (String) o : fallback;
    }

    private static int num(Object o, int fallback) {
        return o instanceof Number ? ((Number) o).intValue() : fallback;
    }
}