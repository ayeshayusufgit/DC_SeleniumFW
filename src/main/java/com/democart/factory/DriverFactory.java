package com.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	// Threadlocal concept needs to be applied on WebDriver,this is for the Democart project
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		String browser = prop.getProperty("browser");
		System.out.println("The browser:" + browser);

		switch (browser.trim()) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			break;

		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			break;

		case "Safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the correct browser name");
			System.out.println("Pass only Chrome|Firefox|Safari in the config.properties");
		}

		// driver().manage().deleteAllCookies();
		// driver().manage().window().maximize();
		// return driver;
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// return the local copy of the driver
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File dest = new File(filePath);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}
}
