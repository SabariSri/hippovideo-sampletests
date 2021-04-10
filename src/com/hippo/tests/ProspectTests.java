package com.hippo.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hippo.base.TestUtils;
import com.hippo.constants.DataConstants;
import com.hippo.constants.UrlConstants;
import com.hippo.helpers.AssertionHelperClass;
import com.hippo.pages.ProspectsHomePage;

public class ProspectTests extends TestUtils {

	ProspectsHomePage prospectPage;

	TestUtils action = new TestUtils();
	AssertionHelperClass customAssert = new AssertionHelperClass();

	@BeforeMethod
	public void beforeEachTestMethod(ITestResult result) throws InterruptedException {
		report.startTest(result.getMethod().getMethodName());
		TestUtils action = new TestUtils();
		action.launchUrl(UrlConstants.prospectsPageUrl);
	}

	@AfterMethod
	public void testMethodCleanup() {
		report.endTest();
	}

	@Test
	public void testAddProspect() {
		prospectPage = PageFactory.initElements(driver, ProspectsHomePage.class);
		DataConstants.userDetails[0] = DataConstants.emailIDOne;
		customAssert.assertTrue(prospectPage.isAddNewProspectsWorking(DataConstants.userDetails), "testAddProspect");
	}

	@Test
	public void testAddProspectAndVerifyTheAvailability() throws Exception {
		prospectPage = PageFactory.initElements(driver, ProspectsHomePage.class);
		DataConstants.userDetails[0] = DataConstants.emailIDTwo;
		prospectPage.addNewProspect(DataConstants.userDetails);
		customAssert.assertTrue(prospectPage.isRequiredEmailAvailableInProspectsTable(DataConstants.emailIDTwo),
				"testAddProspectAndVerifyTheAvailability");
	}

	@Test
	public void testRemoveProspect() {
		prospectPage = PageFactory.initElements(driver, ProspectsHomePage.class);
		String prospectEmail = DataConstants.emailIDOne;
		customAssert.assertTrue(prospectPage.isRemoveProspectsWorking(prospectEmail), "testRemoveProspect");
	}

	@Test
	public void testRemoveProspectAndVerifyTheAvailability() throws Exception {
		prospectPage = PageFactory.initElements(driver, ProspectsHomePage.class);
		String prospectEmail = DataConstants.emailIDTwo;
		prospectPage.removeProspectEntry(prospectEmail);
		customAssert.assertFalse(prospectPage.isRequiredEmailAvailableInProspectsTable(prospectEmail),
				"testRemoveProspectAndVerifyTheAvailability");
	}
}
