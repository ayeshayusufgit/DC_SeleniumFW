package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By myAccountLink = By.xpath("//a[@title='My Account']");
	private By loginLink = By.xpath("//a[text()='Login']");
	private By registerLink = By.xpath("//a[text()='Register']");
	private By loginErrorMEssage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public LoginPage clickLoginLink() {
		elementUtil.clickWhenReady(myAccountLink, 10);
		elementUtil.clickWhenReady(loginLink, 10);
		return new LoginPage(driver);

	}

	public RegistrationPage clickRegisterLink() {
		elementUtil.clickWhenReady(myAccountLink, 10);
		elementUtil.clickWhenReady(registerLink, 10);
		return new RegistrationPage(driver);
	}

	public String getLoginErrorMessage() {
		return elementUtil.waitForElementVisible(loginErrorMEssage, 10).getText();
	}

}
