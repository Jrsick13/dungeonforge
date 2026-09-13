package dungeonforge.config;

import dungeonforge.core.GameWorld;
import dungeonforge.core.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingletonTest {

    @BeforeEach
    public void setUp() {
        RandomSource.resetForTests();
        GameConfig.resetForTests();
    }

    @Test
    public void testSameInstanceReturned() {
        RandomSource r1 = RandomSource.getInstance();
        RandomSource r2 = RandomSource.getInstance();
        assertSame(r1, r2, "RandomSource.getInstance() must return the exact same instance reference.");

        GameConfig c1 = GameConfig.getInstance();
        GameConfig c2 = GameConfig.getInstance();
        assertSame(c1, c2, "GameConfig.getInstance() must return the exact same instance reference.");
    }

    @Test
    public void testConstructorsArePrivate() {
        Constructor<?>[] randomSourceConstructors = RandomSource.class.getDeclaredConstructors();
        for (Constructor<?> constructor : randomSourceConstructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "RandomSource constructor must be private.");
        }

        Constructor<?>[] gameConfigConstructors = GameConfig.class.getDeclaredConstructors();
        for (Constructor<?> constructor : gameConfigConstructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "GameConfig constructor must be private.");
        }
    }

    @Test
    public void testSameSeedProducesIdenticalSequence() {
        RandomSource.getInstance().reseed(42L);
        int val1 = RandomSource.getInstance().nextInt(100);
        int val2 = RandomSource.getInstance().nextInt(100);

        RandomSource.resetForTests();
        RandomSource.getInstance().reseed(42L);
        int val3 = RandomSource.getInstance().nextInt(100);
        int val4 = RandomSource.getInstance().nextInt(100);

        assertEquals(val1, val3, "Same seed must yield identical first random output.");
        assertEquals(val2, val4, "Same seed must yield identical second random output.");
    }

    @Test
    public void testDifferentSeedProducesDifferentSequence() {
        RandomSource.getInstance().reseed(42L);
        int val1 = RandomSource.getInstance().nextInt(1000000);

        RandomSource.resetForTests();
        RandomSource.getInstance().reseed(99L);
        int val2 = RandomSource.getInstance().nextInt(1000000);

        assertNotEquals(val1, val2, "Different seeds must produce different random values.");
    }

    @Test
    public void testSameSeedProducesSameDungeon() {
        RandomSource.getInstance().reseed(42L);
        GameWorld world1 = new GameWorld(new Player("Delver"));
        int monsters1 = world1.totalMonsters();

        RandomSource.resetForTests();
        GameConfig.resetForTests();

        RandomSource.getInstance().reseed(42L);
        GameWorld world2 = new GameWorld(new Player("Delver"));
        int monsters2 = world2.totalMonsters();

        assertEquals(monsters1, monsters2, "Same seed must produce the same total monster count in the dungeon.");
    }
}