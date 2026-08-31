# Implementation Plan - Experiment 3: Fragment-based Flexible UI with Modern Design

Develop a modern Android application in the `Exp-3` folder that utilizes a Single-Activity architecture with Fragments to create a flexible UI.

## User Review Required

> [!IMPORTANT]
> - I will use a **Single-Activity architecture** where `MainActivity` hosts different Fragments.
> - Each Fragment will use **Jetpack Compose** for its UI to ensure a "modern look".
> - Navigation between Fragments will be managed using the **Jetpack Navigation Component**.
> - A **Shared ViewModel** will be used to pass authentication data (Name and USN) across Fragments.

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-3:app` module.

#### [NEW] [Exp-3/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-3/build.gradle.kts)
Project-level build script for Exp-3.

#### [NEW] [Exp-3/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-3/app/build.gradle.kts)
Module-level build script with Compose and Navigation dependencies.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-3/app/src/main/AndroidManifest.xml)
Register `MainActivity`.

### Source Code

#### [NEW] [UserViewModel.kt](file:///D:/MAD Lab Experiments/Exp-3/app/src/main/java/com/example/fragmentapp/UserViewModel.kt)
Shared ViewModel to store Name and USN.

#### [NEW] [MainActivity.kt](file:///D:/MAD Lab Experiments/Exp-3/app/src/main/java/com/example/fragmentapp/MainActivity.kt)
The entry point activity that hosts the Fragment Container View.

#### [NEW] [Fragments](file:///D:/MAD Lab Experiments/Exp-3/app/src/main/java/com/example/fragmentapp/ui/)
- `LoginFragment.kt`: Modern auth screen.
- `DashboardFragment.kt`: Navigation hub (Home, Student Details, Account).
- `HomeFragment.kt`: Modern placeholder.
- `StudentDetailsFragment.kt`: Displays Name and USN with enhanced styling.
- `AccountFragment.kt`: User profile screen.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-3:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app.
2. Enter details in `LoginFragment`.
3. Verify navigation to `DashboardFragment` using Fragment transactions/Navigation component.
4. Check "Student Details" screen for correctly passed data and modern UI.
5. Verify transitions between all screens.
