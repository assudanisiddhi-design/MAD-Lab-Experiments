# Walkthrough - Experiment 4: Linking Activities using Intents

Successfully implemented a multi-activity application to demonstrate navigation and data passing using Android Intents.

## Changes Made

### Architecture & UI
- **Multi-Activity Design**: Shifted from a Fragment-based architecture (Exp-3) to a Multi-Activity model to explicitly demonstrate **Intents**.
- **Explicit Intents**: Used `Intent(this, TargetActivity::class.java)` to link all major screens.
- **Modern Look**: Maintained the high-quality **Jetpack Compose** UI from Experiment 3 across all new activities.

### Data Management
- **Intent Extras**: Implemented data passing for "Name" and "USN" using `intent.putExtra()` and `intent.getStringExtra()`.
- **Verified Flow**: Verified that data persists correctly as the user navigates from Login -> Dashboard -> Student Details.

### Documentation
- Created a comprehensive [Exp-4/README.md](file:///D:/MAD Lab Experiments/Exp-4/README.md) explaining Explicit Intents and data bundles.
- Captured 3 screenshots demonstrating the Intent-based navigation flow.
- Updated the root [README.md](file:///D:/MAD Lab Experiments/README.md) to reflect the completion of Experiment 4.

## Verification Results

### Build & Sync
- [x] Gradle Sync: Successful
- [x] Build Task (`:Exp-4:app:assembleDebug`): Successful

### Functional Testing
- [x] **Intent Launching**: Verified that every button correctly triggers `startActivity()`.
- [x] **Data Transfer**: Confirmed that USN and Name are correctly received by the target activities via Intent extras.

## Screenshots
- [Login Screen](file:///D:/MAD Lab Experiments/Exp-4/screenshots/login.png)
- [Dashboard via Intent](file:///D:/MAD Lab Experiments/Exp-4/screenshots/dashboard.png)
- [Passed Data Verification](file:///D:/MAD Lab Experiments/Exp-4/screenshots/student_details.png)
