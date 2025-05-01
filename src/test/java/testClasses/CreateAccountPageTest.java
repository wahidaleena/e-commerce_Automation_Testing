package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.AccountPage;
import elementRepository.CreateAccountPage;
import elementRepository.LandingPage;
import utilities.Constants;
import utilities.RandomDataUtility;
import utilities.SharedUserData;

public class CreateAccountPageTest extends BaseClass {

	LandingPage land;
	CreateAccountPage newAccount;
	AccountPage userAccount;
	public String fname;
	public String lname;
	
	@Test(priority = 0,description = "To verify if correct URl is loaded",groups = {"Smoke"})
	public void verifyIfUserHitsTheCorrectURL() {

		newAccount = new CreateAccountPage(driver);
		boolean actualResult = newAccount.getLogoOfThePage();
		Assert.assertTrue(actualResult);
		System.out.println("logo is present: " + actualResult);

	}

	@Test(priority = 1,description = "To verify if a customer can create an account",groups={"Smoke"})
	public void verifyTheCustomerAccountIsCreated() {

		land=new LandingPage(driver);
		newAccount=land.clickOnCreateAccount();
		SharedUserData.generateNewUser();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		fname =SharedUserData.getFname();
		lname = SharedUserData.getLname();
		userAccount=newAccount.createCustomerAccount(fname, lname, email, password, password);
		String expectedMsg=Constants.welcomeMsg+" "+fname+" "+lname+"!";
		String actualMsg=userAccount.getWelcomeMsg();
		Assert.assertEquals(actualMsg, expectedMsg,"Not able to create an account");
		System.out.println("Account created and message is: "+actualMsg);
		
	}
	
}
