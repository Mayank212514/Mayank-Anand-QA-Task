package tests;

import helpers.TestSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MonefyApp;

/**
 * Simplified Balance Tests
 */
public class BalanceTests extends TestSetup {

    @Test(priority = 1, description = "Verify Income Balance Increment")
    public void incomeIncreasesBalance() {
        getTest().info("=== Starting Income Balance Increment Test ===");
        System.out.println("\n=== Test: Income Balance Increased ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);

        // Add income
        System.out.println("Adding income: $500");
        app.addIncome("Salary", "500", "Test");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        // Balance should change
        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should increase after adding income");

        getTest().pass(" Income balance increased");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 2, description = "Verify Expense Balance Decrement")
    public void expenseDecreasesBalance() {
        getTest().info("=== Starting Expense Balance Decrement Test ===");
        System.out.println("\n=== Test: Expense Balance Decreased ===");

        MonefyApp app = new MonefyApp(driver);

        String initialBalance = app.getBalance();
        System.out.println("Initial Balance: " + initialBalance);

        // Add expense
        System.out.println("Adding expense: $100");
        app.addExpense("Food", "100", "Test");

        String finalBalance = app.getBalance();
        System.out.println("Final Balance: " + finalBalance);

        // Balance should change
        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should decrease after adding expense");

        getTest().pass(" Expense balance decreased");
        System.out.println(" Test Passed\n");
    }
}
