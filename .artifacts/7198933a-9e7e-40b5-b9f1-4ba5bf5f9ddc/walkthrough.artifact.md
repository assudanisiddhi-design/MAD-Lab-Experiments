# Walkthrough - Experiment 6: Basic Android Views Showcase

Developed an Android application that provides a complete showcase of basic UI components (`TextView`, `EditText`, `Button`, `ImageButton`, `CheckBox`, `ToggleButton`, `RadioButton`, and `RadioGroup`) in a modern "Student Survey Form".

## Changes Made

### Basic Views Implementation
- **BasicViewsActivity**: Created a new activity containing a comprehensive form:
    - **Text Components**: Used for headers and labeling.
    - **Input Fields**: Implemented `OutlinedTextField` for multi-line bio entry.
    - **Selection Components**: Added `Checkbox` for interest selection and `RadioButton` group for department selection.
    - **Toggle Components**: Integrated a modern `Switch` for notification preferences.
    - **Interactive Buttons**: Combined standard `Button` for submission and `IconButton` for contextual information.

### UI & UX Enhancements
- **Jetpack Compose**: Used for the entire view showcase to ensure elements align with the modern look of previous experiments.
- **Form Layout**: Organized all elements in a scrollable column for better accessibility and readability.
- **User Feedback**: Integrated `Toast` messages to provide immediate feedback on interactions (ImageButton click and Form submission).

### Documentation & Assets
- Created a detailed [Exp-6/README.md](file:///D:/MAD Lab Experiments/Exp-6/README.md).
- Captured 3 screenshots demonstrating the View Showcase, the updated Dashboard, and data persistence.
- Updated the Root [README.md](file:///D:/MAD Lab Experiments/README.md).

## Verification Results

### Build & Sync
- [x] Gradle Sync: Successful
- [x] Build Task (`:Exp-6:app:assembleDebug`): Successful

### Functional Testing
- [x] **Component Interaction**: Verified that all basic views respond to user input and maintain state.
- [x] **Seamless Integration**: Confirmed that the new showcase integrates into the existing dashboard and authentication flow.

## Screenshots
- [View Showcase Form](file:///D:/MAD Lab Experiments/Exp-6/screenshots/showcase.png)
- [Updated Dashboard](file:///D:/MAD Lab Experiments/Exp-6/screenshots/dashboard.png)
- [Student Profile Verification](file:///D:/MAD Lab Experiments/Exp-6/screenshots/student_details.png)
