
# Journaling App

## Overview
The **Journaling App** is an Android application that allows users to add, update, view, and delete journal entries. Users can record their thoughts or memories and view them in a clean, user-friendly interface. This app follows material design principles and has been tested with unit tests.

## Features
- Add, edit, and delete journal entries.
- View an overview of all journal entries.
- Detailed view of each entry, truncated content in list view.
- Supports modern Android practices (e.g., RecyclerView, AppCompatActivity).
- Includes unit testing for core functionality.

## Prerequisites
Before testing, building, or deploying the app, ensure that you have the following installed:
- [Android Studio](https://developer.android.com/studio) (latest version recommended)
- [Gradle](https://gradle.org/) (Android Studio handles this automatically, but ensure it is properly configured)
- A minimum Android SDK version of 21 (Lollipop)
- A target SDK version of 34 (Android 13)
- Java SDK 11 or higher

## Getting Started
To get started with the project, follow these steps:

### 1. Clone the Repository
Clone the GitHub repository to your local machine:
```bash
git clone https://github.com/duersjefen/journaling-app.git
```

### 2. Open in Android Studio
1. Open **Android Studio**.
2. Click on **File > Open**.
3. Select the root folder of the cloned repository.
4. Android Studio will automatically build the project. If any errors appear, click **Sync Now** if required.

### 3. Build the Project
- After syncing, go to **Build > Make Project** or click the hammer icon in Android Studio. This will compile the code and check for any build issues.
- Ensure that the required libraries and dependencies (such as RecyclerView) are downloaded and included in the build.

### 4. Running the App on an Emulator or Device
- Connect an Android device or configure an emulator (Android Studio includes built-in emulator support).
- Go to **Run > Run 'app'** or press the green play button. Android Studio will build and install the app on the selected device or emulator.
- Make sure the device or emulator is running an Android version supported by the app (API Level 21 or higher).

### 5. Running Unit Tests
This project includes unit tests for key functionality (like adding, editing, and deleting journal entries). To run the tests:
1. In **Android Studio**, navigate to the **test** directory (`app/src/test/java/com/example/journalingapp/`).
2. Right-click on a test class (e.g., `JournalAdapterTest` or `MainActivityTest`) and select **Run 'TestName'**.
3. Ensure that all tests pass.

To run all tests:
- Go to the terminal in Android Studio and run:
  ```bash
  ./gradlew test
  ```

### 6. Debugging
If you encounter any issues during testing or running the app:
- Use the **Logcat** tool in Android Studio to view logs and error messages.
- Use **breakpoints** for step-by-step debugging.

## Deployment
To build a signed APK for deployment:
1. In **Android Studio**, go to **Build > Generate Signed Bundle / APK**.
2. Select **APK** and follow the instructions to create a new signing key if needed.
3. Once the APK is generated, you can distribute it via email, upload it to Google Play, or sideload it onto an Android device.

## Folder Structure
The key components of the project are organized as follows:
```
├── app/src/main/java/com/example/journalingapp/
│   ├── AddEntryActivity.kt       # Handles adding and editing journal entries
│   ├── JournalAdapter.kt         # Adapter for RecyclerView to display journal entries
│   ├── JournalEntry.kt           # Data class representing each journal entry
│   ├── MainActivity.kt           # Main activity with list and button to add entries
│
├── app/src/main/res/layout/      # Layout XML files for app activities and components
│   ├── activity_main.xml         # Layout for main activity
│   ├── activity_add_entry.xml    # Layout for adding/editing entries
│   ├── item_journal_entry.xml    # Layout for individual journal list item
│
├── app/src/test/java/com/example/journalingapp/
│   ├── JournalAdapterTest.kt     # Unit tests for the JournalAdapter
│   ├── MainActivityTest.kt       # Unit tests for MainActivity interactions
│
├── app/build.gradle              # Gradle build configuration
├── settings.gradle               # Gradle settings file
└── README.md                     # Project documentation
```
