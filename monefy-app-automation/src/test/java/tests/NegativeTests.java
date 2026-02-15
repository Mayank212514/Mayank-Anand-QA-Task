package tests;

import helpers.TestSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MonefyApp;

public class NegativeTests extends TestSetup {

    @Test(priority = 1, description = "Add Income Without Note")
    public void addIncomeWithoutNote() {
        getTest().info("=== Starting Income Without Note Test ===");
        System.out.println("\n=== Test: Add Income Without Note ===");

        MonefyApp app = new MonefyApp(driver);

        String initialIncome = app.getBalance();
        System.out.println("Initial Balance: " + initialIncome);
        System.out.println("Adding income: $95678 in Salary");

        app.addIncomeWithoutNote("Salary", "95678");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialIncome,
                "Transaction should work without note");

        getTest().pass(" Transaction works without note");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 2, description = "Add Expense without note")
    public void addExpenseWithoutNote() {
        getTest().info("=== Starting Expense Without Note Test ===");
        System.out.println("\n=== Test: Add Expense Without Note ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding expense: $86 in Taxi");

        app.addExpenseWithoutNote("Taxi", "86");

        getTest().info("Expense transaction completed");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "Transaction should work without note");

        getTest().pass(" Transaction works without note");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 3, description = "Add very small amount ($1)")
    public void addVerySmallAmount() {
        getTest().info("=== Starting Very Small Amount Test ===");
        System.out.println("\n=== Test: Very Small Amount Income ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding income: $1 in Deposit");

        app.addIncome("Deposits", "1", "Deposit Income");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "Small amounts should be accepted");

        getTest().pass(" Minimum amount ($1) accepted");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 4, description = "Add High amount ($987698345)")
    public void addHighAmount() {
        getTest().info("=== Starting High Amount Test ===");
        System.out.println("\n=== Test: High Amount Income ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Adding income: $987698345 in Salary");

        app.addIncome("Salary", "987698345", "Salary Income");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        Assert.assertNotEquals(finalBalance, initialBalance,
                "High amounts should be accepted");

        getTest().pass(" High amounts ($987698345) accepted");
        System.out.println(" Test Passed\n");
    }
}
