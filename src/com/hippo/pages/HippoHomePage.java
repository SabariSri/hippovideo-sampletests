package com.hippo.pages;

import java.util.Base64;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.hippo.base.TestUtils;
import com.hippo.constants.DataConstants;

public class HippoHomePage extends TestUtils {

	@FindBy(how = How.ID, using = "user_email")
	private WebElement wUserName;

	@FindBy(how = How.ID, using = "user_password")
	private WebElement wPwdField;

	@FindBy(how = How.XPATH, using = "//input[@value = 'SIGN IN']")
	private WebElement wSignIn;

	@FindBy(how = How.ID, using = "ly_20000000007")
	private WebElement wUserSettingsIcon;

	@FindBy(how = How.ID, using = "userSignOutTxt")
	private WebElement wSignOut;

	public void signIn() {
		typeIn(wUserName, DataConstants.hippoLoginUserName, "username field");
		byte[] decodedBytes = Base64.getDecoder().decode(DataConstants.hippoLoginPwd);
		String decodedPwd = new String(decodedBytes);
		pwdTypeIn(wPwdField, decodedPwd, "password field");
		clickOn(wSignIn, "Sign-in button");	
	}

	public void signOut() {
		clickOn(wUserSettingsIcon, "user settings icon");
		clickOn(wSignOut, "sign-out button");
	}

}
