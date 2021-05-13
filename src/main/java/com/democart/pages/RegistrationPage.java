package com.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.democart.utils.Constants;
import com.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By fnameTextbox = By.id("input-firstname");
	private By lnameTextbox = By.id("input-lastname");
	private By emailTextbox = By.id("input-email");
	private By telephoneTextbox = By.id("input-telephone");
	private By passwordTextbox = By.id("input-password");
	private By confirmPasswordTextbox = By.id("input-confirm");
	private By subscribeYesRadiobutton = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNoRadiobutton = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	private By privacyPolicyCheckbox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By accountCreatedMessage = By.xpath("//div[@id='content']/h1");
	private By myAccountLink = By.xpath("//a[@title='My Account']");
	private By logoutLink = By.xpath("//a[text()='Logout']");
    private By logoutContinueButton = By.xpath("//a[text()='Continue']");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Perform account registration with firstname:{0} lastname:{1} email:{2} telephone:{3} password:{4} subscribe:{5}")
	public boolean accountRegistration(String fname, String lname, String email, String telephone, String password,
			String subscribe) {

		elementUtil.clickWhenReady(fnameTextbox, 10);
		elementUtil.doSendkeys(fnameTextbox, fname);
		elementUtil.clickWhenReady(lnameTextbox, 10);
		elementUtil.doSendkeys(lnameTextbox, lname);
		elementUtil.clickWhenReady(emailTextbox, 10);
		elementUtil.doSendkeys(emailTextbox, email);
		elementUtil.clickWhenReady(telephoneTextbox, 10);
		elementUtil.doSendkeys(telephoneTextbox, telephone);
		elementUtil.clickWhenReady(passwordTextbox, 10);
		elementUtil.doSendkeys(passwordTextbox, password);
		elementUtil.clickWhenReady(confirmPasswordTextbox, 10);
		elementUtil.doSendkeys(confirmPasswordTextbox, password);

		if (subscribe.equalsIgnoreCase("Yes")) {
			elementUtil.clickWhenReady(subscribeYesRadiobutton, 10);
		} else {
			elementUtil.clickWhenReady(subscribeNoRadiobutton, 10);
		}

		elementUtil.clickWhenReady(privacyPolicyCheckbox, 10);
		elementUtil.clickWhenReady(continueButton, 10);

		String message = elementUtil.waitForElementVisible(accountCreatedMessage, 10).getText();

		if (message.equalsIgnoreCase(Constants.ACCOUNT_CREATION_SUCCESS_MESSAGE)) {
			elementUtil.clickWhenReady(myAccountLink, 10);
			elementUtil.clickWhenReady(logoutLink, 10);
			elementUtil.clickWhenReady(logoutContinueButton, 10);
			return true;
		}
		return false;
	}
}
