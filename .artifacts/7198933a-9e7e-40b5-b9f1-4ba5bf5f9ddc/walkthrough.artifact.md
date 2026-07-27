# Walkthrough - MAD Lab Experiments Organization

Organized the repository with 12 experiment folders, added descriptive READMEs for each, and prepared the project for version control.

## Changes Made

### Directory Structure
- Created folders `Exp-2` to `Exp-12` to organize the full lab syllabus.
- Added a `README.md` in each folder with experiment titles and feature lists.
- Created a root `README.md` that links to all experiments and provides a project overview.

### Git Configuration
- [!] **Note**: I attempted to initialize a Git repository and push to GitHub (`https://github.com/assudanisiddhi-design/MAD-Lab-Experiments.git`). However, the `git` command was not recognized in the current shell environment.

## Experiments Organized
1.  **Exp-1**: Hello World (Existing)
2.  **Exp-2**: Visiting Card App
3.  **Exp-3**: Simple Calculator
4.  **Exp-4**: Sign Up & Login with Validation
5.  **Exp-5**: Wallpaper Changer
6.  **Exp-6**: Counter Application
7.  **Exp-7**: JSON and XML Parser
8.  **Exp-8**: Text-to-Speech (TTS)
9.  **Exp-9**: Phone Dialer (Call & Save)
10. **Exp-10**: GPS Location Tracker
11. **Exp-11**: SQLite Database Operations
12. **Exp-12**: Notification Manager

## Verification Results
- [x] All 12 folders created.
- [x] All 13 README files (12 folder-specific + 1 root) successfully written.
- [ ] Git push: Pending (requires local Git installation).

## Next Steps
- **Manual Push**: Since I cannot access `git` from my environment, please run the following commands in your terminal from `D:/MAD Lab Experiments`:
  ```bash
  git init
  git add .
  git commit -m "Initial commit with 12 experiment folders"
  git remote add origin https://github.com/assudanisiddhi-design/MAD-Lab-Experiments.git
  git branch -M main
  git push -u origin main
  ```
