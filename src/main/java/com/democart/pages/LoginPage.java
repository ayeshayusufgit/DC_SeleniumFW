package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By emailTextbox = By.id("input-email");
	private By passwordTextbox = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By messageText = By.xpath("//div[@class='alert alert-success alert-dismissible']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public AccountsPage doLogin(String email, String password) {
		elementUtil.clickWhenReady(emailTextbox, 10);
		elementUtil.doSendkeys(emailTextbox, email);
		elementUtil.clickWhenReady(passwordTextbox, 10);
		elementUtil.doSendkeys(passwordTextbox, password);
		elementUtil.clickWhenReady(loginButton, 10);
		return new AccountsPage(driver);
	}

	public ForgotPasswordPage clickForgotPassword() {
		elementUtil.clickWhenReady(forgotPasswordLink, 10);
		return new ForgotPasswordPage(driver);
	}

	public String getLoginMessage() {
		return elementUtil.waitForElementVisible(messageText, 10).getText();
	}
}
