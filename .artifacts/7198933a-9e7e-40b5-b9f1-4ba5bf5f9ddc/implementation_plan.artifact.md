# Implementation Plan - Experiment 2: Activity Lifecycle Application

Develop an Android application in the `Exp-2` folder to demonstrate the Activity Lifecycle. The app will include an authentication page, a dashboard, and specific screens for Home, Account, and a detailed Activity Lifecycle tracker.

## User Review Required

> [!IMPORTANT]
> - I will implement the application using multiple Activities to clearly demonstrate the transition between lifecycle states.
> - The "Activity Details" screen will explicitly override and log all major lifecycle methods (`onCreate`, `onStart`, etc.) to both Logcat and the UI.
> - Data (Name and USN) will be passed from the Login screen to the Dashboard and Account screens using Intents.

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-2:app` module.

#### [NEW] [Exp-2/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-2/build.gradle.kts)
Project-level build script for Exp-2.

#### [NEW] [Exp-2/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-2/app/build.gradle.kts)
Module-level build script with Compose dependencies.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/AndroidManifest.xml)
Register all new Activities (`LoginActivity`, `DashboardActivity`, `HomeActivity`, `LifecycleActivity`, `AccountActivity`).

### Source Code

#### [NEW] [LoginActivity.kt](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/java/com/example/lifecycleapp/LoginActivity.kt)
Authentication page requiring "Name" and "USN". On success, navigates to `DashboardActivity`.

#### [NEW] [DashboardActivity.kt](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/java/com/example/lifecycleapp/DashboardActivity.kt)
Dashboard with three navigation buttons: Home, Activity Details, and Account.

#### [NEW] [HomeActivity.kt](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/java/com/example/lifecycleapp/HomeActivity.kt)
A simple landing page for the "Home" option.

#### [NEW] [LifecycleActivity.kt](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/java/com/example/lifecycleapp/LifecycleActivity.kt)
The core of Experiment 2. It will override all lifecycle methods and update a UI list or display Toasts to show the current state transitions.

#### [NEW] [AccountActivity.kt](file:///D:/MAD Lab Experiments/Exp-2/app/src/main/java/com/example/lifecycleapp/AccountActivity.kt)
Displays the user's "Name" and "USN" captured during login.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-2:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app on the emulator.
2. Enter "Siddhi Assudani" and "25MCAR0199" in the Login screen.
3. Navigate to the Dashboard.
4. Open "Activity Details" and observe the lifecycle logs in Logcat and on-screen messages.
5. Test transitions (rotating the device, going to home, returning to the app) to see lifecycle changes.
6. Verify the Account screen shows the correct details.
