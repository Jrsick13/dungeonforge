package dungeonforge.factory;

import dungeonforge.config.GameConfig;
import dungeonforge.config.RandomSource;
import dungeonforge.core.Monster;

import java.util.ArrayList;
import java.util.List;

public class StandardRoomPopulator extends RoomPopulator {

    public StandardRoomPopulator(ThemeKit theme) {
        super(theme);
    }

    @Override
    protected List<Monster> createEncounter(int depth) {
        List<Monster> monsters = new ArrayList<>();
        int maxMonsters = GameConfig.getInstance().getInt("maxMonstersPerRoom", 2);
        int count = RandomSource.getInstance().nextInt(maxMonsters + 1);

        for (int i = 0; i < count; i++) {
            MonsterDef def = theme.createMonster(depth);
            monsters.add(new Monster(def));
        }
        return monsters;
    }
}