package demo.session.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports report = ExtentReporterNG.getExtentReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest> (); //helps in making execution threadsafe
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test = report.createTest (result.getMethod ().getMethodName ());
		extentTest.set(test); // unique thread id is generated for each test

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, "Test Passed");
		test.fail (result.getThrowable());
		String filePath = "";
		try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField ("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			filePath = getScreenShot (result.getMethod ().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod ().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush ();

	}

}
