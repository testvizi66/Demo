package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
    ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest>  extentTest=new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id---> test
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		 ExtentTest test = extentTest.get();

	        if (test == null) {
	            test = extent.createTest(
	                    result.getMethod().getMethodName());
	            extentTest.set(test);
	        }

	        test.fail(result.getThrowable());

	        WebDriver driver = null;

	        try {
	            driver = (WebDriver) result.getTestClass()
	                    .getRealClass()
	                    .getField("driver")
	                    .get(result.getInstance());

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        if (driver != null) {
	            try {
	                String filepath = getScreenshot(
	                        result.getMethod().getMethodName(), driver);

	                if (filepath != null) {
	                    test.addScreenCaptureFromPath(
	                            filepath,
	                            result.getMethod().getMethodName());
	                }

	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = extentTest.get();

        if (test != null) {
            test.log(Status.SKIP, "Test Skipped");
        }
	}
	
	@Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	@Override
    public void onStart(ITestContext Context) {
		
	}
	
	@Override
    public void onFinish(ITestContext Context) {
		extent.flush();
	}
}
