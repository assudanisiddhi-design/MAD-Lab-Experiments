# Walkthrough - Experiment 2: Activity Lifecycle Application

Developed a multi-activity Android application to demonstrate the Activity Lifecycle and data passing between screens.

## Changes Made

### Project Structure
- Configured a new module `:Exp-2:app` in the root project.
- Organized the source code into a multi-activity architecture:
    - `LoginActivity`: Handles user authentication (Name & USN).
    - `DashboardActivity`: Provides a central navigation hub.
    - `LifecycleActivity`: Overrides and displays lifecycle callback methods.
    - `HomeActivity` & `AccountActivity`: Complementary screens for a complete experience.

### Lifecycle Tracking
- Implemented `LifecycleActivity` to log every state transition.
- Used a reactive `mutableStateListOf` to display the sequence of calls directly on the UI in real-time.
- Added `Toast` notifications for immediate feedback on state changes.

### Documentation
- Created a comprehensive [Exp-2/README.md](file:///D:/MAD Lab Experiments/Exp-2/README.md) with project details and test cases.
- Captured and embedded 5 screenshots demonstrating the full application flow.
- Updated the root [README.md](file:///D:/MAD Lab Experiments/README.md) to reflect completion.

## Verification Results

### Build & Sync
- [x] Gradle Sync: Successful
- [x] Build Task (`:Exp-2:app:assembleDebug`): Successful

### Functional Testing
- [x] **Authentication**: Successfully passed Name and USN from Login to other activities.
- [x] **Navigation**: Verified all three dashboard options lead to correct screens.
- [x] **Lifecycle Methods**: Confirmed `onCreate`, `onStart`, and `onResume` are called in sequence. Verified `onPause` and `onStop` when navigating away.

## Screenshots
- [Login Screen](file:///D:/MAD Lab Experiments/Exp-2/screenshots/login.png)
- [Dashboard](file:///D:/MAD Lab Experiments/Exp-2/screenshots/dashboard.png)
- [Lifecycle Tracker](file:///D:/MAD Lab Experiments/Exp-2/screenshots/lifecycle.png)
- [Account Details](file:///D:/MAD Lab Experiments/Exp-2/screenshots/account.png)
