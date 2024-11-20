package com.ui.tests;

import static com.constants.Browser.EDGE;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.page.HomePage;
import com.utility.BrowserUtility;
import com.utility.LamdaTestUtility;

public class BaseTest {

	protected HomePage homePage;
	private boolean isLamdaTest;
	
	@Parameters({"browser","isLamdaTest","isHeadless"})
	@BeforeMethod(description = "Load the Home Page of the WebSite")
	public void setup(
			
			@Optional("chrome")String browser,
			@Optional("false")boolean isLamdaTest,
			@Optional("true")boolean isHeadless,ITestResult result) {

		
		this.isLamdaTest =isLamdaTest;
		WebDriver lamdaDriver;
		if (isLamdaTest) {
			lamdaDriver = LamdaTestUtility.initializeLambaTestSession(browser,
					result.getMethod().getMethodName());
			homePage = new HomePage(lamdaDriver);

		} else {
			// Running tests in Local machine
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
		}

	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "Tear down the browser")
	public void tearDown() {
		if (isLamdaTest) {
			LamdaTestUtility.quitSession();//quit browser session on LamdaTest website
		} else {
			homePage.quit(); //local session quit
		}

	}
}
