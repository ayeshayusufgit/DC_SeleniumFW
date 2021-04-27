package com.democart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.democart.factory.DriverFactory;
import com.democart.pages.AccountsPage;
import com.democart.pages.ForgotPasswordPage;
import com.democart.pages.HomePage;
import com.democart.pages.LoginPage;
import com.democart.pages.RegistrationPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;

	public Properties prop;
	public HomePage homePage;
	public LoginPage loginPage;
	public RegistrationPage registerPage;
	public ForgotPasswordPage forgotPasswordPage;
	public AccountsPage accountsPage;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		accountsPage = new AccountsPage(driver);
		registerPage = new RegistrationPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
