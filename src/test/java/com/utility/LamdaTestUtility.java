package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LamdaTestUtility {

	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverlocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitieslocal = new ThreadLocal<DesiredCapabilities>();

	public static WebDriver initializeLambaTestSession(String browserName,
			String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("browserVersion", "127");
		Map<String, Object> ltOptions = new HashMap<>();
		ltOptions.put("user", "gvenkataraghavendra");
		ltOptions.put("accessKey",
				"ESrHo5lviswtFCRxcYDpIAD9T6zRuBKTfB9VRPk77eoQwwZotc");
		ltOptions.put("build", "Selenium 4");
		ltOptions.put("name", testName);
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("seCdp", true);
		ltOptions.put("selenium_version", "4.23.0");
		capabilities.setCapability("LT:Options", ltOptions);
		capabilitieslocal.set(capabilities);
		WebDriver driver = null;

		try {
			driver = new RemoteWebDriver(new URL(HUB_URL),
					capabilitieslocal.get());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driverlocal.set(driver);
		return driverlocal.get();
	}

	public static void quitSession() {
		if (driverlocal.get() != null) {
			driverlocal.get().quit();
		}
	}

}
