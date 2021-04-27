package com.democart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
@Story("UserStory 203 : Registration Page feature testing")
public class RegisterPageTest extends BaseTest {

	@BeforeMethod
	public void registerPageSetup() {
		registerPage = homePage.clickRegisterLink();

	}

	@Description("Verify Account Registration Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getRegisterationData")
	public void registrationTest(String fname, String lname, String email, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registerPage.accountRegistration(fname, lname, email, telephone, password, subscribe));
	}

	@DataProvider
	public Object[][] getRegisterationData() {
		return ExcelUtil.getTestData(Constants.REGISTRATION_SHEET_NAME);
	}
}
