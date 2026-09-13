# The Singleton Audit — Lab 3, Part C

> **The hard part of Singleton week is not writing one. It is 12 lines of code.**
> The hard part is knowing when *not* to.
>
> Singleton is the most over-applied pattern in the book. A student who leaves this week able
> to write one has learned the easy half. A student who leaves able to *refuse* to write one
> has learned the half that matters.

Below are **eight** candidate classes from DungeonForge's future. Three you have already met;
five arrive in Weeks 4 to 15. For each, decide: **Singleton, or not?**

Answer with the test we will use all semester:

> **Would a second instance be a BUG, or merely unusual?**
>
> If two instances would produce *incorrect behaviour* — not just wasted memory, not just
> inconvenience — the class may deserve to be a Singleton.
> If two instances would merely be *odd*, it is a dependency, and you should pass it in.

Fill in every row. Two of the eight are genuine singletons; you already know which, because
you built them this week. Your job is to defend the other six answers.

| # | Class | What it does | Singleton? | Would a 2nd instance be a bug, or just unusual? Why?                                                                                                                                             |
|---|---|---|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | `GameConfig` | Holds every tunable setting | Yes        | Bug. If two instances existed, different parts of the game might read conflicting configuration values (e.g., difficulty or screen settings), leading to inconsistent game state.                |
| 2 | `RandomSource` | The one seeded RNG | Yes        | Bug. If two instances existed, different parts of the game might read conflicting configuration values (e.g., difficulty or screen settings), leading to inconsistent game state.                |
| 3 | `Player` | The player character | Yes        | Bug. Multiple RNG streams would desync seed reproduction and break determinism, making game logs and bug reports impossible to reproduce.                                                        |
| 4 | `MonsterFactory` (Wk 4) | Turns blueprints into monsters | No         | Unusual. While a solo game usually has one player, a co-op or split-screen mode would naturally require multiple player instances. It is regular domain data, not a global policy object.        |
| 5 | `EventBus` (Wk 5) | Publishes game events to subscribers | No         | Unusual. Having multiple factory instances is harmless. They are stateless utility objects that generate objects based on inputs; instantiating more than one is merely redundant, not incorrect. |
| 6 | `CommandHistory` (Wk 7) | The undo stack | No         | Unusual. If you had multiple independent command windows or modal UI sub-screens, separate undo stacks would be expected. Sharing one globally is a design choice, not a strict requirement.     |
| 7 | `SaveSystemFacade` (Wk 12) | Reads and writes save files | No         | Unusual. Instantiating multiple save handlers pointing to different files or serialization paths is entirely normal. Restricting it to one would hinder parallel saving/loading or testing.      |
| 8 | `Logger` | Writes diagnostic output to a file | No         |   Unusual. Multiple loggers writing to different streams (e.g., console vs. file) are common practice. A second instance is redundant rather than a system-breaking bug.                                                                                                                                                                                               |

## The three that will cause arguments

Rows 5, 7 and 8 are the interesting ones, and reasonable engineers disagree about all three.
Pick **one** of them and write a paragraph:

**Which one:** EventBus

**The case FOR making it a Singleton:**

An event bus is meant to act as a centralized nervous system for the entire application, allowing decoupled components to broadcast and react to events without holding direct references to each other. Making it a singleton ensures that every class in the project publishes to and subscribes from the exact same global messaging pipe, preventing accidental fragmentation where a component listens to a secondary bus and misses critical game events.

**The case AGAINST:**

Enforcing a global singleton event bus creates hidden dependencies and tight coupling across the entire architecture, making unit testing fragile because tests can leak event listeners and pollute state for subsequent tests. If subsystems or modular mini-games need isolated event handling, a singleton prevents you from instantiating localized event contexts.

**What you would actually do in this project, and why:**

In DungeonForge, I would keep the EventBus as a managed instance passed down through dependency injection rather than locking it down as a strict Singleton. This keeps the architecture flexible for testing, allowing unit tests to spin up a fresh, isolated event bus per test case without cross-talk or global state pollution.

> There is no answer key for this paragraph. You are graded on whether you engaged with the
> tension, not on which side you landed.

## One more question

Your `GameConfig` has a method called `resetForTests()`. It exists only so that tests can
undo the global state that the Singleton created.

**In one or two sentences: what is that method telling you about the pattern?**

It is telling you that Singletons introduce global side effects that are fundamentally hostile to unit testing. The need for a backdoor reset() method exposes the pattern's core flaw—it tightly couples code to global state, forcing developers to break encapsulation just to clean up test contamination.
