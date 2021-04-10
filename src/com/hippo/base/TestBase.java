package com.hippo.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.hippo.constants.UrlConstants;
import com.hippo.constants.WaitConstants;
import com.hippo.pages.HippoHomePage;
import com.hippo.report.CustomExtentReports;

public class TestBase {
	public static WebDriver driver;

	public CustomExtentReports report = new CustomExtentReports();

	@BeforeTest
	@Parameters({ "browser" })
	public void beforeTest(String browser) {
		report.startReport(browser);
		report.startTest("Setup - BeforeTest Xml");
		try {
			String strLocation = System.getProperty("user.dir");
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", strLocation + "/lib/mac_chromedriver_77");
				ChromeOptions opt = new ChromeOptions();
				// opt.addArguments("headless");
				driver = new ChromeDriver(opt);
				report.stepInfo("Chrome driver is instantiated");
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", strLocation + "/lib/mac_geckodriver_v25");
				driver = new FirefoxDriver();
				report.stepInfo("Firefox driver is instantiated");
			} else {
				report.stepInfo("Unsupported browser setup in test xml");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(WaitConstants.pageLoadTime, TimeUnit.SECONDS);
			report.endTest();
		} catch (Exception e) {
			report.stepFail("Error on beforeTest Method of TestBase" + e.toString());
		}
		report.endTest();
	}

	@BeforeClass
	public void beforeClass() {
		report.startTest("Setup - BeforeTestClass");
		HippoHomePage homePage = PageFactory.initElements(driver, HippoHomePage.class);
		TestUtils action = new TestUtils();
		action.launchUrl(UrlConstants.signInPageUrl);
		homePage.signIn();
		report.endTest();
	}
	
	@AfterClass
	public void classCleanup() {
		report.startTest("Cleanup - AfterTestClass");
		HippoHomePage homePage = PageFactory.initElements(driver, HippoHomePage.class);
		TestUtils action = new TestUtils();
		action.launchUrl(UrlConstants.dashboardPageUrl);
		homePage.signOut();
		report.endTest();
	}

	@AfterTest
	public void testCleanup() {
		report.endReport();
		driver.close();
		driver.quit();
	}
}
