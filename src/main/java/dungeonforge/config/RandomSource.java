package dungeonforge.config;

import java.util.Random;

public final class RandomSource {

    private static RandomSource instance;
    private final Random random;
    private long currentSeed;

    private RandomSource() {
        long seed = GameConfig.getInstance().getLong("seed", 42L);
        this.currentSeed = seed;
        this.random = new Random(seed);
    }

    public static synchronized RandomSource getInstance() {
        if (instance == null) {
            instance = new RandomSource();
        }
        return instance;
    }

    public void reseed(long seed) {
        this.currentSeed = seed;
        this.random.setSeed(seed);
    }

    public long getSeed() {
        return currentSeed;
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public <T> T pick(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[random.nextInt(array.length)];
    }

    public static synchronized void resetForTests() {
        instance = null;
    }
}