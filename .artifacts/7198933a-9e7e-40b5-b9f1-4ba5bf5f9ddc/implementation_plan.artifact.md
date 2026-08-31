# Implementation Plan - Experiment 6: Basic Android Views Showcase

Develop a modern Android application in the `Exp-6` folder that demonstrates the use of basic Android UI components (`TextView`, `EditText`, `Button`, `ImageButton`, `CheckBox`, `ToggleButton`, `RadioButton`, and `RadioGroup`) while maintaining the notification and dashboard features from previous experiments.

## User Review Required

> [!IMPORTANT]
> - I will implement a new activity, `BasicViewsActivity`, which will serve as a **"Student Survey Form"**.
> - This form will utilize all requested basic views to collect additional student information (e.g., gender, interests, notifications preference).
> - I will keep the **Modern UI** look by using Jetpack Compose equivalents for these basic views, ensuring they are presented in a professional, card-based layout.
> - The **Notification Trigger** from Exp-5 will be preserved on the Dashboard.

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-6:app` module.

#### [NEW] [Exp-6/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-6/build.gradle.kts)
Project build script.

#### [NEW] [Exp-6/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-6/app/build.gradle.kts)
Module build script with standard dependencies.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-6/app/src/main/AndroidManifest.xml)
Register all activities including the new `BasicViewsActivity`.

### Source Code

#### [NEW] [NotificationHelper.kt](file:///D:/MAD Lab Experiments/Exp-6/app/src/main/java/com/example/basicviewsapp/NotificationHelper.kt)
Ported from Exp-5 to maintain notification functionality.

#### [NEW] [BasicViewsActivity.kt](file:///D:/MAD Lab Experiments/Exp-6/app/src/main/java/com/example/basicviewsapp/BasicViewsActivity.kt)
The core of Experiment 6. A form containing:
- **TextView**: Section headers and labels.
- **EditText**: Multi-line bio or address input.
- **Button**: Submit/Save button.
- **ImageButton**: A "Profile Info" icon button.
- **CheckBox**: List of interests (e.g., Android, AI, Web).
- **ToggleButton**: Toggle for "App Visibility" or "Dark Mode".
- **RadioButton & RadioGroup**: Selection for "Department" or "Gender".

#### [NEW] [DashboardActivity.kt](file:///D:/MAD Lab Experiments/Exp-6/app/src/main/java/com/example/basicviewsapp/DashboardActivity.kt)
Updated dashboard with a button for "Basic Views Showcase".

#### [NEW] Other Activities
- `LoginActivity.kt`, `HomeActivity.kt`, `AccountActivity.kt`, `StudentDetailsActivity.kt`: Ported and updated with the new package name.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-6:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app and log in.
2. Navigate to "Basic Views Showcase" from the Dashboard.
3. Interact with all UI elements (Type text, check boxes, toggle switches, select radio buttons).
4. Click the ImageButton and verify its interaction.
5. Trigger a notification to ensure Experiment 5 features still work.
6. Verify "Student Details" still shows the Name/USN from login.
