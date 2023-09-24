# Custom Calendar Android App

![CalendarTest Logo](/app_logo.png)

CalendarTest is a simple Android app that allows users to view a monthly calendar with the ability to mark certain days as holidays. It uses the Android Data Binding library and RecyclerView to create a dynamic and interactive calendar view.

## Features

- Display a monthly calendar with days of the week.
- Navigate between months.
- Mark specific days as holidays.
- Customize the appearance of holidays.

## Screenshots

![Screenshot 1](/screenshots/screenshot1.png)

![Screenshot 2](/screenshots/screenshot2.png)

## Installation

To use Custom Calendar in your Android project, follow these steps:

1. Clone this repository or download the ZIP file.

2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or physical device.

## Usage

Custom Calendar is designed as a sample project, but you can use its components as a starting point for your own calendar-related applications. Here's an overview of the key components:

### `MainActivity`

This is the main activity that hosts the calendar. It initializes the calendar view, handles navigation between months, and defines off-days (holidays) for demonstration purposes.

### `CalendarAdapter`

The `CalendarAdapter` is responsible for populating the RecyclerView with calendar items. It also customizes the appearance of days, including marking holidays and highlighting the current day.

### Layouts

The XML layout files (`activity_main.xml`, `item_calendar.xml`, and `month_header.xml`) define the UI components used in the app. You can customize these layouts to match your design requirements.

### Data Binding

Data Binding is used to bind data between the UI elements and the underlying data model. The XML layouts and the `MainActivity` class demonstrate how to use Data Binding for efficient UI updates.

## Contributing

If you'd like to contribute to this project, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your changes: `git checkout -b feature/your-feature-name`.
3. Make your changes and commit them: `git commit -m "Add your feature"`.
4. Push to your fork: `git push origin feature/your-feature-name`.
5. Create a pull request to merge your changes into the main repository.

## License

Custom Calendar is open-source software released under the MIT License. See the [LICENSE](/LICENSE) file for details.

## Contact

If you have any questions or suggestions, feel free to [contact the project maintainer](mailto:batoolalshwiki4555@gmail.com).

Enjoy using Custom Calendar!

