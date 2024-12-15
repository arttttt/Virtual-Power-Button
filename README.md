# Virtual Power Button

Virtual Power Button is an Android application that emulates power button functionality for devices with faulty or broken physical power buttons. It provides easy access to screen locking and power menu options through a convenient shortcut.

## Features

- Lock screen functionality
- Access to power menu (power off, restart options)
- Home screen shortcut for quick access
- Material Design 3 UI with dynamic theming support
- Dark/Light theme support
- Android 12+ optimized

## Requirements

- Android 12 or higher (API level 31+)
- Accessibility Service permission
- Shortcut support

## Setup Instructions

1. Launch the app
2. Grant Accessibility Service permission when prompted
    - This is required for power menu functionality
    - The app will redirect you to the Accessibility Settings
    - Find "Virtual Power Button" in the list and enable it
3. Create a shortcut using the "Create Shortcut" button in the app
4. The shortcut will appear on your home screen

## How It Works

The app uses Android's Accessibility Service API to provide power button functionality:
- Screen locking is implemented using `GLOBAL_ACTION_LOCK_SCREEN`
- Power menu is accessed using `GLOBAL_ACTION_POWER_DIALOG`
- Shortcuts are created using Android's ShortcutManager API

## Build Instructions

To build the project:

1. Clone the repository:
```bash
git clone https://github.com/[username]/virtual-power-button.git
```

2. Open the project in Android Studio

3. Build the project:
```bash
./gradlew assembleDebug
```

## Tech Stack

- Kotlin
- Jetpack Compose
- Material Design 3
- ViewModel
- Kotlin Flow
- Android Accessibility Service
- Android Shortcuts API

## Architecture

The app follows MVVM architecture pattern and uses:
- ViewModel for business logic
- StateFlow for state management
- Compose for declarative UI
- Dependency Injection for better testability

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or suggestions, please open an issue in the GitHub repository.

---
Made with ❤️ by arttttt