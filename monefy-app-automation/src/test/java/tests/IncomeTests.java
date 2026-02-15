package tests;

import helpers.TestSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MonefyApp;

public class IncomeTests extends TestSetup {

    @Test(priority = 1, description = "Add salary income")
    public void addSalaryIncome() {
        getTest().info("=== Starting Salary Income Test ===");
        System.out.println("\n=== Test: Add Salary Income ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding income: $3987 in Salary");

        app.addIncome("Salary", "3987", "Salary deposit");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should increase");

        getTest().pass(" Salary of $3987 added successfully");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 2, description = "Add deposit income")
    public void addDepositIncome() {
        getTest().info("=== Starting Deposit Income Test ===");
        System.out.println("\n=== Test: Add Deposit Income ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding income: $19659 in Deposit");

        app.addIncome("Deposits", "19659", "Deposit Income");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should increase");

        getTest().pass(" Deposit of $19659 added successfully");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 3, description = "Add savings income")
    public void addSavingsIncome() {
        getTest().info("=== Starting Savings Income Test ===");
        System.out.println("\n=== Test: Add Savings Income ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding income: $200 in Savings");

        app.addIncome("Savings", "200", "Savings deposit");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should increase");

        getTest().pass(" Savings of $200 added successfully");
        System.out.println(" Test Passed\n");
    }
}
