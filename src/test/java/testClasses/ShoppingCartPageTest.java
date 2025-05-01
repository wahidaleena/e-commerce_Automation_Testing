package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import elementRepository.ProductPage;
import elementRepository.ShoppingCartPage;
import utilities.Constants;
import utilities.ExcelRead;
import utilities.SharedUserData;

public class ShoppingCartPageTest extends BaseClass {

	LandingPage land;
	LoginPage login;
	HomePage home;
	ProductPage product;
	ShoppingCartPage cart;
	
	@Test(priority = 0,groups= {"cart tests"},description = "To verify that the item is removed from the cart successfully")
	public void verifyTheItemIsRemovedFromTheCart() throws InterruptedException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email, password);
		cart=home.clickOnViewCartToremoveItemFromCart();
		cart.deleteItemFromCart(Constants.prodToBeDeleted,Constants.sizeToBeDeleted,Constants.colorToBeDeleted);
		boolean actualResult=cart.checkTheItemIsNotPresentInTheCartAfterRemoval(Constants.prodToBeDeleted);
		Assert.assertTrue(actualResult);
	}
	
	@Test(priority = 1,groups= {"cart tests"},description = "To verify that the details in the cart are updated successfully")
	public void verifyTheDetailsInTheCartAreUpdated() throws InterruptedException, IOException {
		
		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email, password);
		product=home.clickOnEditIconInCart(Constants.prodToBeEdited);
		cart=product.editTheProdDetailsInCart(Constants.editedQty);
		boolean actualValue=cart.checkTheEditedFieldInCartAfterUpdate(Constants.prodToBeEdited,Constants.editedQty,Constants.editSize,Constants.editColor);
		System.out.println(actualValue);
		Assert.assertTrue(actualValue);
		
	}
}
