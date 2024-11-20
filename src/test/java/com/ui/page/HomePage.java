package com.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility {

	private static final By SIGN_IN_LNK_LOCATOR = By.xpath("//a[@class='login']");

	public HomePage(Browser browserName,boolean isHeadless) {
		super(browserName,isHeadless);
		navigateToWebsite(JsonUtility.readJson(QA).getUrl());
		//navigateToWebsite(readPropertyFile(QA, "URL"));
		
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		navigateToWebsite(JsonUtility.readJson(QA).getUrl());
		//navigateToWebsite(readPropertyFile(QA, "URL"));
		
	}

	public LoginPage goToLoginPage() {
		click(SIGN_IN_LNK_LOCATOR);
		LoginPage loginPage =new LoginPage(getDriver());
		return loginPage;
	}

	
}
 