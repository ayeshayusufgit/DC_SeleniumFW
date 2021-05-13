package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By myAccountLink = By.xpath("//a[@title='My Account']");
	private By loginLink = By.xpath("//a[text()='Login']");
	private By registerLink = By.xpath("//a[text()='Register']");
	//private By loginErrorMEssage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Click Login link")
	public LoginPage clickLoginLink() {
		elementUtil.clickWhenReady(myAccountLink, 10);
		elementUtil.clickWhenReady(loginLink, 10);
		return new LoginPage(driver);
	}

	@Step("Click Register link")
	public RegistrationPage clickRegisterLink() {
		elementUtil.clickWhenReady(myAccountLink, 10);
		elementUtil.clickWhenReady(registerLink, 10);
		return new RegistrationPage(driver);
	}

	@Step("Get Login Error Message")
	public String getLoginErrorMessage() {
		return elementUtil.waitForElementVisible(loginErrorMessage, 10).getText();
	}
}
