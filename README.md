# EasyJuice

## Description:
  Save some text on your phone to send it via text later with a press of a button.

## Features:
1.  A user is able to store a string of text locally to Android
2.  A user is able to open the app, enter a phone number,  and press send to send the string in a text message

## Specifications:

### Storage:
>    1. A string entered on the setup screen is saved locally when the save button is pressed - Storage.URLStorageTest

### Navigation
>    1. After saving the string locally, the app is directed to the main screen - Navigation.MainScreenTest
>    2. The Option item in the optionsmenubar on the main screen directs the user to the setup screen - Navigation.MainScreenTest
>    3. If a string is stored, the main screen is the one that is shown first - Navigation.ScreenInitializationTest
>    4. If a string is not stored, the setup screen is the one that is shown first - Navigation.ScreenInitializationTest

----
### Input Validation
>    1. The input must be ten consecutive numbers - Validation.PhoneNumValidationTest
