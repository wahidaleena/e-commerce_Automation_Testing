package testClasses;

import org.testng.annotations.Test;



import java.util.ArrayList;

import org.testng.Assert;


import base.BaseClass;
import dataProvider.DataProviderClass;
import elementRepository.AccountPage;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import utilities.Constants;
import utilities.RandomDataUtility;
import utilities.SharedUserData;

public class LoginPageTest extends BaseClass {

	LoginPage login;
	AccountPage userAccount;
	LandingPage land;
	HomePage home;

	@Test(priority = 0, description = "To verify if user can login successfully",groups = {"Smoke"})
	public void verifySuccessfulLogin() throws InterruptedException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email, password);

		/*
		 * String actualResult=home.getHeadingOfHomePage(); String
		 * expectedResult=Constants.homeHeading;
		 * Assert.assertTrue(actualResult.contains(expectedResult));
		 * System.out.println("Heading of page:"+actualResult);
		 */
		String actualResult = home.getTextOfWelcomeMessage();	
		String expectedResult = Constants.welcomeMsg+" "+SharedUserData.getFullname()+"!";
		System.out.println(expectedResult);
		Assert.assertEquals(actualResult, expectedResult);
		System.out.println("Successful login and message is: " + actualResult);

	}

	@Test(priority = 1,groups = {"Negative"}, description = "Unsuccessful login with correct email and incorrect password", dataProviderClass = DataProviderClass.class, dataProvider = "Unsuccessful Login with correct email")
	public void verifyUnsuccessfulLoginWithCorrectEmail(String uname, String pword) {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		login.userLogin(uname, pword);
		String actualError = login.getTextOfloginError();
		String expectedError = Constants.loginErrMsgForCorrectEmail;
		System.out.println(actualError);
		Assert.assertEquals(actualError, expectedError);

	}

	@Test(priority = 2,groups = {"Negative"}, description = "Unsuccesful login with incorrect email and correct/incorrect password", dataProviderClass = DataProviderClass.class, dataProvider = "Unsuccessful Login with incorrect email")
	public void verifyUnsuccessfulLoginWithInCorrectEmail(String uname, String pword) {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		login.userLogin(uname, pword);
		String actualError = login.getTextOfEmailError();
		String expectedError = Constants.loginErrMsgForInCorrectEmail;
		System.out.println(actualError);
		Assert.assertEquals(actualError, expectedError);

	}
}
