# Monefy App - Mobile Test Automation

Automated testing framework for the Monefy Android application using Appium and Java.

# Prerequisites

    • Java 17 or higher
    • Maven 3.6+
    • Android SDK (with platform-tools in PATH)
    • Appium Server 2 or higher
    • Android Emulator or physical device
    • Monefy APK (placed in 'app/monefy.apk')


## Setup

    1. Install Dependencies

        Make sure you have the Android SDK and Appium installed:

        ### Install Appium ###
        • npm install -g appium

        ### Install UIAutomator2 driver ###
        • appium driver install uiautomator2

        ### Verify installation ###
        • appium driver list


    2. Configure Environment

        ### Set up Android SDK path: ###

        ### Windows ###
        • set ANDROID_HOME=C:\Users\YourName\AppData\Local\Android\Sdk

        ### Mac/Linux ###
        • export ANDROID_HOME=/Users/yourname/Library/Android/sdk


    3. Start Emulator
        ### List available emulators ###
        • emulator -list-avds

        ### Start emulator ###
        • emulator -avd Pixel_5_API_30
        • Or, Start the emulator in the Android Studio

        ### 4. Clone and Build ###

        • git clone <repository>
        • cd monefy-app-automation
        • mvn clean compile


## How to Run Tests

    ### Start Appium Server

        ### Open a terminal and run: ###
        • appium
        ### Keep this terminal running. ###

    ### Run All Tests ###
        • mvn clean test

    ### Run Specific Test Class ###

        ### Run expense tests only ###
        • mvn test -Dtest=ExpenseTests

        ### Run income tests only ###
        • mvn test -Dtest=IncomeTests

        ### Run balance tests only ###
        • mvn test -Dtest=BalanceTests


        ### Run Single Test ###
        • mvn test -Dtest=ExpenseTests#{testname}

    ### View Test Report ###

        ### After tests complete, open the HTML report: ###
        • Location: 'TestReport.html file in the project'
        • Double-Click on the HTML file


## Tech Stack & Approach

    ### Why This Stack? ###

       • Appium + Java + TestNG - Industry standard for mobile automation with strong community support and extensive documentation.

       • Page Object Model - Keeps tests clean and maintainable by separating UI interactions from test logic.

       • ExtentReports - Generates professional HTML reports with screenshots for failures.

    ### Locator Strategy ###

        ### I prioritize stable locators to ensure tests remain reliable: ###

        1. Resource-id (most stable) - 'By.id("com.monefy.app.lite:id/expense_button")'
        2. Text-based XPath - 'By.xpath("//*[@text='Food']")'
        3. Accessibility labels - 'By.xpath("//*[@content-desc='Add Expense']")'

        ### This approach ensures tests work across different app versions and devices. ###

## Test Coverage

    • Expense Management - Add expenses in different categories
    • Income Management - Record income from various sources
    • Balance Calculation - Verify Balance Amount (income - expense = balance)
    • Edge Cases - Empty notes, rapid transactions, boundary values
    • Total: 12 automated tests


## Troubleshooting

    ### Tests fail with "Session not created" ###
        • Make sure Appium server is running ('appium')
        • Check emulator is running ('adb devices')

    ### "APK not found" error ###
        • Place 'monefy.apk' in the 'app/' folder(Already placed inside the app folder)
        • Check the path in TestSetup.java

## Future Improvements

    • Add tests for transaction editing and deletion
    • Test multiple accounts
    • Implement parallel test execution
    • Add CI/CD integration

## Author
Mayank Anand
