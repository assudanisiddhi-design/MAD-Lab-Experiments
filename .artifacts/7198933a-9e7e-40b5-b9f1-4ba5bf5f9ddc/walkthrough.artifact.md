# Walkthrough - Experiment 5: Notifications in Android

Developed an Android application to demonstrate system notifications, featuring channel management and permission handling for modern Android versions.

## Changes Made

### Notification Implementation
- **NotificationHelper**: Created a utility class to handle **Notification Channel** registration and notification delivery.
- **Permission Handling**: Implemented the `POST_NOTIFICATIONS` runtime permission request for Android 13+.
- **Dashboard Trigger**: Added a dedicated "Trigger Notification" button to the `DashboardActivity`.

### UI & Architecture
- **Multi-Activity Design**: Maintained the robust multi-activity flow with Intent data passing.
- **Modern Look**: Used Material 3 design principles and Jetpack Compose to ensure a professional and responsive UI.

### Documentation
- Created a detailed [Exp-5/README.md](file:///D:/MAD Lab Experiments/Exp-5/README.md).
- Captured 3 screenshots demonstrating the login, dashboard, and student details with notification support.
- Updated the repository root [README.md](file:///D:/MAD Lab Experiments/README.md).

## Verification Results

### Build & Sync
- [x] Gradle Sync: Successful
- [x] Build Task (`:Exp-5:app:assembleDebug`): Successful

### Functional Testing
- [x] **Authentication**: Data (Name/USN) correctly flows through the app.
- [x] **Notifications**: Verified that clicking "Trigger Notification" generates a system alert.
- [x] **Permissions**: Confirmed that the app requests permission on Android 13+ devices.

## Screenshots
- [Login Screen](file:///D:/MAD Lab Experiments/Exp-5/screenshots/login.png)
- [Dashboard & Trigger](file:///D:/MAD Lab Experiments/Exp-5/screenshots/dashboard.png)
- [Profile Data Verification](file:///D:/MAD Lab Experiments/Exp-5/screenshots/student_details.png)
