# Sprint 0 Plan — Week 2 — SUPPLIED

**Epic:** E2, Process infrastructure

## Sprint Goal

> A repository that builds itself, tests itself, and refuses work that isn't finished.

## Committed stories

| Issue | Story | Points |
|---|---|---|
| S0.1 | The project builds and tests itself | 3 |
| S0.2 | A broken build blocks a merge | 2 |
| S0.3 | The board shows the truth | 2 |

**Capacity:** ~8 points · **Committed:** 7 points

## Risks

| Risk | Mitigation |
|---|---|
| Maven or JDK 21 not installed, blocking every story | Verify `mvn -v` and `java -version` first; IntelliJ bundles Maven if needed |
| `gh` CLI authentication fails, blocking the seed | `docs/backlog.md` contains every issue for manual creation — about twenty minutes |
| Hidden `.github/` folder not copied on macOS | Cmd+Shift+. reveals hidden files in Finder; confirm with `ls -a` |

---

# ↓ YOU fill in these two sections at the end of the week ↓

## Calibration — actual vs. estimate

> Do this every sprint. You are not estimating your own work until Week 6, but the data you
> record now is what makes those estimates useful when you get there. There is no way to
> learn this from theory.

| Story | Estimated points | Actual hours | Was the estimate high, low, or about right? |
| :--- | :---: | :---: | :--- |
| S0.1 | 3 | 3 | About right |
| S0.2 | 2 | 2 | About right |
| S0.3 | 2 | 2 | About right |

**Points completed (my first velocity number):** ____

## Sprint Review — one sentence

> What can the project do now that it could not do at the start of the week?
The core Java 21 Maven project structure, automated GitHub Actions CI workflow, and initial Scrum board backlogs are now fully operational and validated.

# Sprint 00 Retrospective

### 1. What went well?
- Successfully set up the project skeleton, GitHub Actions CI pipeline, and board issues.

### 2. What could be improved?
- Branch synchronization issues between feature branches and main caused git push rejections.

### 3. One specific thing to do differently next sprint:
- Switch to main and pull latest remote changes before creating new feature branches.