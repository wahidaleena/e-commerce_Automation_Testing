package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import elementRepository.CatalogSearch;
import elementRepository.HomePage;
import elementRepository.LandingPage;
import elementRepository.LoginPage;
import elementRepository.ProductPage;
import elementRepository.ProductsUnderACategoryPage;
import elementRepository.WishListPage;
import utilities.Constants;
import utilities.SharedUserData;

public class WishListPageTest extends BaseClass {

	LandingPage land;
	LoginPage login;
	HomePage home;
	CatalogSearch selectProduct;
	ProductPage product;
	ProductsUnderACategoryPage prodCategory;
	WishListPage wishlist; 

	@Test(priority = 0,groups= {"product tests"}, description = "To verify if the product is present in the wishlist page after adding it to wishlist")
	public void verifyTheProductIsAddedToWishlist() throws InterruptedException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email,password);
		prodCategory = land.selectProductCategoryFromNavBar(Constants.catgProdName);
		product = prodCategory.selectAProduct(Constants.catgitemName);
		wishlist = product.addProductToWishlist();
		boolean result = wishlist.checkTheItemInWishlist(Constants.catgitemName);   
		AssertJUnit.assertTrue(result);

	}
}
