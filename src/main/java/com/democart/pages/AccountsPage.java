package com.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.democart.utils.Constants;
import com.democart.utils.ElementUtil;

import io.qameta.allure.Step;

//Some comments
public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;

	private By headerLogo = By.cssSelector("div#logo a");
	private By accountSectionLinks = By.cssSelector("div#content h2");
	private By searchTextbox = By.cssSelector("div#search input");
	private By searchButton = By.cssSelector("div#search button");
	private By searchProducts = By.cssSelector("div.product-layout div.product-thumb");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Get the Account Page Title")
	public String getAccountsPageTitle() {
		return elementUtil.waitForTitleToBe(Constants.ACCOUNTS_PAGE_TITLE, 10);
	}

	@Step("Get the Account Page Header value")
	public String getHeaderValue() {
		if (elementUtil.doIsDisplayed(headerLogo)) {
			return elementUtil.doGetText(headerLogo);
		}
		return null;
	}

	@Step("Get Account Section count")
	public int getAccountsSectionLinkCount() {
		return elementUtil.getElements(accountSectionLinks).size();
	}

	@Step("Get Account Section List")
	public List<String> getAccountsSectionLinkText() {

		List<WebElement> accountsSectionElementsList = elementUtil.getElements(accountSectionLinks);
		List<String> accountsSectionLinkTextList = new ArrayList<String>();

		for (WebElement element : accountsSectionElementsList) {
			accountsSectionLinkTextList.add(element.getText());
		}
		return accountsSectionLinkTextList;
	}

	@Step("Perform Search with: {0}")
	public boolean doSearch(String searchTerm) {
		System.out.println("Searching for" + searchTerm);
		elementUtil.waitForElementVisible(searchTextbox, 10);
		elementUtil.doClick(searchButton);
		elementUtil.doSendkeys(searchTextbox, searchTerm);

		if (elementUtil.getElements(searchProducts).size() > 0)
			return true;
		return false;
	}
}
