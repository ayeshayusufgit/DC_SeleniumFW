package com.democart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doActionsClick(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(locator)).click().build().perform();
	}

	public void doSendkeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doActionsSendkeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();

		Actions actions = new Actions(driver);
		// actions.moveToElement(getElement(locator)).sendKeys(value).perform();
		actions.moveToElement(element).sendKeys(value).perform();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean doIsSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public void clickElement(By locator, String value) {
		List<WebElement> elementList = getElements(locator);
		for (WebElement element : elementList) {
			String elementText = element.getText();
			if (elementText.equals(value)) {
				element.click();
				break;
			}
		}
	}

	public boolean checkElement(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectByVisibleTexWOSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);

		for (WebElement element : optionsList) {
			String elementText = element.getText();

			if (elementText.equals(value)) {
				element.click();
				break;
			}
		}
	}

	public List<String> doGetOptions(By locator) {
		List<String> optionsList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		for (WebElement element : options) {
			optionsList.add(element.getText());
		}
		return optionsList;
	}

	public void selectChoiceFromDropdown(By locator, String... value) {
		List<WebElement> elements = getElements(locator);
		if (!value[0].equalsIgnoreCase("ALL")) {
			for (WebElement element : elements) {
				String elementText = element.getText();
				System.out.println(elementText);
				for (int i = 0; i < value.length; i++) {
					if (elementText.equals(value[i])) {
						element.click();
						break;
					}
				}
			}
		} else {
			try {
				for (WebElement element : elements) {
					element.click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public void doMoveToElement(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(locator)).perform();
	}

	public void clickSubMenu(By parentMenu, By subMenu) {
		doMoveToElement(parentMenu);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doActionsClick(subMenu);
	}

	public void clickSubMenu(By parentMenu, By firstMenu, By secondMenu) {
		doMoveToElement(parentMenu);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doMoveToElement(firstMenu);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doActionsClick(secondMenu);
	}

	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public boolean waitForUrlToBe(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public String waitForTitleToBe(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeout) {
		waitForAlert(timeout).accept();
	}

	public void dismissJSAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public List<WebElement> visibilityOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void getPageElementsText(By locator, int timeout) {
		/*
		 * visibilityOfAllElements(locator,
		 * timeout).stream().filter(ele->!ele.getText().isBlank())
		 * .forEach(ele->System.out.println(ele.getText()));
		 */
	}

	public void jsWaitForPageLoad(int timeout) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String jsCommand = "return document.readyState";

		if (jse.executeScript(jsCommand).toString().equalsIgnoreCase("complete")) {
			System.out.println("The page is completely loaded!!!");
			return;
		}

		for (int i = 0; i < timeout; i++) {
			try {
				Thread.sleep(1000);
				if (jse.executeScript(jsCommand).toString().equalsIgnoreCase("complete")) {
					System.out.println("The page is completely loaded!!!");
					break;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
}
