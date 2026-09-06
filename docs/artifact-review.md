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

Pick the **three strongest user stories** in `docs/backlog.md`. For each, two or three
sentences.

> Praise is harder than criticism, and it's where most of the learning is. "It's clear" earns
> nothing. "Its third criterion names an observable output — the same object reference — so
> two people would always agree whether it passed" earns full marks.

### Strong story 1: ______

**INVEST letters it satisfies especially well:**

**What specifically makes its acceptance criteria checkable:**

### Strong story 2: ______

**INVEST letters it satisfies especially well:**

**What specifically makes its acceptance criteria checkable:**

### Strong story 3: ______

**INVEST letters it satisfies especially well:**

**What specifically makes its acceptance criteria checkable:**

---

## C3 — Trace a story to code · 4 pts

Take **US-1.1** (settings live in one place). **Write no Java.** In plain English, describe
what you'd expect to see in the pull-request diff when this story is done, and which
acceptance criterion each piece satisfies.

| What I'd expect in the diff | Which acceptance criterion it satisfies |
|---|---|
|  |  |
|  |  |
|  |  |
|  |  |

**One sentence: how did the acceptance criteria help you predict the shape of the work?**

---

## C4 — The bonus catch · up to +3 bonus

Once you have dealt with the bad story, something in `docs/sprint-01-plan.md` no longer adds
up the way it did.

**What is it:**

**What a real team would do about it in sprint planning:**

**What this suggests about the relationship between vague work and over-committed sprints:**

---

## C5 — One honest question

What is one thing about the Scrum process you still don't understand after this week? A good
question here is worth more to me than a confident wrong answer.

