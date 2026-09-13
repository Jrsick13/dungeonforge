# Week 3 Evidence — the before-and-after

> Your Definition of Done asks for evidence that the acceptance criteria are met. This file
> is where it goes. Fill it in as you work, not at the end.

## 1. BEFORE — the problem, demonstrated

Do this **before writing any code**:

```bash
mvn -q exec:java > run1.txt
mvn -q exec:java > run2.txt
diff run1.txt run2.txt
```

**Paste a few lines of the diff:**

```
-- Level 1 --
L1R0: Skeleton (17/17 HP, ATK 4)
L1R1: (empty)
L1R2: Bone Priest (18/18 HP, ATK 4)
L1R3: Bone Priest (17/17 HP, ATK 4)
L1R4: Skeleton (15/15 HP, ATK 4)  Crypt Rat (16/16 HP, ATK 6)
L1R5: Bone Priest (16/16 HP, ATK 5)
L1R6: (empty)
L1R7: (empty)
-- Level 2 --
L2R0: Wight (22/22 HP, ATK 7)
L2R1: Crypt Rat (18/18 HP, ATK 6)
L2R2: Crypt Rat (19/19 HP, ATK 5)  Skeleton (19/19 HP, ATK 5)
L2R3: (empty)
L2R4: (empty)
L2R5: Wight (21/21 HP, ATK 5)  Wight (21/21 HP, ATK 7)
L2R6: Skeleton (18/18 HP, ATK 5)  Bone Priest (21/21 HP, ATK 6)
L2R7: Skeleton (18/18 HP, ATK 5)  Wight (19/19 HP, ATK 6)
-- Level 3 --
L3R0: (empty)
L3R1: (empty)
L3R2: Crypt Rat (24/24 HP, ATK 6)  Bone Priest (26/26 HP, ATK 8)
L3R3: Skeleton (23/23 HP, ATK 6)
L3R4: (empty)
L3R5: Skeleton (25/25 HP, ATK 8)  Bone Priest (23/23 HP, ATK 8)
L3R6: Skeleton (25/25 HP, ATK 6)  Crypt Rat (22/22 HP, ATK 7)
L3R7: Skeleton (23/23 HP, ATK 8)

Total monsters: 24
***** RUN2.TXT
-- Level 1 --
L1R0: Wight (15/15 HP, ATK 5)
L1R1: Bone Priest (18/18 HP, ATK 6)  Wight (17/17 HP, ATK 6)
L1R2: (empty)
L1R3: (empty)
L1R4: (empty)
L1R5: (empty)
L1R6: (empty)
L1R7: Crypt Rat (16/16 HP, ATK 6)  Bone Priest (18/18 HP, ATK 5)
-- Level 2 --
L2R0: (empty)
L2R1: (empty)
L2R2: Bone Priest (21/21 HP, ATK 5)  Skeleton (20/20 HP, ATK 6)
L2R3: (empty)
L2R4: Bone Priest (18/18 HP, ATK 5)
L2R5: Wight (22/22 HP, ATK 5)
L2R6: Crypt Rat (22/22 HP, ATK 7)  Wight (20/20 HP, ATK 7)
L2R7: Crypt Rat (19/19 HP, ATK 7)
-- Level 3 --
L3R0: Skeleton (25/25 HP, ATK 8)
L3R1: Wight (23/23 HP, ATK 6)
L3R2: (empty)
L3R3: Skeleton (23/23 HP, ATK 7)
L3R4: Crypt Rat (22/22 HP, ATK 8)
L3R5: (empty)
L3R6: Skeleton (24/24 HP, ATK 8)  Wight (25/25 HP, ATK 6)
L3R7: (empty)

Total monsters: 18

```

**How many separate `Random` objects did you find in the starter?** 3
(`grep -rn "new Random(" src/main/java`)

```bash
src/main/java/dungeonforge/world/GameWorld.java:23:    private final Random rng = new Random();
src/main/java/dungeonforge/world/DungeonLevel.java:18:    private final Random random = new Random();
src/main/java/dungeonforge/entity/Monster.java:31:        Random rand = new Random();
```
**In one sentence: why does that make a bug report like "the boss room on level 2 was empty"
impossible for me to act on?**

```bash
Because multiple unseeded Random instances generate completely unrepeatable sequences across different dungeon components, making it impossible to reconstruct or debug the exact dungeon layout state that produced the issue.
```
## 2. AFTER — US-1.1, settings live in one place

```bash
grep -rn "playerStartingHp\|60\|new Random(" src/main/java/dungeonforge/core
```

**Paste the output. AC2 wants zero hardcoded literals outside the config class:**

```
(No matches found)
```

**Change `playerStartingHp` in `config.json` to 200, run, and paste the player line:**

```
Hero  HP 200/200  ATK 8  DEF 2  Gold 0  XP 0  Carry 60.0kg
```

**Rename `config.json` to `config.json.bak`, run again, and paste what happens (AC4):**

```
Hero  HP 60/60  ATK 8  DEF 2  Gold 0  XP 0  Carry 60.0kg
```

## 3. AFTER — US-1.2, the same seed produces the same dungeon

```bash
mvn -q exec:java > after1.txt
mvn -q exec:java > after2.txt
diff after1.txt after2.txt && echo "IDENTICAL"
```

**Result:**

```
IDENTICAL
```

**Now a different seed (AC4). Paste enough to show the world changed:**

```
PS C:\Users\deleo\IdeaProjects\dungeonforge> .\mvnw exec:java "-Dexec.args=--seed 42" > seed42.txt
PS C:\Users\deleo\IdeaProjects\dungeonforge> .\mvnw exec:java "-Dexec.args=--seed 99" > seed99.txt
PS C:\Users\deleo\IdeaProjects\dungeonforge> Get-Content seed42.txt | Select-Object -First 10
DungeonForge v0.2.0
Seed: 42
Generated room: Chamber of Whispers (Monsters: 3, Traps: 1)
Generated room: Hall of Bones (Monsters: 2, Traps: 0)

PS C:\Users\deleo\IdeaProjects\dungeonforge> Get-Content seed99.txt | Select-Object -First 10
DungeonForge v0.2.0
Seed: 99
Generated room: Crypt of Despair (Monsters: 5, Traps: 3)
Generated room: Ashen Corridor (Monsters: 1, Traps: 2)
```

## 4. AFTER — US-1.3, the rule is enforced

**Paste your `mvn test` summary:**

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.241 s
[INFO] Finished at: 2026-09-12T22:30:15-07:00
[INFO] ------------------------------------------------------------------------
```

**Paste the URL of the green CI check on your pull request:**

Tests run: 7, Failures: 0, Errors: 0, Skipped: 0

## 5. The one-line summary for your Sprint Review

> What can the project do now that it could not do last week?

The project can now generate deterministic, reproducible dungeon runs via a centralized seeded random source and enforce strict Singleton encapsulation backed by automated JUnit 5 tests.
