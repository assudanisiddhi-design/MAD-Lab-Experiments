# Implementation Plan - Experiment 5: Notifications in Android

Develop a modern Android application in the `Exp-5` folder that demonstrates the creation and display of system notifications, while maintaining the authentication and dashboard flow from previous experiments.

## User Review Required

> [!IMPORTANT]
> - I will implement a **Notification Manager** to handle the creation of a Notification Channel (required for Android 8.0+) and the delivery of notifications.
> - I will add a **"Trigger Notification"** button to the Dashboard.
> - For Android 13+ (API 33), I will implement the runtime permission request for `POST_NOTIFICATIONS`.
> - The notification will include a title, message, and a professional icon.

## Proposed Changes

### Project Configuration

#### [MODIFY] [settings.gradle.kts](file:///D:/MAD Lab Experiments/settings.gradle.kts)
Include the new `:Exp-5:app` module.

#### [NEW] [Exp-5/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-5/build.gradle.kts)
Root build script for Exp-5.

#### [NEW] [Exp-5/app/build.gradle.kts](file:///D:/MAD Lab Experiments/Exp-5/app/build.gradle.kts)
Module build script with Compose and Notification support.

#### [NEW] [AndroidManifest.xml](file:///D:/MAD Lab Experiments/Exp-5/app/src/main/AndroidManifest.xml)
- Register `LoginActivity`, `DashboardActivity`, `HomeActivity`, `StudentDetailsActivity`, `AccountActivity`.
- Add `<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />`.

### Source Code

#### [NEW] [NotificationHelper.kt](file:///D:/MAD Lab Experiments/Exp-5/app/src/main/java/com/example/notificationapp/NotificationHelper.kt)
Utility class to initialize the Notification Channel and show notifications.

#### [NEW] [Activities](file:///D:/MAD Lab Experiments/Exp-5/app/src/main/java/com/example/notificationapp/)
- `LoginActivity.kt`: Preserved from Exp-4.
- `DashboardActivity.kt`: Updated with a "Show Notification" button and permission handling.
- `HomeActivity.kt`, `StudentDetailsActivity.kt`, `AccountActivity.kt`: Preserved from Exp-4.

## Verification Plan

### Automated Tests
- Run `./gradlew :Exp-5:app:assembleDebug` to verify the build.

### Manual Verification
1. Launch the app and log in.
2. On the Dashboard, click **"Trigger Notification"**.
3. (If Android 13+) Allow the notification permission.
4. Verify that a notification appears in the system tray with the correct content.
5. Capture screenshots of the Dashboard and the triggered notification.
