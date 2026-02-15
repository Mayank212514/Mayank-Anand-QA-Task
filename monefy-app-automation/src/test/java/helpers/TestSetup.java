package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestSetup {

    public AndroidDriver driver;
    public static ExtentReports report;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static int testCount = 0;

    @BeforeSuite
    public void setupReport() {
        // Create the report folder
        new File("test-reports").mkdirs();
        new File("test-reports/screenshots").mkdirs();

        // Create timestamped report
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "test-reports/TestReport_" + timestamp + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setReportName("Monefy App - E2E Test Automation");
        htmlReporter.config().setDocumentTitle("Test Execution Report");
        htmlReporter.config().setEncoding("utf-8");

        report = new ExtentReports();
        report.attachReporter(htmlReporter);

        // System information
        report.setSystemInfo("Application", "Monefy - Money Manager");
        report.setSystemInfo("Platform", "Android");
        report.setSystemInfo("Automation Tool", "Appium + Java");
        report.setSystemInfo("Framework", "TestNG");
        report.setSystemInfo("Tester", "QA Team");
        report.setSystemInfo("Environment", "Test");

        System.out.println("========================================");
        System.out.println("  Test Execution Started");
        System.out.println("  Report: " + reportPath);
        System.out.println("========================================\n");
    }

    @BeforeMethod
    public void startApp(ITestResult result) throws Exception {
        testCount++;
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getRealClass().getSimpleName();

        // Create test entry
        ExtentTest extentTest = report.createTest(className + " - " + testName);
        test.set(extentTest);

        System.out.println("\n[TEST " + testCount + "] Starting: " + className + "." + testName);
        System.out.println(" ");

        try {
            getTest().log(Status.INFO, "Initializing test environment...");

            // Configure Android settings
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("emulator-5554");

            // Get APK path
            String apkPath = System.getProperty("user.dir") + "/app/monefy.apk";
            File apkFile = new File(apkPath);

            if (!apkFile.exists()) {
                getTest().log(Status.WARNING, "APK not found at: " + apkPath);
                System.out.println(" APK not found at: " + apkPath);
            }

            options.setApp(apkPath);
            options.setAppPackage("com.monefy.app.lite");
            options.setAppActivity("com.monefy.activities.main.MainActivity_");
            options.setNoReset(false);

            getTest().log(Status.INFO, "Connecting to Appium server at http://127.0.0.1:4723");
            System.out.println(" Connecting to Appium server...");

            // Start the app
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            getTest().log(Status.PASS, " App started successfully");
            System.out.println(" App started successfully");

        } catch (Exception e) {
            getTest().log(Status.FAIL, " Failed to start app: " + e.getMessage());
            System.out.println(" Failed to start app: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void closeApp(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Log test result
        if (result.getStatus() == ITestResult.SUCCESS) {
            getTest().pass(MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
            System.out.println(" TEST PASSED: " + testName);

        } else if (result.getStatus() == ITestResult.FAILURE) {
            getTest().fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
            getTest().fail("Error: " + result.getThrowable());
            System.out.println(" TEST FAILED: " + testName);
            System.out.println(" Reason: " + result.getThrowable().getMessage());

            // Take screenshot
            String screenshotPath = takeScreenshot(testName);
            if (screenshotPath != null) {
                try {
                    getTest().addScreenCaptureFromPath("screenshots/" + new File(screenshotPath).getName());
                } catch (Exception e) {
                    System.out.println("Could not attach screenshot to report");
                }
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            getTest().skip(MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
            getTest().skip("Reason: " + result.getThrowable());
            System.out.println(" TEST SKIPPED: " + testName);
        }

        // Close the app
        if (driver != null) {
            try {
                driver.quit();
                getTest().log(Status.INFO, "App closed successfully");
                System.out.println(" App closed");
            } catch (Exception e) {
                System.out.println(" App already closed");
            }
        }

        System.out.println("\n");
    }

    @AfterSuite
    public void generateReport() {
        report.flush();
        System.out.println("\n========================================");
        System.out.println("  Test Execution Completed");
        System.out.println("  Total Tests Run: " + testCount);
        System.out.println("========================================");
        System.out.println(" Test report created in: test-reports/");
    }

    public String takeScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";

            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            java.nio.file.Path screenshotPath = java.nio.file.Paths.get("test-reports/screenshots/" + screenshotName);
            java.nio.file.Files.createDirectories(screenshotPath.getParent());
            java.nio.file.Files.write(screenshotPath, screenshot);

            System.out.println(" Screenshot saved: " + screenshotName);
            return screenshotPath.toString();
        } catch (Exception e) {
            System.out.println(" Could not save screenshot: " + e.getMessage());
            return null;
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
