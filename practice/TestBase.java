package com.extentreports.practice;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static ExtentReports extent;
	public ExtentTest test;

	static {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/com/extentreports/practice/report_"
				+ sdf.format(calender.getTime()) + ".html", false);

	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " Test is Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " Test is Failed because: " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " Test is skipped because: " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " Test is started");
		}
	}

	@BeforeMethod
	public void beforemethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test is started");
	}

	@AfterMethod
	public void aftermethod(ITestResult result) {
		getresult(result);
	}

	@AfterClass(alwaysRun = true)
	public void afterclass() {
		extent.endTest(test);
		extent.flush();
	}
}
