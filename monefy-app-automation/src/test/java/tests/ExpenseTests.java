package tests;

import helpers.TestSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MonefyApp;

public class ExpenseTests extends TestSetup {
    @Test(priority = 1, description = "Add expense in Food category")
    public void addSingleExpense() {
        getTest().info("=== Starting Single Expense Test ===");
        System.out.println("\n=== Test: Add Single Expense ===");

        MonefyApp app = new MonefyApp(driver);

        // Get initial balance
        String initialBalance = app.getBalance();
        getTest().info("Initial Balance: " + initialBalance);
        System.out.println("Initial Balance: " + initialBalance);

        getTest().info("Adding expense: $25 in Food");
        System.out.println("Adding expense: $25 in Food");

        app.addExpense("Food", "25", "Test expense");

        getTest().info("Expense transaction completed");

        // Get final balance
        String finalBalance = app.getBalance();
        getTest().info("Final Balance: " + finalBalance);
        System.out.println("Final Balance: " + finalBalance);

        // Verify balance changed
        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should change after adding expense");

        getTest().pass(" Expense of $25 added successfully");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 2, description = "Add expense in Transport category")
    public void addTransportExpense() {
        getTest().info("=== Starting Transport Test ===");
        System.out.println("\n=== Test: Add Transport Expense ===");

        MonefyApp app = new MonefyApp(driver);

        // Get initial balance
        String initialBalance = app.getBalance();
        getTest().info("Initial Balance: " + initialBalance);
        System.out.println("Initial Balance: " + initialBalance);

        getTest().info("Adding expense: $865471 in Transport");
        System.out.println("Adding expense: $865471 in Transport");

        app.addExpense("Transport", "865471", "Transport expense");

        getTest().info("Transport transaction completed");

        // Get final balance
        String finalBalance = app.getBalance();
        getTest().info("Final Balance: " + finalBalance);
        System.out.println("Final Balance: " + finalBalance);

        // Verify balance changed
        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should change after adding expense");

        getTest().pass(" Expense of $865471 added successfully");
        System.out.println(" Test Passed\n");
    }

    @Test(priority = 3, description = "Add expense in Entertainment category")
    public void addEntertainmentExpense() {
        getTest().info("=== Starting Entertainment Test ===");
        System.out.println("\n=== Test: Add Entertainment Expense ===");

        MonefyApp app = new MonefyApp(driver);

        // Get initial balance
        String initialBalance = app.getBalance();
        getTest().info("Initial Balance: " + initialBalance);
        System.out.println("Initial Balance: " + initialBalance);

        getTest().info("Adding expense: $195 in Entertainment");
        System.out.println("Adding expense: $195 in Entertainment");

        app.addExpense("Entertainment", "195", "Entertainment expense");

        getTest().info("Entertainment transaction completed");

        // Get final balance
        String finalBalance = app.getBalance();
        getTest().info("Final Balance: " + finalBalance);
        System.out.println("Final Balance: " + finalBalance);

        // Verify balance changed
        Assert.assertNotEquals(finalBalance, initialBalance,
                "Balance should change after adding expense");

        getTest().pass(" Expense of $195 added successfully");
        System.out.println(" Test Passed\n");
    }
}
