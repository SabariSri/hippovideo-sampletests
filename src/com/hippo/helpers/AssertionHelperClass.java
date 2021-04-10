package com.hippo.helpers;

import org.testng.Assert;

import com.hippo.base.TestUtils;

public class AssertionHelperClass extends TestUtils {

	/***
	 * This class is responsible for providing assertion mechanism for the test
	 * cases. Basic rule for assertion is to raise exception when assertion fails,
	 * and do nothing when is passed
	 */

	public void assertTrue(boolean condition, String testMethodName) {
		/***
		 * Assert on the given condition. If condition is false, raise exception, do nothing otherwise
		 * @param condition: condition to evaluate
		 * @param method: method name to append it with the log
		 */
		if (condition) {
			report.stepPass("Test Passed : " + testMethodName);
			Assert.assertTrue(condition);
		} else {
			report.stepFail("Test Failed : " + testMethodName);
			Assert.assertFalse(condition);
		}

	}

	public void assertFalse(boolean condition, String testMethodName) {
		/***
		 * Assert on the given condition. If condition is true, raise exception, do nothing otherwise
		 * @param condition: condition to evaluate
		 * @param method: method name to append it with the log
		 */
		if (!condition) {
			report.stepPass("Test Passed : " + testMethodName);
			Assert.assertTrue(condition);
		} else {
			report.stepFail("Test Failed : " + testMethodName);
			Assert.assertFalse(condition);
		}
	}

}
