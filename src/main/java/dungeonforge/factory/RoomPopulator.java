package dungeonforge.factory;

import dungeonforge.core.Monster;
import dungeonforge.core.Room;
import dungeonforge.items.Chest;

import java.util.List;

public abstract class RoomPopulator {
    protected final ThemeKit theme;

    public RoomPopulator(ThemeKit theme) {
        this.theme = theme;
    }

    public final void populate(Room room, int depth) {  // FIXED skeleton
        room.setFlavor(theme.createRoomFlavor());
        for (Monster m : createEncounter(depth)) {
            room.addMonster(m);
        }
        Chest chest = createChest(depth);
        if (chest != null && !chest.isEmpty()) {
            room.setChest(chest);
        }
    }

    protected abstract List<Monster> createEncounter(int depth); // DEFERRED

    protected Chest createChest(int depth) {
        return null; // HOOK
    }
}