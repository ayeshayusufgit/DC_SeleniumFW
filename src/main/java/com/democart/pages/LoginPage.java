package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By emailTextbox = By.id("input-email");
	private By passwordTextbox = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	// private By messageText = By.xpath("//div[@class='alert alert-success
	// alert-dismissible']");
	private By messageText = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Perform Login with: {0} and {1}")
	public AccountsPage doLogin(String email, String password) {
		elementUtil.clickWhenReady(emailTextbox, 10);
		elementUtil.doSendkeys(emailTextbox, email);
		elementUtil.clickWhenReady(passwordTextbox, 10);
		elementUtil.doSendkeys(passwordTextbox, password);
		elementUtil.clickWhenReady(loginButton, 10);
		return new AccountsPage(driver);
	}

	@Step("Click on Forgot Password Link")
	public ForgotPasswordPage clickForgotPassword() {
		elementUtil.clickWhenReady(forgotPasswordLink, 10);
		return new ForgotPasswordPage(driver);
	}

	
	@Step("Get login message")
	public String getLoginMessage() {
		return elementUtil.waitForElementVisible(messageText, 10).getText();
		//return elementUtil.waitForElementWithFluentWait(messageText, 10, 1).getText();
	}
}
