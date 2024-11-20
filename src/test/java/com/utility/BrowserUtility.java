package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());

		} else {
			System.err.print("Invalid Browser.Please specify correct Browser");
		}
	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());

		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());

		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());

		} else {
			System.err.print("Invalid Browser.Please specify correct Browser");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {

		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old"); // headless mode
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old"); // headless mode
				options.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old"); // headless mode
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());

			}

		} else {
			System.err.print("Invalid Browser.Please specify correct Browser");
		}
	}

	public void navigateToWebsite(String url) {
		driver.get().get(url);

	}

	public void maximizeWindow() {
		driver.get().manage().window().maximize();
	}

	public void click(By locator) {
		WebElement anyElement = driver.get().findElement(locator);
		anyElement.click();
	}

	public void inputText(By locator, String value) {
		WebElement anyElement = driver.get().findElement(locator);
		anyElement.sendKeys(value);
	}

	public String getVisibleText(By locator) {
		WebElement anyElement = driver.get().findElement(locator);
		return anyElement.getText();
	}

	public String takeScreenshot(String name) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
		String timestamp = sdf.format(date);
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String path ="./screenshots/" + name
				+ "-" + timestamp + ".png";
		File destFile = new File(path);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	public void quit()
	{
		driver.get().quit();
	}
}
