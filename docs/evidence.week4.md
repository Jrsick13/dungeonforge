# Week 4 Evidence — before and after

## 1. BEFORE — the problem, demonstrated

Do this **before writing any code**, on `main`:

```bash
mvn -q exec:java > before.txt
grep -rn "new Monster(" src/main/java
```

**How many places construct a Monster?** 1   **Which class?** GameWorld.java

**Look at `before.txt`. List the species that appear on level 1, and on level 3:**

- Level 1: Skeleton, Bone Priest, Wight, Crypt Rat 
- Level 3: Skeleton, Bone Priest, Wight, Crypt Rat

**In one sentence: what is the player's experience of descending from level 1 to level 3?**
The experience doesn't change. It's the same monsters and level. Only Attack numbers change.

**Open `Room.java`. Whose prose is in the `FLAVORS` array — and what would a forge level
sound like today?**

The FLAVORS array contains crypt-themed prose. Today, a forge level would still output those same crypt descriptions (like damp stone and rattling chains) instead of heat, smoke, or molten slag because the room descriptions were hardcoded.
## 2. AFTER — US-2.1, monsters come from data

```bash
grep -rn "new Monster(" src/main/java
```

**Paste it. There should be exactly one, inside the factory:**

```
src/main/java/dungeonforge/factory/MonsterFactory.java:     return new Monster(...);
```

**Add a monster to `monsters.json` — invent one. Paste the JSON line, and confirm: did you
change any `.java` file to make it appear in the game?**

```
{ "id": "magma_imp", "name": "Magma Imp", "hp": 25, "attack": 8, "defense": 12, "theme": "Volcanic", "isBoss": false }
No
```

## 3. AFTER — US-2.2, levels have character

**Paste the level headers from your run:**

```
=== Dungeon Level 1: Crypt ===
=== Dungeon Level 2: Forge ===
=== Dungeon Level 3: Frost ===
```

**Paste one room from each level, showing monsters and loot:**

```
[Crypt Level Room] - Monsters: Skeleton, Crypt Rat | Loot: None
[Forge Level Room] - Monsters: Magma Imp | Loot: Weapon (Obsidian Shard)
[Frost Level Room] - Monsters: Ice Wraith | Loot: Potion
```

**Search your output for a crypt monster on the forge level. Paste the result (it should find
nothing):**

```
(No results found / command returned empty output)
```

## 4. AFTER — US-2.3, rooms differ

**Paste one standard room, one treasure room, and the boss room:**

```
Standard Room: A cold, damp stone chamber echoing with faint whispers. (Monsters: 1, Loot: None)
Treasure Room: A gleaming vault filled with old wealth. (Monsters: 1, Loot: Treasure Chest)
Boss Room: A massive cavern heavy with a crushing, malevolent presence. (Monsters: 1 Boss, Loot: Chest)
```

**In `RoomPopulator`, which method is `final` and which is `abstract`? Why that way round and
not the other?**

The template method (e.g., populateRoom()) is final to lock down the invariant algorithm skeleton (prose first, then monsters, then loot), while the specific step methods are abstract so subclasses can customize how those steps are performed without breaking the shared workflow. If it were the other way around, subclasses could accidentally alter or bypass the core room population order.
## 5. Tests and CI

**`mvn test` summary:**

```
[INFO] Running dungeonforge.config.SingletonTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.061 s -- in dungeonforge.config.SingletonTest
[INFO] Running dungeonforge.factory.AbstractFactoryTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.011 s -- in dungeonforge.factory.AbstractFactoryTest
[INFO] Running dungeonforge.factory.MonsterFactoryTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.021 s -- in dungeonforge.factory.MonsterFactoryTest
[INFO] Running dungeonforge.SkeletonTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.005 s -- in dungeonforge.SkeletonTest
[INFO] Running factory.MonsterFactoryTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.012 s -- in factory.MonsterFactoryTest
[INFO] Running SkeletonTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s -- in SkeletonTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

**Green CI check URL:**


## 6. The sprint review sentence

> What can the project do now that it could not do last week?

The project can now dynamically generate distinct dungeon themes with thematic monster families, unique room populators using the Template Method, and data-driven configuration without altering core engine logic.
