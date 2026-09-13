package dungeonforge.config;

import java.util.Random;

public final class RandomSource {

    private static RandomSource instance;
    private final Random random;

    private RandomSource() {
        long seed = GameConfig.getInstance().getLong("seed", 42L);
        this.random = new Random(seed);
    }

    public static synchronized RandomSource getInstance() {
        if (instance == null) {
            instance = new RandomSource();
        }
        return instance;
    }

    public void reseed(long seed) {
        this.random.setSeed(seed);
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static synchronized void resetForTests() {
        instance = null;
    }
}