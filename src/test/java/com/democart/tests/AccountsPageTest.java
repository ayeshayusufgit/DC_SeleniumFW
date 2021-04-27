package com.democart.tests;

import java.util.List;

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
@Story("UserStory 201 : Account Page feature testing")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		loginPage = homePage.clickLoginLink();
		accountsPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
	}

	
	@Description("Verify the Accounts Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title:" + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Description("Verify the Accounts Page Section Count Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accountsSectionCountTest() {
		int accountSectionLinkCount = accountsPage.getAccountsSectionLinkCount();
		Assert.assertTrue(accountSectionLinkCount == Constants.ACCOUNTS_SECTION_COUNT);
	}

	@Description("Verify the Accounts Page Section Link Text Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void accountsSectionTextTest() {
		List<String> accountSectionLinkText = accountsPage.getAccountsSectionLinkText();
		Assert.assertEquals(accountSectionLinkText, Constants.getAccountSectionList());
	}

	
	@Description("Verify Product Search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4, dataProvider = "getProductSearchData")
	public void searchTest(String searchTerm) {
		Assert.assertTrue(accountsPage.doSearch(searchTerm));
	}

	/*
	 * @DataProvider public Object[][] getProductSearchData() { Object[][]
	 * productSearchData = { { "ipod" }, { "samsung" }, { "abc" } }; return
	 * productSearchData; }
	 */

	@DataProvider
	public Object[][] getProductSearchData() {
		return ExcelUtil.getTestData(Constants.SEARCH_SHEET_NAME);
	}
}
