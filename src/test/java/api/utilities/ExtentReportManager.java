package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; //Responsible for look and feel of your report
    public ExtentReports extentReports;
    public ExtentTest test; //Creating entry for pass, fail, etc.

    String repName;

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";

        // Initialize the Extent Reports with Spark Reporter
        sparkReporter = new ExtentSparkReporter(".\\reports\\+repName");
        sparkReporter.config().setReportName("Pet store user API");
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setTheme(Theme.DARK);


        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Pest store user API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User", "Azima");

    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extentReports.createTest(result.getName());
        extentTest.get().log(Status.PASS, "Test Passed");
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        ExtentTest test = extentTest.get();
        test.log(Status.FAIL, "Test Failed: " + result.getName());
        test.log(Status.FAIL, "Error: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }


    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
        extentReports.flush(); //It will not generate report without this method
    }
}


