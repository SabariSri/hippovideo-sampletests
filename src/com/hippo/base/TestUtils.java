package com.hippo.base;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hippo.constants.WaitConstants;

public class TestUtils extends TestBase {

	public void launchUrl(String url) {
		try {
			driver.get(url);
			report.stepPass("Url launched :: " + url);
		} catch (Exception e) {
			report.stepFail("Unable to launch url :: " + url + "\n\n" + " ERROR MESSAGE :: " + e.toString());
		}
	}

	public void typeIn(WebElement element, String value, String refKey) {
		try {
			element.sendKeys(value);
			report.stepPass("Typed " + value + " in " + refKey);
		} catch (Exception e) {
			report.stepFail("Unable to type " + value + " in " + refKey + "\n\n" + "ERROR MESSAGE " + e.toString());
		}
	}

	public void pwdTypeIn(WebElement element, String value, String refKey) {
		try {
			element.sendKeys(value);
			byte[] encodedBytes = Base64.getEncoder().encode(value.getBytes());
			value = new String(encodedBytes);
			report.stepPass("Typed " + value + " in " + refKey);
		} catch (Exception e) {
			report.stepFail("Unable to type " + value + " in " + refKey + "\n\n" + "ERROR MESSAGE " + e.toString());
		}
	}

	public void clickOn(WebElement element, String refKey) {
		try {
			element.click();
			report.stepPass("Clicked on " + refKey);
		} catch (Exception e) {
			report.stepFail("Unable to click on " + refKey + "\n\n" + "ERROR MESSAGE :: " + e.toString());
			System.out.println("");
		}
	}

	public String getText(WebElement element, String refKey) {
		try {
			String text = element.getText();
			report.stepInfo("Text of " + refKey + " is " + text);
			return text;
		} catch (Exception e) {
			report.stepFail("Unable to getText of " + refKey + "\n\n" + "ERROR MESSAGE :: " + e.toString());
			return null;
		}
	}

	public List<String> getListText(List<WebElement> elements, String refKey) {
		List<String> textList = new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				textList.add(element.getText().trim());
			}
			report.stepInfo("Text of " + refKey + " is " + textList);
			return textList;
		} catch (Exception e) {
			report.stepFail("Unable to getTexts of " + refKey + "\n\n" + "ERROR MESSAGE :: " + e.toString());
			return null;
		}
	}

	public boolean isElementVisible(String strXpath) {
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.elementLoadTime);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(strXpath)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.elementLoadTime);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
//			report.stepFail(
//					"Element " + element.toString() + " is not visible" + "\n\n" + "ERROR MESSAGE :: " + e.toString());
			return false;
		}
	}
}
