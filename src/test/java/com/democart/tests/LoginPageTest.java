package com.democart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.democart.base.BaseTest;
import com.democart.utils.Constants;
import com.democart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: Design Full Account Page for Democart Application")
@Story("UserStory 202 : Login Page feature testing")
public class LoginPageTest extends BaseTest {

	@BeforeClass
	public void loginPageSetup() {
		loginPage = homePage.clickLoginLink();
	}

	@Description("Verify Forgot Password Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void forgotPasswordTitleTest() {
		forgotPasswordPage = loginPage.clickForgotPassword();
		Assert.assertEquals(forgotPasswordPage.getForgotPasswordTitle(), Constants.FORGOT_PASSWORD_TITLE);
	}

	@Description("Verify Forgot Password Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void forgotPasswordTest() {
		forgotPasswordPage = loginPage.clickForgotPassword();
		loginPage = forgotPasswordPage.resetPassword(prop.getProperty("email"));
		Assert.assertEquals(loginPage.getLoginMessage(), Constants.FORGOT_PASSWORD_EMAIL_SENT);
	}

	@Description("Verify -Ve Login Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3, dataProvider = "getLoginData")
	public void loginDataDrivenTest(String email, String password, String loginErrorMessage) {
		loginPage.doLogin(email, password);
		Assert.assertEquals(loginPage.getLoginMessage(), loginErrorMessage);
	}

	@DataProvider
	public Object[][] getLoginData() {
		return ExcelUtil.getTestData(Constants.LOGIN_SHEET_NAME);
	}

	@Description("Verify Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
}
