package com.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	
	private static final By EMAIL_INPUT_LOCATOR = By.xpath("//input[@id='email']");
	private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@id='passwd']");
	private static final By SUBMIT_BUTTON = By.xpath("//button[@id='SubmitLogin']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	public MyAccountPage performLogin(String emailID, String password)
	{
		inputText(EMAIL_INPUT_LOCATOR,emailID);
		inputText(PASSWORD_INPUT_LOCATOR,password);
		click(SUBMIT_BUTTON);
		MyAccountPage myAccountPage =new MyAccountPage(getDriver());
		return myAccountPage;
	}

}
