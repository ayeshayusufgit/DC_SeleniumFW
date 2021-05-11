package com.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	// Threadlocal concept needs to be applied on WebDriver
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public Properties prop;

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("The browser:" + browserName);

		switch (browserName.trim()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver());
			}
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver());
			}
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the correct browser name");
			System.out.println("Pass only chrome|firefox|safari in the config.properties");
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

	public void init_remoteDriver(String browser) {

		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browserName", "chrome");
			try {
				tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
				// tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.equals("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("browserName", "firefox");
			try {
				tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));
				// tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		FileInputStream fileInputStream = null;
		String encodedBase64 = null;

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File finalDestination = new File(filePath);

		try {
			FileUtils.copyFile(src, finalDestination);
			fileInputStream = new FileInputStream(finalDestination);
			byte[] bytes = new byte[(int) finalDestination.length()];
			fileInputStream.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}
	
	public String getScreenshot_2() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		//String filePath =  "./screenshots/" + System.currentTimeMillis() + ".png";
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
