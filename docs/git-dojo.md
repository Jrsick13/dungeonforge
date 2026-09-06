# Git Dojo — my recovery notes

> Part D of Lab 2. For each drill: the command(s) you ran, **one sentence in your own
> words** on what it did, and one on when you would reach for it again.
>
> Graded on the sentences, not the commands. Commands can be copied; understanding cannot.

## The three trees — in my own words

| Tree | What lives here |
|---|---|
| Working Directory | The actual files currently on disk that you are creating, editing, or deleting. |
| Staging Area (Index) | The snapshot region holding prepared changes ready to be included in the next commit. |
| HEAD | A pointer to the latest commit on your current active branch. |

---

## Drill 1 — Committed to `main` by accident

**Commands I ran:**
```bash
git switch -c fix/rescued-work
git switch main
git reset --hard origin/main

```
**What it did:**
It preserved my recent commit on a newly created feature branch, then forcefully reset my local main branch back to match the remote state.
**When I would use it again:**
When I accidentally commit changes directly onto main instead of creating a feature branch first.
---

## Drill 2 — Wrong commit message / forgot a file

**Commands I ran:**
```bash
git add missing-file.txt
git commit --amend -m "docs: add missing file and correct message"
```
**What it did:**
It combined the newly staged changes with the previous commit and updated the commit message under a new commit hash.
**Why you must not do this to a commit you already pushed:**
Amending rewrites Git history by creating a new commit ID, which causes history conflicts and broken branches for teammates sharing the remote repository.
---

## Drill 3 — Committed a file that should be ignored

**Commands I ran:**
```bash
git rm -r --cached target
echo "target/" >> .gitignore
git add .gitignore
git commit -m "chore: untrack build output"
```
**What it did:**
It removed build output files from Git tracking while preserving them on disk, and updated .gitignore to prevent future tracking.
**Why adding it to `.gitignore` alone was not enough:**
.gitignore alone was not enough: .gitignore only prevents untracked files from being staged; files that are already tracked in the repository must be explicitly untracked using git rm --cached.
---

## Drill 4 — Merge conflict

**Commands I ran:**
```bash
git merge feature/b
# (Edited README.md to remove conflict markers <<<<<<<, =======, >>>>>>>)
git add README.md
git commit -m "docs: resolve merge conflict between feature/a and feature/b"
```
**In the conflict markers, which side was "mine"?**
The top section between <<<<<<< HEAD and ======= represents "mine" (the active branch).
**What it did:**
It paused the automated merge so conflicting lines could be manually edited and unified before recording a single final merge commit.
**How I would back out of a merge I regretted starting:**
Run git merge --abort in the terminal to stop the merge process and restore the working tree to its pre-merge state.
---

## Drill 5 — "I destroyed everything"

**Commands I ran:**
```bash
git reset --hard HEAD~3
git reflog
git reset --hard 7a6e625
```
**What `git reflog` showed me:**
It displayed an ordered log recording every position the HEAD pointer has occupied locally, including commits detached or deleted via hard resets.
**One sentence on why this changes how nervous I should be about Git:**
Knowing git reflog keeps a safety log of local HEAD movements proves almost any mistake can be undone as long as changes were committed at least once.
---

## Stretch — Drill 6 (detached HEAD, interactive rebase)

**Notes:**
Interactive rebase (git rebase -i HEAD~4) opens an editor listing historical commits, allowing you to reorder, edit, or mark commits with squash (or s) to combine multiple commits into a single commit.
---

## The one command I want to remember from today
git reflog
