# Pill Tracker - Project Overview

Pill Tracker is an Android application designed to help users manage their medication schedules. It allows users to input pill names and define either a fixed hour interval or specific daily times (up to 3) for taking them. The app tracks the history of when each pill was taken and calculates the next scheduled dose.

## Project Structure
- **Model**: `Pill.kt` defines the data structure and next-dose calculation logic.
- **ViewModel**: `PillViewModel.kt` manages state and handles adding/taking pills.
- **Fragments**: 
    - `FirstFragment`: Displays the list of all tracked pills with a "Take" button.
    - `SecondFragment`: Form for adding new pills with scheduling options.
    - `HistoryFragment`: Shows the chronological history of doses for a specific pill.
- **Navigation**: Managed via `nav_graph.xml`.
- **UI**: Built using Material 3 components and ViewBinding.

## Build and Test Commands
This project uses Gradle.

- **Build**: `./gradlew assembleDebug`
- **Clean**: `./gradlew clean`
- **Run Unit Tests**: `./gradlew test`
- **Run Instrumentation Tests**: `./gradlew connectedAndroidTest`
- **Lint**: `./gradlew lint`

## Code Style Guidelines
- **Kotlin**: Follow official Kotlin style guides. Use KTX extensions where applicable.
- **Architecture**: MVVM pattern with `LiveData` and `ViewModel`.
- **ViewBinding**: Always use ViewBinding instead of `findViewById`.
- **Resources**: All strings should be in `strings.xml`. Use Material 3 themes and components.
- **Concurrency**: Use Coroutines if background tasks are added (e.g., Database/Preferences).

## Testing Instructions
- **Manual Testing**:
    1. Open the app and verify the empty state message.
    2. Click the FAB (+) to add a pill.
    3. Test "Interval" mode: Enter "Ibuprofen" and "4" hours. Verify it appears in the list.
    4. Test "Daily Times" mode: Enter "Vitamin D" and select specific times.
    5. Click "Take" on a pill and verify the "Next dose" time updates correctly.
    6. Click on a pill name to view its history and verify timestamps.
- **Automated Testing**: Ensure new logic in `Pill.nextTime` is covered by unit tests in `PillTest.kt`.

## Security Considerations
- **Data Privacy**: Medication data is currently stored in-memory (and resets on app kill). If persistence is added (e.g., Room), ensure data is stored locally and not leaked via logs.
- **Input Validation**: Ensure pill names are sanitized and intervals/times are within logical bounds.
- **Permissions**: The app currently requires no special permissions. If notifications are added for reminders, request `POST_NOTIFICATIONS` on API 33+.
