package com.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility{

	private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']//span");
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	public String getUserNameLoggedIn() {
		return getVisibleText(USER_NAME_LOCATOR);
	}
}
