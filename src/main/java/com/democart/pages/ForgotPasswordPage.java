package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.Constants;
import com.democart.utils.ElementUtil;
import com.sun.org.apache.bcel.internal.classfile.Constant;

public class ForgotPasswordPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By emailTextbox = By.id("input-email");
	private By continueButton = By.xpath("//input[@value='Continue']");

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getForgotPasswordTitle() {
		return elementUtil.waitForTitleToBe(Constants.FORGOT_PASSWORD_TITLE, 10);
	}

	public LoginPage resetPassword(String email) {
		elementUtil.clickWhenReady(emailTextbox, 10);
		elementUtil.doSendkeys(emailTextbox, email);
		elementUtil.clickWhenReady(continueButton, 10);
		return new LoginPage(driver);
	}

}
