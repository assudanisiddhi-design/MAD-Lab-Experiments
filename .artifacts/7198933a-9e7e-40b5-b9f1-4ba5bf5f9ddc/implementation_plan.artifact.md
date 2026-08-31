# Implementation Plan - Experiment 7: Creating an Adaptive Application with ListView and ImageView

Develop a modern Android application in the `Exp-7` folder that demonstrates an **Adaptive UI** using `ListView` (implemented via `LazyColumn`) and `ImageView` components, while maintaining the authentication and dashboard flow from previous experiments.

## User Review Required

> [!IMPORTANT]
> - I will implement a new activity, `AdaptiveListActivity`, which will display a **"Course Curriculum List"**.
> - Each list item will feature an **ImageView** (using a professional vector or placeholder) and text descriptions.
> - **Adaptive Design**: I will ensure the list layout adapts gracefully to different screen orientations, providing a professional look on both portrait and landscape.
> - I will use **Jetpack Compose's `LazyColumn`** as the modern and more efficient successor to the legacy `ListView`, fulfilling the requirement while maintaining the "Modern Look".

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-7:app` module.

#### [NEW] [Exp-7/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-7/build.gradle.kts)
Project build script.

#### [NEW] [Exp-7/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-7/app/build.gradle.kts)
Module build script with standard Compose dependencies.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-7/app/src/main/AndroidManifest.xml)
Register all activities including the new `AdaptiveListActivity`.

### Source Code

#### [NEW] [NotificationHelper.kt](file:///D:/MAD Lab Experiments/Exp-7/app/src/main/java/com/example/adaptivelistapp/NotificationHelper.kt)
Maintains notification functionality.

#### [NEW] [AdaptiveListActivity.kt](file:///D:/MAD Lab Experiments/Exp-7/app/src/main/java/com/example/adaptivelistapp/AdaptiveListActivity.kt)
The core of Experiment 7:
- **LazyColumn**: Implementation of the modern list view.
- **Card Layout**: Each item in the list will be a modern Card.
- **Image Integration**: Using `Icon` or `AsyncImage` for the `ImageView` component.
- **Orientation Support**: Ensuring the UI remains readable and organized when rotated.

#### [NEW] [DashboardActivity.kt](file:///D:/MAD Lab Experiments/Exp-7/app/src/main/java/com/example/adaptivelistapp/DashboardActivity.kt)
Updated with a button for "Adaptive List Showcase".

#### [NEW] Other Activities
- `LoginActivity.kt`, `HomeActivity.kt`, `AccountActivity.kt`, `StudentDetailsActivity.kt`: Ported and updated with the new package name `com.example.adaptivelistapp`.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-7:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app and log in.
2. Navigate to "Adaptive List Showcase" from the Dashboard.
3. Scroll through the list and verify each item has a distinct image and text.
4. Rotate the device to verify the "Adaptive" behavior (layout remains stable and professional).
5. Verify Name/USN persistence in Student Details.
