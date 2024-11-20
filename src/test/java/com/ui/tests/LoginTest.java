package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;


@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends BaseTest {

	@Test(description = "Verify with the valid user is able to login into the Application using data source as json", groups = {
			"e2e",
			"sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginTestDP")
	public void validateLoginJsonTest(User user) {

		assertEquals(
				homePage.goToLoginPage()
						.performLogin(user.getEmailAddress(),
								user.getPassword())
						.getUserNameLoggedIn(),
				"Venkata Raghavendra Sai Goli");
	}

	@Test(description = "Verify with the valid user is able to login into the Application using data source as csv", groups = {
			"e2e",
			"sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void validateLoginCSVTest(User user) {

		assertEquals(
				homePage.goToLoginPage()
						.performLogin(user.getEmailAddress(),
								user.getPassword())
						.getUserNameLoggedIn(),
				"Venkata Raghavendra Sai Goli");
	}

	@Test(description = "Verify with the valid user is able to login into the Application using data source as excel", groups = {
			"e2e",
			"sanity"}, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void validateLoginExcelTest(User user) {

		assertEquals(
				homePage.goToLoginPage()
						.performLogin(user.getEmailAddress(),
								user.getPassword())
						.getUserNameLoggedIn(),
				"Venkata Raghavendra Sai Goli");
		
	}
}
