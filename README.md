# MVVM + Clean Architecture while utilizing Gradle Version Catalogs for Threat Detection App

### Gradle version catalogs streamlines the dependencies while helping out with plugin managment across modules within the project

### Project Structure
- Presentation Layer: 
- -- UI -- Compose
- -- ViewModel
- Data Layer:
- -- Entity/Model
- -- Data Source
- -- -- Remote/Local
- -- -- -- Repository
- -- -- -- Service
- -- -- -- Exceptions
- Domain Layer:
- -- UseCase
- Dependency Injection
- -- Module
# Setup Guide

Follow these steps to set up the Android Studio project for development.

## Prerequisites
Before you begin, ensure you have the following installed:
- [Android Studio](https://developer.android.com/studio) (Android Studio Ladybug Feature Drop | 2024.2.2)
- Java Development Kit (JDK 21.0.4)
- Git (for version control)

## Cloning the Repository
1. Open **Terminal** or **Command Prompt**.
2. Run the following command to clone the repository:
   ```bash
   git clone https://github.com/meshileya/ThreatDetectionDemo
   ```

## Opening the Project in Android Studio
1. Launch **Android Studio**.
2. Click on **Open an Existing Project**.
3. Select the cloned repository folder and click **OK**.
4. Wait for Gradle to sync and build the project.

## Installing Dependencies
- Ensure all dependencies in `build.gradle` are up to date.
- Sync the project with Gradle by clicking **File** → **Sync Project with Gradle Files**.

## Running the Project
1. Connect an **Android device** via USB or launch an **emulator**.
2. Click **Run → Run 'app'** in the top menu.

## Troubleshooting
- If you encounter dependency issues, run:
  ```bash
  ./gradlew build --refresh-dependencies
  ```
- Ensure the correct **JDK version** is selected under **File** → **Project Structure** → **SDK Location**.
