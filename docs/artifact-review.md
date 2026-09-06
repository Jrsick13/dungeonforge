# Artifact Review Clinic — Lab 2, Part C

> **This is the only document you write this week.** Everything else — the epics, the
> stories, the acceptance criteria, the Definition of Done, the sprint plans — was written
> for you.
>
> Reading critically is a harder and more useful skill than writing from a blank page, and it
> is the one that will make your own stories good when you start writing them in Week 6.

Read all three before answering:
- `docs/backlog.md`
- `docs/definition-of-done.md`
- `docs/sprint-01-plan.md`

---

## C1 — Find the three planted flaws · 12 pts

There is **exactly one deliberate defect in each of the three documents**: one bad user
story, one unverifiable Definition-of-Done criterion, and one sprint-plan item that isn't
what it claims to be.

> **Hint for the story:** re-read INVEST first. The bad one fails more than one letter.
>
> **Hint for the DoD:** ask of every checkbox — *could two reasonable people disagree about
> whether this is true?* If yes, it isn't a criterion. It's an opinion.

### Flaw 1 — in `docs/backlog.md`

**Which item:**
"Implement Combat and Magic Systems"

**What's wrong with it:**
The story combines multiple large, complex features into a single card without clear boundaries or specific inputs/outputs.

**Which INVEST letter(s) it violates, and how:**
- **S (Small):** It is far too large and complex to be estimated accurately or completed within a single sprint iteration.
- **T (Testable):** It lacks concrete, binary acceptance criteria, making it impossible to pass or fail objectively during testing.

**My repaired version:**
```markdown
As a player,
I want to select a basic physical attack from the action menu during a turn,
so that I can deal damage to an enemy target.

Acceptance Criteria
- Given a player's turn in combat, when the player selects "Attack" and target "Goblin", then the Goblin's health decreases by the player's attack stat value.
- Given a target with equal or less health than the damage dealt, when the attack lands, then the target is marked as defeated and removed from the active turn queue.
```

---

### Flaw 2 — in `docs/definition-of-done.md`

**Which checkbox:**
[ ] Code is well-structured and easy to understand"]
**Why it can't actually be checked:**
It is completely subjective and opinion-based. Two developers can easily disagree on whether code is "well-structured" or "easy to understand," making it impossible to verify objectively.
**My replacement, phrased so that it can be:**
[ ] Code passes all automated linter rules with zero errors and has received at least one peer code review approval.
---

### Flaw 3 — in `docs/sprint-01-plan.md`

**Which item:**
"Epic 3: World Map Generation" or "Risk 1: Hope nothing breaks in production"
**Why it isn't really what the document calls it:**
It is listed as a single sprint story/task, but it is actually a massive multi-sprint Epic (or a passive wish listed as an actionable risk mitigation plan) without estimated point breakdown or actionable tasks.
**My repaired version, including a mitigation someone could actually act on:**
| Story / Task ID | Estimated Hours | Actionable Deliverable / Mitigation |
| :--- | :---: | :--- |
| S1.1-MapSetup | 3 | Implement a standard 10x10 tile grid generation class covered by unit tests.

---

## C2 — Say what's good, and why · 9 pts

### Strong story 1: US-1.1 (Settings Live in One Place)

**INVEST letters it satisfies especially well:**
- **I (Independent) & V (Valuable):** It can be implemented without relying on other user stories, and it provides immediate value by establishing a single source of truth for configuration parameters.

**What specifically makes its acceptance criteria checkable:**
Its criteria explicitly state measurable inputs and expected binary outputs, such as reading configuration keys from a specific file path and throwing a defined runtime exception when a key is missing, leaving zero room for interpretation.

---

### Strong story 2: US-1.2 (Parse Command-Line Arguments)

**INVEST letters it satisfies especially well:**
- **S (Small) & T (Testable):** The scope is tightly bounded to string parsing from an array, allowing it to be easily completed in a few hours and thoroughly tested with unit assertions.

**What specifically makes its acceptance criteria checkable:**
The criteria define exact flags (e.g., `--config` or `-c`) and pair them directly with observable string outcomes, so two developers running the test suite would always agree on whether the test passes or fails.

---

### Strong story 3: US-1.3 (Validate Configuration Attributes)

**INVEST letters it satisfies especially well:**
- **E (Estimable) & T (Testable):** The bounds of valid versus invalid configuration parameters are completely enumerated, allowing accurate point estimation and straightforward test assertion writing.

**What specifically makes its acceptance criteria checkable:**
It outlines specific numerical ranges and threshold conditions (e.g., ports between 1 and 65535), ensuring that automated tests can assert exact pass/fail boundaries deterministically.

---

## C3 — Trace a story to code · 4 pts

| What I'd expect in the diff | Which acceptance criterion it satisfies |
|---|---|
| A new configuration manager or singleton class that encapsulates reading and storing key-value pairs. | Satisfies AC-1: Configuration values are loaded into and accessed via a single centralized component. |
| A file reader or properties parser method that loads key-value definitions from a standard configuration file. | Satisfies AC-2: Values are dynamically initialized from an external configuration file at runtime startup. |
| Getter methods for configuration properties that return strong types (e.g., String, int, boolean). | Satisfies AC-3: Client components query the centralized manager for strongly-typed configuration settings. |
| Error handling logic (e.g., throwing a `ConfigurationException`) when a required key is missing or malformed. | Satisfies AC-4: An explicit runtime error is triggered if a required configuration property cannot be resolved. |

**One sentence: how did the acceptance criteria help you predict the shape of the work?**
The explicit criteria detailed exact component interactions, inputs, and error states, making it easy to infer the exact classes, getters, and exception paths needed in the pull request.

---

## C4 — The bonus catch · up to +3 bonus

**What is it:**
Once the bad story is broken down or removed, the total estimated story points in `docs/sprint-01-plan.md` drop below the planned team velocity, leaving planned sprint capacity unallocated.

**What a real team would do about it in sprint planning:**
The team would re-evaluate the newly refined, properly sized user story and pull in additional prioritized backlog items to accurately match their target velocity.

**What this suggests about the relationship between vague work and over-committed sprints:**
Vague user stories mask their true complexity with inaccurate point estimates, leading teams to artificially inflate velocity expectations and over-commit to sprints they cannot realistically deliver.

---

## C5 — One honest question

How does a team effectively handle unexpected mid-sprint scope changes or critical bug fixes without completely derailing the committed sprint goal and WIP limits?

