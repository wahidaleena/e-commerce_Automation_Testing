package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.CheckoutPage;
import elementRepository.CheckoutSuccessPage;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import elementRepository.ReviewAndPaymentPage;
import utilities.Constants;
import utilities.ExcelRead;
import utilities.ExcelWrite;
import utilities.GeneralUtilities;
import utilities.RandomDataUtility;
import utilities.SharedUserData;

public class CheckoutPageTest{

	protected static WebDriver driver;
	
	LandingPage land;
	LoginPage login;
	HomePage home;
	CheckoutPage checkout;
	ReviewAndPaymentPage payment;
	CheckoutSuccessPage checkoutSuccess;
	
	
	@BeforeClass(groups={"start"})
    public void setUp() {
		if(driver==null) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com");
		
		}
		extentReport.TestListener.driver=driver;
    }
	@Test(priority = 0,groups= {"checkout"},description = "To verify if the order is placed successfully")
	public void verifyTheOrderIsPlacedSuccessfully() throws InterruptedException, IOException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		String fname=SharedUserData.getFname();
		String lname=SharedUserData.getLname();
		String fullname=SharedUserData.getFullname();
		home = login.userLogin(email, password);
		checkout = home.clickOnproceedToCheckout();
		String countryCode=Constants.countryCode;
		String state=Constants.state;
		//String stateCaps=GeneralUtilities.capitalizeFirstLetter(state.trim());
		String postalCode=Constants.postalCode;
		postalCode=GeneralUtilities.sanitizePostalCode(postalCode);
		System.out.println("state: "+state+" and post code: "+postalCode);
		payment = checkout.addShippingDetails(RandomDataUtility.getAddressLine1(), RandomDataUtility.getCity(), countryCode, state, postalCode, RandomDataUtility.getMobileNumber());
		boolean checkboxStatus=payment.isCheckboxSelected();
		System.out.println("checkbox selected: " +checkboxStatus);
		checkoutSuccess = payment.PlaceTheOrder();
		String sucessfulOrder=checkoutSuccess.getTheOrderMsgAndOrderNumb();
		String orderNumber=GeneralUtilities.getOnlyDigits(sucessfulOrder);
		SharedUserData.setOrderNumber(orderNumber);
		ExcelWrite.writeDataIntoExcelFile(email, password, fname, lname,fullname,orderNumber);
		String actualMessage = checkoutSuccess.getTheSucessMessageAfterplacingTheOrder();
		String expectedMessage = Constants.checkoutMsg;
		System.out.println(actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		checkoutSuccess.clickOnContinueShopping();
		Thread.sleep(500);
	}
	 @Test(priority=1,groups= {"signout"},description = "To verify if the user has Sign Out successfully ")
	  public void verifyTheUserHasSignOut() throws InterruptedException {
		  
		    //land = new LandingPage(driver);
			//login = land.clickOnSignIn();
			//home = login.userLogin(Constants.email, Constants.pass);
		    land = new LandingPage(driver);   // Re-initialize landing page
		    home = new HomePage(driver);       // Re-initialize home page
			boolean actualresult=home.clickOnSignOutButton();
			Assert.assertTrue(actualresult);
			System.out.println("User signed out successfully");
			
	  }
	
	@AfterClass(groups={"stop"})
    public void tearDown() {
       if(driver!=null) {
            driver.quit();
       }
        
    }
}
