# Pill Tracker

A simple and intuitive Android application to track medication schedules and history.

## How It Was Created

This application was developed iteratively using modern Android development practices. The process involved:
1.  **Architecture Setup**: Implementing the MVVM (Model-View-ViewModel) pattern to separate business logic from UI.
2.  **Navigation**: Utilizing the Jetpack Navigation Component to manage transitions between the pill list, the addition form, and the history view.
3.  **Dynamic Scheduling Logic**: Designing a flexible `Pill` model that handles both interval-based (e.g., every 8 hours) and daily-scheduled (up to 3 specific times per day) medications.
4.  **UI Design**: Leveraging Material 3 components and `RecyclerView` for a clean, responsive list-based interface.
5.  **State Management**: Using `LiveData` and `ViewModel` to ensure UI state remains consistent across configuration changes and shared between fragments.

## Features

- **Pill Management**: Add pills with custom names.
- **Flexible Scheduling**:
    - **Interval Mode**: Set a fixed number of hours between doses.
    - **Daily Mode**: Set up to 3 specific times per day to take a pill.
- **Real-time Tracking**: A "Take" button on the main list to record doses immediately.
- **Next Dose Calculation**: Automatically calculates and displays the next scheduled time based on the chosen schedule type and last taken dose.
- **History Log**: View a full timestamped history for every dose of a specific pill.

## TODO List

- [ ] **Data Persistence**: Implement a Room database to save pill data and history permanently (currently in-memory).
- [ ] **Notifications**: Add a background service or WorkManager to trigger reminders/notifications when a dose is due.
- [ ] **Edit/Delete**: Add the ability to modify pill schedules or remove medications from the list.
- [ ] **Time Selection Improvement**: Allow more than 3 daily doses for complex medication regimes.
- [ ] **Visual Indicators**: Highlight pills that are overdue for a dose in red or with an icon.
- [ ] **Export History**: Allow users to export their pill-taking history to a PDF or CSV for their doctor.
- [ ] **Unit Testing**: Add comprehensive unit tests for the `nextTime` calculation logic in `Pill.kt`.

## Getting Started

1.  Clone the repository.
2.  Open in Android Studio.
3.  Build and run on an emulator or physical device (API 34+).
