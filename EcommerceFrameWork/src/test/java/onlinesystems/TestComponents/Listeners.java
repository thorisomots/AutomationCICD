package onlinesystems.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import onlinesystems.resources.ExtentReporterNG;

public class Listeners extends BaseTests implements ITestListener {
	ExtentTest test;
	ExtentReports reports = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

	@Override
	public void onStart(ITestContext context) {
		// Triggered when test suite starts

	}



	@Override
	public void onTestStart(ITestResult result) {
		// Triggered when individual test method starts
		test = reports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Triggered when test passes
		extentTest.get().log(Status.PASS, "The test has passed!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Triggered when test fails
		// test.log(Status.FAIL, "The test has failed!!");
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}
	
	@Override
	public void onFinish(ITestContext context) {
		// Triggered when test suite finishes
		reports.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Triggered when test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Triggered for tests within success percentage
	}
}
