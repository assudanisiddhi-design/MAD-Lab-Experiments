# Walkthrough - Experiment 3: Fragment-based Flexible UI with Modern Design

Developed a modern, single-activity Android application using Fragments to create a flexible and responsive UI.

## Changes Made

### Architecture & UI
- **Single-Activity Design**: Implemented `MainActivity` as the primary host for all app screens.
- **Fragment Management**: Used `supportFragmentManager` to swap between modular UI components (`LoginFragment`, `DashboardFragment`, etc.).
- **Modern Look**: Integrated **Jetpack Compose** within each Fragment to deliver a high-quality, modern aesthetic using Material 3.
- **Enhanced Dashboard**: Replaced the "Activity Details" option with **"Student Details"** as requested.

### Data Management
- **Shared ViewModel**: Implemented `UserViewModel` to ensure seamless data sharing (Name and USN) across different fragments without complex bundle passing.

### Documentation
- Created a comprehensive [Exp-3/README.md](file:///D:/MAD Lab Experiments/Exp-3/README.md) with details on the fragment-based approach and UI enhancements.
- Captured and stored 3 key screenshots in `Exp-3/screenshots/` demonstrating the login, dashboard, and student details.

## Verification Results

### Build & Sync
- [x] Gradle Sync: Successful
- [x] Build Task (`:Exp-3:app:assembleDebug`): Successful

### Functional Testing
- [x] **Authentication**: Verified that user input in `LoginFragment` is correctly saved.
- [x] **Navigation**: Confirmed smooth transitions between Fragments using the dashboard.
- [x] **Data Persistence**: Verified that "Student Details" correctly displays the data entered during the login phase.

## Screenshots
- [Modern Login](file:///D:/MAD Lab Experiments/Exp-3/screenshots/login.png)
- [Modern Dashboard](file:///D:/MAD Lab Experiments/Exp-3/screenshots/dashboard.png)
- [Student Details](file:///D:/MAD Lab Experiments/Exp-3/screenshots/student_details.png)
