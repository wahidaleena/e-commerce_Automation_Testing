package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.AccountPage;
import elementRepository.CatalogSearch;
import elementRepository.EditAccountPage;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import elementRepository.ProductPage;
import elementRepository.ProductsUnderACategoryPage;
import elementRepository.WishListPage;
import utilities.Constants;
import utilities.ExcelRead;
import utilities.ExcelWrite;
import utilities.GeneralUtilities;
import utilities.SharedUserData;

public class EditAccountPageTest extends BaseClass {

	LandingPage land;
	LoginPage login;
	HomePage home;
	CatalogSearch selectProduct;
	ProductPage product;
	ProductsUnderACategoryPage prodCategory;
	WishListPage wishlist;
	AccountPage account;
	EditAccountPage editAccount;

	@Test(priority = 0,groups = {"Smoke"}, description = "To verify the Edit- My Account details are updated successfully")
	public void verifyTheEditDetailsAreUpdatedSuccessfully() throws IOException {
		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		String fname=SharedUserData.getFname();
		String lname=SharedUserData.getLname();
		home = login.userLogin(email, password);
		account = home.selectMyAccount();	
		String expectedFullname=ExcelRead.getLastRowsStringData(4, "Login Credentials")+ExcelRead.getStringData(1, 0, "EditAccountPageTest");
		editAccount = account.clickOnEdit();		
		account = editAccount.EditTheDetails(ExcelRead.getStringData(1, 0, "EditAccountPageTest"));
		String actualFullname = account.getFullName();
		System.out.println(actualFullname);
		String updatedLname=GeneralUtilities.getOtherNames(actualFullname);
		System.out.println(updatedLname);
		ExcelWrite.writeDataIntoExcelFile(email, password, fname, updatedLname, actualFullname,"");
		System.out.println(actualFullname);		
		System.out.println(expectedFullname);
		AssertJUnit.assertEquals(actualFullname,expectedFullname);

	}
}
