package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import utilities.Constants;

public class SignOutTest extends BaseClass {
	
	LandingPage land;
	LoginPage login;
	HomePage home;
	
  @Test(priority = 0,description = "To verify if the user has Sign Out successfully ")
  public void verifyTheUserHasSignOut() throws InterruptedException {
	  
	    //land = new LandingPage(driver);
		//login = land.clickOnSignIn();
		//home = login.userLogin(Constants.email, Constants.pass);
		boolean actualresult=home.clickOnSignOutButton();
		AssertJUnit.assertTrue(actualresult);
		
  }
}
