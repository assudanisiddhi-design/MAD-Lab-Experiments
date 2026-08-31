# Implementation Plan - Experiment 4: Linking Activities using Intents

Develop a modern Android application in the `Exp-4` folder that demonstrates navigating between multiple Activities and passing data using Intents.

## User Review Required

> [!IMPORTANT]
> - Unlike Exp-3 (which used Fragments), Exp-4 will use a **Multi-Activity architecture** to specifically satisfy the requirement of demonstrating **Intents**.
> - I will maintain the **Modern UI** look from Exp-3 using Jetpack Compose within each Activity.
> - Data (Name and USN) will be passed explicitly through `Intent` extras from the Login screen to the Dashboard, and subsequently to the Student Details and Account screens.

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-4:app` module.

#### [NEW] [Exp-4/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-4/build.gradle.kts)
Project-level build script.

#### [NEW] [Exp-4/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-4/app/build.gradle.kts)
Module-level build script with Compose dependencies.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/AndroidManifest.xml)
Register all activities: `LoginActivity`, `DashboardActivity`, `HomeActivity`, `StudentDetailsActivity`, and `AccountActivity`.

### Source Code [NEW]

#### [NEW] [LoginActivity.kt](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/java/com/example/intentapp/LoginActivity.kt)
Entry activity. Validates input and launches `DashboardActivity` with Name and USN extras.

#### [NEW] [DashboardActivity.kt](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/java/com/example/intentapp/DashboardActivity.kt)
Receives User data. Provides navigation to other activities via Buttons + Intents.

#### [NEW] [HomeActivity.kt](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/java/com/example/intentapp/HomeActivity.kt)
Simple activity reached via Intent.

#### [NEW] [StudentDetailsActivity.kt](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/java/com/example/intentapp/StudentDetailsActivity.kt)
Displays Name and USN received via Intent from the Dashboard.

#### [NEW] [AccountActivity.kt](file:///D:/MAD Lab Experiments/Exp-4/app/src/main/java/com/example/intentapp/AccountActivity.kt)
Displays profile info received via Intent.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-4:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app and enter details.
2. Verify that clicking "Login" starts a new Activity (`DashboardActivity`).
3. Verify that navigation to "Student Details" and "Account" works correctly using Intents.
4. Confirm that Name and USN are displayed correctly in the final screens.
5. Capture screenshots for documentation.
