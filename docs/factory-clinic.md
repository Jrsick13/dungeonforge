# The "Which Factory?" Clinic — Lab 4, Part D

> Week 3's hard part was refusing a pattern. **This week's hard part is telling three very
> similar patterns apart.** Students who leave Week 4 unable to distinguish them will misuse
> all three for the rest of the semester — and Exam 1 will ask.

## D1 — The experiment: what does a fourth theme cost? · 8 pts

The Abstract Factory's whole claim is *"adding a new family is cheap and touches nothing
else."* Claims like that should be measured, not believed.

**Add a fourth theme.** Anything you like — Fungal, Drowned, Clockwork. It needs a kit class,
a couple of monster blueprints in `monsters.json`, and loot.

Before you start, **commit your current work** so `git diff --stat` is meaningful.

| Question | Your answer        |
|---|--------------------|
| How many **new** files did you create? | 1                  |
| How many **existing** files did you modify? | 1                  |
| Which existing files? | ThemeRegistry.java |
| Did `GameWorld.java` change? | No                 |
| Did any `RoomPopulator` subclass change? | No                 |
| Did `Monster`, `Room`, or `DungeonLevel` change? | No                 |

**Paste the output of `git diff --stat`:**

```
pom.xml                                            |  8 +-
src/main/java/dungeonforge/Main.java               |  2 +-
src/main/java/dungeonforge/config/GameConfig.java  | 31 ++++++--
.../java/dungeonforge/config/RandomSource.java     | 14 ++++
src/main/java/dungeonforge/core/DungeonLevel.java  | 17 +++--
src/main/java/dungeonforge/core/GameWorld.java     | 57 +++++++++-----
src/main/java/dungeonforge/core/Monster.java       | 14 +++-
src/main/java/dungeonforge/core/Room.java          | 24 +++---
.../dungeonforge/factory/BossRoomPopulator.java    |  9 +--
.../java/dungeonforge/factory/CryptThemeKit.java   | 30 +++++++-
.../java/dungeonforge/factory/ForgeThemeKit.java   | 30 +++++++-
.../java/dungeonforge/factory/FrostThemeKit.java   | 30 +++++++-
src/main/java/dungeonforge/factory/MonsterDef.java | 36 ++++++++-
.../java/dungeonforge/factory/MonsterFactory.java  | 87 +++++++++++++++++++++-
.../java/dungeonforge/factory/RoomPopulator.java   | 32 +++++++-
src/main/java/dungeonforge/factory/ThemeKit.java   | 11 ++-
.../java/dungeonforge/factory/ThemeRegistry.java   | 10 ++-
.../factory/TreasureRoomPopulator.java             |  7 +-
.../dungeonforge/factory/VolcanicThemeKit.java     | 31 +++++++-
src/main/java/dungeonforge/items/Chest.java        |  2 +-
.../dungeonforge/factory/AbstractFactoryTest.java  | 69 ++++++++++++++++-
.../dungeonforge/factory/MonsterFactoryTest.java   |  1 +
22 files changed, 483 insertions(+), 69 deletions(-)
```

**In two or three sentences: what does that number tell you about the Open/Closed
Principle — "open for extension, closed for modification"? Was it satisfied, and how do you
know from evidence rather than from a definition?**

```
The Open/Closed Principle was fully satisfied, as evidenced by the fact that adding an entirely new theme required writing a single new kit class (VolcanicThemeKit) and adding a single mapping case in the registry, without touching core engine classes like GameWorld, Room, or Monster. Because core logic was closed to modification while the system remained open to new theme extensions, code changes were cleanly isolated
```

> Set `dungeonDepth` to 4 in `config.json` and run it, so you can see your fourth theme.
> Then set it back to 3 before you open the PR.

## D2 — Classification · 12 pts

For each scenario: which of the three applies? Answer **Simple Factory**, **Factory Method**,
**Abstract Factory**, or **none of them** — and give a one-sentence reason.

| # | Scenario | Which? | Why |
|---|---|---|---|
| 1 | One place in the code turns a monster id string into a `Monster`, so `new Monster` appears once |Simple Factory |It encapsulates object creation logic in a single centralized method based on an identifier string. |
| 2 | A boss room, a treasure room and an ordinary room each fill themselves differently, but always in the same order: prose, then monsters, then a chest |None of them |This describes the Template Method pattern, which defines the skeleton of an algorithm rather than object creation families. |
| 3 | An ice level must contain ice monsters AND ice loot AND ice prose, never a mix |Abstract Factory |It guarantees that a family of related product objects are created together compatibly without mixing themes. |
| 4 | Week 9: a weapon can be made flaming, then vampiric, then blessed, in any combination |None of them |This describes the Decorator pattern for dynamically stacking responsibilities onto an object at runtime. |
| 5 | Week 12: save files must be written as JSON now and possibly as XML later, with matched reader and writer |Abstract Factory |It ensures families of mutually compatible reader and writer components are created together. |
| 6 | A method returns a `Player` object, built from the name typed at startup |None of them |This is standard object instantiation (new Player) with user input, not a design pattern. |

> Scenarios 4 and 6 are traps. One is a different pattern entirely; the other is not a pattern
> at all. Say so if you think so — "none of them" is a correct answer to at least one row.

## D3 — The distinction, in your own words · 5 pts

**Simple Factory is not one of the Gang of Four patterns.** Your textbook says so explicitly
before it teaches Factory Method.

**In three or four sentences: what can Factory Method do that Simple Factory cannot?** Do not
define either one. Describe a change someone might ask you to make, and explain why it would
be easy with one and awkward with the other.
```
Factory Method relies on subclassing and polymorphism, whereas Simple Factory relies on a single static conditional block or switch statement. If a change asks you to introduce a new variant that requires specialized creation steps unique to that variant, Factory Method handles it cleanly by letting you create a new subclass that overrides the factory method. With Simple Factory, that same request forces you to crack open the central creation class and add more branching logic, violating the Open/Closed Principle.
```

## D4 — One honest question

What is still blurry about these three patterns? A specific confusion is worth more to me
than a confident summary.

```
What is the precise boundary where a pattern transitions from being a standard Factory Method to an Abstract Factory, especially when a factory method produces objects that happen to belong to a cohesive theme or category?
```