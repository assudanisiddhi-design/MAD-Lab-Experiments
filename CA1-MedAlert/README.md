# CA1: MedAlert - Medicine Reminder & Tracking Application

## Description
MedAlert is a comprehensive Android application designed to help users manage their medication schedules, track dosages, set reminder alerts, and view detailed medication profiles. Developed as part of Continuous Assessment 1 (CA1), this project demonstrates core Android development concepts including Activity & Fragment lifecycles, RecyclerView with custom adapters, Parcelable data transfer across intents, input validation, and local system notifications.

## Concept & Technology
- **Android Studio & Kotlin**: Primary development environment and language for Android application development.
- **RecyclerView & Custom Adapter**: `MedicineAdapter` efficiently renders list items in `MedicineDashboardActivity` using `ItemMedicineBinding`.
- **Fragment & FragmentManager**: `AddMedicineFragment` is dynamically hosted inside the dashboard with smooth enter/exit slide animations.
- **View Binding**: Clean, null-safe UI binding across activities, fragments, and list items.
- **Parcelable Interface**: `Medicine` data model implements `@Parcelize` for passing structured object data via `Intent` extras.
- **NotificationChannel & NotificationManagerCompat**: `NotificationHelper` handles system notification channel creation (`POST_NOTIFICATIONS`) and posts reminder alerts when a medicine is scheduled.
- **Activity & Fragment Lifecycle Management**: Implements and logs callbacks (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`, `onDestroyView`, `onDetach`) for robust lifecycle handling.
- **Material Design UI**: Utilizes Material 3 design elements including FloatingActionButton (FAB), CardView layouts, RadioGroups, CheckBoxes, and clean color schemes.

## Scenario
1. **Dashboard Home**: On launch, the user is greeted with a personalized header ("Good Morning, Siddhi") and an interactive dashboard. If no items exist, an empty state illustration is displayed.
2. **Add Medication**: Clicking the Floating Action Button (`+`) smoothly slides in the `AddMedicineFragment`. The user fills in medication details (Name, Dosage, Type, Frequency, and Reminder toggle).
3. **Notification & Validation**: The form validates required inputs. If reminders are enabled, a system notification ("MedAlert Reminder") is automatically issued.
4. **Schedule List**: Upon saving, the new medication is added to the list, hiding the empty state layout and updating the RecyclerView.
5. **Medication Details**: Tapping any card in the list transfers the `Medicine` object via Parcelable intent to `MedicineDetailsActivity`, displaying full medication details.

## Project Structure
```text
CA1-MedAlert/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/medalert/
│   │   │   │   ├── AddMedicineFragment.kt (Add Medicine Fragment & Form Validation)
│   │   │   │   ├── Medicine.kt (Parcelable Data Model)
│   │   │   │   ├── MedicineAdapter.kt (RecyclerView Adapter & ViewHolder)
│   │   │   │   ├── MedicineDashboardActivity.kt (Main Dashboard & RecyclerView Host)
│   │   │   │   ├── MedicineDetailsActivity.kt (Detailed View Activity)
│   │   │   │   └── NotificationHelper.kt (System Notification Manager)
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_medicine_dashboard.xml
│   │   │   │   │   ├── activity_medicine_details.xml
│   │   │   │   │   ├── fragment_add_medicine.xml
│   │   │   │   │   └── item_medicine.xml
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml
│   │   │   │       ├── strings.xml
│   │   │   │       └── themes.xml
│   │   │   └── AndroidManifest.xml (Manifest permissions & activity declarations)
│   └── build.gradle.kts (Module Build Configuration)
└── build.gradle.kts (Sub-project Build Script)
```

## Features & Key Functionalities
1. **Medication Dashboard**: Interactive list displaying active medicines with custom CardView items and empty state handling.
2. **Dynamic Fragment Host**: Smooth fragment transactions with custom slide and fade animations.
3. **Notification System**: Built-in Android `NotificationChannel` support for issuing instant reminder alerts upon adding medication.
4. **Parcelable Intent Passing**: Explicit intents carrying parcelized `Medicine` objects to open comprehensive detail screens.
5. **Lifecycle Logging**: Full Logcat tracing across Activity and Fragment lifecycle events (`Log.d("MedAlert", ...)`).

## Test Cases & Verification

### Test Case 1: Initial Launch & Empty State
- **Input**: Launch `MedicineDashboardActivity` on device/emulator.
- **Expected Output**: Personal greeting ("Good Morning, Siddhi") and empty state layout ("No medicines scheduled yet") are displayed with the FAB active.
- **Result**: Pass

### Test Case 2: Add Medication & System Notification
- **Input**: Click FAB `+`, enter Name: `"Paracetamol"`, Dosage: `"500 mg"`, Type: `"Tablet"`, Frequency: `"Twice Daily"`, Check `"Enable Reminder Alert"`, and click Save.
- **Expected Output**: Input validation passes, system posts notification `"Reminder created for Paracetamol"`, form closes, and the new item is listed on the dashboard.
- **Result**: Pass

### Test Case 3: Medication Details Screen
- **Input**: Tap on the "Paracetamol" item in the dashboard list.
- **Expected Output**: Navigates to `MedicineDetailsActivity` showing Name, Dosage, Type, Frequency, and Reminder Status ("Enabled").
- **Result**: Pass
