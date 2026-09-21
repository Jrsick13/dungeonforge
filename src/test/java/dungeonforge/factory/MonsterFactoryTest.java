package dungeonforge.factory;

import dungeonforge.factory.MonsterFactory;
import dungeonforge.core.Monster;
import dungeonforge.config.RandomSource;
import dungeonforge.config.GameConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterFactoryTest {

    private MonsterFactory factory;

    @BeforeEach
    public void setUp() {
        RandomSource.resetForTests();
        GameConfig.resetForTests();
        RandomSource.getInstance().reseed(42L);
        factory = new MonsterFactory();
    }

    @Test
    public void testFactoryLoadsMonstersFromJson() {
        // Assuming you have at least one theme/monster in monsters.json (e.g., in crypt or forge)
        List<String> cryptMonsters = factory.idsForTheme("crypt");
        assertNotNull(cryptMonsters, "Crypt monster list should not be null.");
    }

    @Test
    public void testCreateValidMonsterWithScaling() {
        // Test creating a monster at depth 1 vs depth 3
        Monster monsterLevel1 = factory.create("skeleton", 1);
        Monster monsterLevel3 = factory.create("skeleton", 3);

        assertNotNull(monsterLevel1, "Monster at depth 1 should be created.");
        assertNotNull(monsterLevel3, "Monster at depth 3 should be created.");

        // Depth 3 should generally have higher or scaled stats compared to depth 1
        // (Assuming base definition or scaling logic increases stats with depth)
        assertTrue(monsterLevel3.getMaxHp() >= monsterLevel1.getMaxHp(),
                "Higher depth monsters should have scaled equal or greater HP.");
    }

    @Test
    public void testFallbackMonsterForInvalidId() {
        Monster unknownMonster = factory.create("nonexistent_monster_id_999", 1);
        assertNotNull(unknownMonster, "Factory must return a fallback monster instead of null.");
        assertEquals("Glitch Goblin", unknownMonster.getName(), "Fallback monster name should match.");
    }

    @Test
    public void testThemeQueryHelpers() {
        // Test that theme query helpers return lists and boss IDs without crashing
        List<String> forgeIds = factory.idsForTheme("forge");
        assertNotNull(forgeIds, "Ids for forge theme should be initialized.");

        String forgeBoss = factory.bossIdForTheme("forge");
        // It might be null if no boss is defined for forge, but the method should execute safely
        assertTrue(forgeBoss == null || !forgeBoss.isEmpty(), "Boss ID should be null or a non-empty string.");
    }
}