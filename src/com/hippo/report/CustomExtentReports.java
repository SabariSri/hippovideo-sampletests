package com.hippo.report;

import java.io.File;

import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomExtentReports {
	public static ExtentReports extent;
	public static ExtentTest logger;

	public void startReport(String browser) {
		if (browser.equalsIgnoreCase("chrome"))
			extent = new ExtentReports(System.getProperty("user.dir") + "/CustomTestOutput/HtmlReport_ChromeTest.html",
					true);
		else if (browser.equalsIgnoreCase("firefox"))
			extent = new ExtentReports(System.getProperty("user.dir") + "/CustomTestOutput/HtmlReport_FirefoxTest.html",
					true);
		extent.addSystemInfo("Host Name", "Crazy Frog").addSystemInfo("Environment", "QA").addSystemInfo("User Name",
				"Sabari");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
	}

	public void startTest(String testcaseName) {
		logger = extent.startTest(testcaseName);
	}

	public void stepPass(String passText) {
		logger.log(LogStatus.PASS, passText);
	}
	
	public void stepFail(String failText) {
		logger.log(LogStatus.FAIL, failText);
	}

	public void stepInfo(String infoText) {
		logger.log(LogStatus.INFO, infoText);
	}

	public void stepWarning(String warningText) {
		logger.log(LogStatus.WARNING, warningText);
	}

	public void skipTest() {
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	public void endTest() {
		extent.endTest(logger);
	}

	public void endReport() {
		extent.flush();
		extent.close();
		
	}
}
