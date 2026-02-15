package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class MonefyApp {

    private final AndroidDriver driver;

    // Locators of Home Page
    private final By balanceAmount = By.id("com.monefy.app.lite:id/balance_amount");
    private final By incomeAmount = By.id("com.monefy.app.lite:id/income_amount");
    private final By expenseAmount = By.id("com.monefy.app.lite:id/expense_amount");

    public MonefyApp(AndroidDriver driver) {
        this.driver = driver;
        handleOnboardingScreens();
    }

    // ===== APP START SCREEN HANDLER =====
    public void handleOnboardingScreens() {
        System.out.println(" Handling onboarding...");

        tryClickByTextContains("GET STARTED", 3);
        tryClickByTextContains("AMAZING", 3);
        tryClickByTextContains("READY", 3);
        tryClickById("com.monefy.app.lite:id/buttonClose", 3);

        System.out.println(" Onboarding completed\n");
        waitSeconds(5);
    }

    private void tryClickByTextContains(String text, int waitAfterSeconds) {
        try {
            WebElement btn = driver.findElement(By.xpath("//*[contains(@text, '" + text + "')]"));
            btn.click();
            waitSeconds(waitAfterSeconds);
        } catch (Exception ignored) {
        }
    }

    private void tryClickById(String id, int waitAfterSeconds) {
        try {
            driver.findElement(By.id(id)).click();
            waitSeconds(waitAfterSeconds);
        } catch (Exception ignored) {

        }
    }

    // ===== WAIT FOR HOME PAGE =====
    private void waitForHomeScreen() {
        System.out.println(" Waiting for home screen...");

        int attempts = 0;
        while (attempts < 10) {
            try {
                WebElement balance = driver.findElement(balanceAmount);
                if (balance.isDisplayed()) {
                    System.out.println(" Home screen loaded");
                    waitSeconds(3);
                    return;
                }
            } catch (Exception ignored) {
            }

            waitSeconds(2);
            attempts++;
        }

        System.out.println(" Home screen wait completed");
    }

    // ===== ADD EXPENSE =====
    public void addExpense(String category, String amount, String note) {
        System.out.println(" Adding expense: $" + amount + " in " + category);

        System.out.println(" 1. Clicking expense button...");
        boolean clicked = false;

        try {
            driver.findElement(By.id("com.monefy.app.lite:id/expense_button")).click();
            System.out.println(" Clicked using ID");
            clicked = true;
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//*[@text='EXPENSE']")).click();
                System.out.println(" Clicked using text");
                clicked = true;
            } catch (Exception e2) {
                try {
                    driver.findElement(By
                            .xpath("//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/expense_button']"))
                            .click();
                    System.out.println(" Clicked using ImageView");
                    clicked = true;
                } catch (Exception e3) {
                    try {
                        driver.findElement(By
                                .xpath("//android.widget.RelativeLayout[.//android.widget.TextView[@text='EXPENSE']]"))
                                .click();
                        System.out.println(" Clicked using parent layout");
                        clicked = true;
                    } catch (Exception e4) {
                        System.out.println(" Could not click expense button");
                    }
                }
            }
        }

        if (!clicked) {
            throw new RuntimeException("Failed to click expense button");
        }

        waitSeconds(4);

        System.out.println(" 2. Entering amount: $" + amount);
        enterAmount(amount);
        waitSeconds(3);

        System.out.println(" 3. Clicking CHOOSE CATEGORY...");
        clickChooseCategory();
        waitSeconds(3);

        System.out.println(" 4. Selecting category: " + category);
        selectCategory(category);

        System.out.println(" 5. Waiting for transaction to save...");
        waitSeconds(10);

        waitForHomeScreen();
        System.out.println(" Expense added successfully\n");
    }

    // ===== ADD INCOME =====
    public void addIncome(String category, String amount, String note) {
        System.out.println("\n Adding income: $" + amount + " in " + category);

        System.out.println(" 1. Clicking income button...");
        boolean clicked = false;

        try {
            driver.findElement(By.id("com.monefy.app.lite:id/income_button")).click();
            System.out.println(" Clicked using ID");
            clicked = true;
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//*[@text='INCOME']")).click();
                System.out.println(" Clicked using text");
                clicked = true;
            } catch (Exception e2) {
                try {
                    driver.findElement(
                            By.xpath("//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/income_button']"))
                            .click();
                    System.out.println(" Clicked using ImageView");
                    clicked = true;
                } catch (Exception e3) {
                    try {
                        driver.findElement(
                                By.xpath("//android.widget.RelativeLayout[.//android.widget.TextView[@text='INCOME']]"))
                                .click();
                        System.out.println(" Clicked using parent layout");
                        clicked = true;
                    } catch (Exception e4) {
                        System.out.println(" Could not click income button");
                    }
                }
            }
        }

        if (!clicked) {
            throw new RuntimeException("Failed to click income button");
        }

        waitSeconds(4);

        System.out.println(" 2. Entering amount: $" + amount);
        enterAmount(amount);
        waitSeconds(3);

        System.out.println(" 3. Clicking CHOOSE CATEGORY...");
        clickChooseCategory();
        waitSeconds(3);

        System.out.println(" 4. Selecting category: " + category);
        selectCategory(category);

        System.out.println(" 5. Waiting for transaction to save...");
        waitSeconds(10);

        waitForHomeScreen();
        System.out.println(" Income added successfully\n");
    }

    public void addExpenseWithoutNote(String category, String amount) {
        addExpense(category, amount, null);
    }

    public void addIncomeWithoutNote(String category, String amount) {
        addIncome(category, amount, null);
    }

    private void enterAmount(String amount) {
        for (char digit : amount.toCharArray()) {
            try {
                driver.findElement(By.xpath("//*[@text='" + digit + "']")).click();
                waitSeconds(1);
            } catch (Exception e) {
                System.out.println(" Could not click digit: " + digit);
            }
        }
    }

    private void clickChooseCategory() {
        try {
            driver.findElement(By.xpath("//*[@text='CHOOSE CATEGORY']")).click();
            System.out.println(" Clicked CHOOSE CATEGORY");
        } catch (Exception e1) {
            try {
                driver.findElement(By.xpath("//*[contains(@text, 'CHOOSE')]")).click();
                System.out.println(" Clicked using partial text");
            } catch (Exception e2) {
                try {
                    driver.findElement(By.id("com.monefy.app.lite:id/button_amount_container")).click();
                    System.out.println(" Clicked button container");
                } catch (Exception e3) {
                    System.out.println(" CHOOSE CATEGORY not found, continuing anyway...");
                }
            }
        }
    }

    private void selectCategory(String category) {
        System.out.println(" Selecting category: " + category);

        try {
            String categoryText;
            switch (category.toLowerCase()) {
                case "bills":
                    categoryText = "Bills";
                    break;
                case "car":
                    categoryText = "Car";
                    break;
                case "clothes":
                    categoryText = "Clothes";
                    break;
                case "communications":
                    categoryText = "Communications";
                    break;
                case "eating out":
                    categoryText = "Eating out";
                    break;
                case "entertainment":
                    categoryText = "Entertainment";
                    break;
                case "food":
                    categoryText = "Food";
                    break;
                case "gifts":
                    categoryText = "Gifts";
                    break;
                case "health":
                    categoryText = "Health";
                    break;
                case "house":
                    categoryText = "House";
                    break;
                case "pets":
                    categoryText = "Pets";
                    break;
                case "sports":
                    categoryText = "Sports";
                    break;
                case "taxi":
                    categoryText = "Taxi";
                    break;
                case "toiletry":
                    categoryText = "Toiletry";
                    break;
                case "transport":
                    categoryText = "Transport";
                    break;
                case "salary":
                    categoryText = "Salary";
                    break;
                case "deposits":
                    categoryText = "Deposits";
                    break;
                case "savings":
                    categoryText = "Savings";
                    break;

                default:
                    categoryText = category;
                    break;
            }

            driver.findElement(By.xpath("//*[@text='" + categoryText + "']")).click();
            System.out.println(" Selected: " + categoryText);
        } catch (Exception e) {
            System.out.println(" Could not select category: " + e.getMessage());
        }
    }

    public String getBalance() {
        System.out.println(" Getting balance...");
        waitForHomeScreen();

        try {
            waitSeconds(2);
            return driver.findElement(balanceAmount).getText();
        } catch (Exception e) {
            System.out.println(" Could not get balance: " + e.getMessage());
            return "$0.00";
        }
    }

    public String getTotalIncome() {
        System.out.println(" Getting total income...");
        waitForHomeScreen();

        try {
            waitSeconds(2);
            return driver.findElement(incomeAmount).getText();
        } catch (Exception e) {
            System.out.println(" Could not get income: " + e.getMessage());
            return "$0.00";
        }
    }

    public String getTotalExpense() {
        System.out.println(" Getting total expense...");
        waitForHomeScreen();

        try {
            waitSeconds(2);
            return driver.findElement(expenseAmount).getText();
        } catch (Exception e) {
            System.out.println(" Could not get expense: " + e.getMessage());
            return "$0.00";
        }
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
