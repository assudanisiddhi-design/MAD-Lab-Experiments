# Experiment 4: Linking Activities using Intents

## Description
This experiment demonstrates the mechanism of **Intents** in Android to link multiple activities and pass data between them. Unlike modular fragments, this application uses distinct Activities for each screen, illustrating the full activity lifecycle transitions during navigation.

## Concept & Technology
- **Explicit Intent**: Used to launch a specific activity class (e.g., `LoginActivity` -> `DashboardActivity`).
- **Intent Extras**: A bundle of key-value pairs used to pass data (Name, USN) across the activity boundary.
- **Jetpack Compose**: Powers the modern UI within each separate Activity.
- **Activity Stack**: Managing the back-stack as users navigate through the app.

## Scenario
The user logs in via the `LoginActivity`. Upon validation, an Intent is created with the student's Name and USN as extras. The `DashboardActivity` receives this data and allows the user to navigate further to "Student Details" or "Account" activities, each time passing the relevant information through new Intents.

## Project Structure
```text
Exp-4/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/intentapp/
│   │   │   │   ├── LoginActivity.kt (Starts Intent)
│   │   │   │   ├── DashboardActivity.kt (Main Hub)
│   │   │   │   ├── HomeActivity.kt
│   │   │   │   ├── StudentDetailsActivity.kt (Displays Intent Data)
│   │   │   │   └── AccountActivity.kt
│   │   │   └── AndroidManifest.xml (Activity Registry)
│   └── build.gradle.kts
├── screenshots/
│   ├── login.png
│   ├── dashboard.png
│   └── student_details.png
├── build.gradle.kts
└── settings.gradle.kts
```

## Features & Scenarios
1.  **Activity Transitions**: Demonstrates launching new activities using `startActivity(intent)`.
2.  **Data Persistence via Intents**: Shows how to use `putExtra()` and `getStringExtra()` to move data across the app.
3.  **Modern UI**: Consistently high-quality design using Material 3 components.

## Test Cases & Screenshots

### Test Case 1: Navigation with Data
- **Scenario**: Enter Name and USN in Login screen.
- **Result**: `DashboardActivity` is launched and displays the user's name, proving the Intent successfully carried the data.
![Login](./screenshots/login.png)

### Test Case 2: Multi-Hop Intent Passing
- **Scenario**: Navigate from Dashboard to Student Details.
- **Result**: The `StudentDetailsActivity` correctly displays both Name and USN passed through a secondary intent.
![Dashboard](./screenshots/dashboard.png)

### Test Case 3: Intent Content Verification
- **Scenario**: Verify the "Student Details" screen for accuracy.
- **Result**: Displays "Assudani Siddhi" and "25MCAR0199" retrieved from Intent Extras.
![Student Details](./screenshots/student_details.png)
