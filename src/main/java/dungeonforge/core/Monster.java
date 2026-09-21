package dungeonforge.core;

import dungeonforge.config.RandomSource;
import dungeonforge.factory.MonsterDef;

/**
 * WEEK 1 -- a monster.
 *
 * TODO(week 3, US-1.2): this class owns its own Random. So does Room. So does GameWorld.
 * Three independent sources of randomness means the same seed can never reproduce the same
 * dungeon, which means a bug you hit once may never be reproducible. Count how many separate
 * Random instances exist in this project before you start.
 */

public class Monster extends Entity {

    private final String species;
    private final int xpReward;

    // Existing constructor (if needed elsewhere)
    public Monster(String species, int baseHp, int baseAttack, int xpReward) {
        super(species,
                baseHp + RandomSource.getInstance().nextInt(5) - 2,
                baseAttack + RandomSource.getInstance().nextInt(3) - 1,
                0);
        this.species = species;
        this.xpReward = xpReward;
    }

    // NEW constructor: Creates a runtime Monster from a MonsterDef blueprint
    public Monster(MonsterDef def) {
        super(def.getName(),
                def.getHp() + RandomSource.getInstance().nextInt(5) - 2,
                def.getAttack() + RandomSource.getInstance().nextInt(3) - 1,
                def.getXp());
        this.species = def.getName();
        this.xpReward = def.getXp();
    }

    public String getSpecies() { return species; }
    public int getXpReward()   { return xpReward; }

    @Override
    public String describe() {
        return species + " (" + hp + "/" + maxHp + " HP, ATK " + attackPower + ")";
    }
}