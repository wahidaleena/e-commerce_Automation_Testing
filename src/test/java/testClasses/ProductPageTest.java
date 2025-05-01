package testClasses;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataProvider.DataProviderClass;
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
import utilities.SharedUserData;

public class ProductPageTest extends BaseClass {

	LandingPage land;
	LoginPage login;
	HomePage home;
	CatalogSearch selectProduct;
	ProductPage product;
	ProductsUnderACategoryPage prodCategory;
	WishListPage wishlist;
	AccountPage account;
	EditAccountPage editAccount;

	@Test(priority = 0,groups= {"product tests"}, description = "To verify that the exact selected product appears in the page",dataProviderClass = DataProviderClass.class,dataProvider = "Search product names")
	public void verifyTheSearchedProductName(String prodName) throws InterruptedException, IOException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email, password);
		selectProduct = home.searchForAProduct(Constants.searchProductDescriptionOnSearchBar);
		product = selectProduct.selectAProductFromSearchResult(prodName);
		String actualName = product.getNameOfTheSelectedProduct();
		String expectedName = prodName;
		AssertJUnit.assertTrue(actualName.contains(expectedName));

	}

	@Test(priority = 1,groups= {"product tests"}, description = "To verify that the product is added to Cart from direct search successfully",dataProviderClass = DataProviderClass.class,dataProvider = "Search product names")
	public void verifyTheDressProductIsAddedToCartFromDirectSearch(String prodName) throws InterruptedException, IOException {

		land = new LandingPage(driver);
		login = land.clickOnSignIn();
		String email=SharedUserData.getEmail();
		String password=SharedUserData.getPassword();
		home = login.userLogin(email,password);
		selectProduct = home.searchForAProduct(Constants.searchProductDescriptionOnSearchBar);
		product = selectProduct.selectAProductFromSearchResult(prodName);
		String actualMessage = product.addDressProductToCartFromDirectSearch(Constants.dressqty);
		System.out.println("Product added to cart");
		String expectedMessage =Constants.cartAddMsg1+prodName+Constants.cartAddMsg2;
		AssertJUnit.assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test(priority = 2,groups= {"product tests"}, description = "To verify that the product is added to Cart from category selection successfully")
	public void verifyTheBagProductIsAddedToCartFromNavBar() throws IOException, InterruptedException {

		land = new LandingPage(driver);
		prodCategory=land.selectProductCategoryFromNavBar(Constants.catgProdName);
		product = prodCategory.selectAProduct(Constants.catgitemName);
		String actualMessage=product.addBagproductToCartFromCategory();
		String expectedMessage = Constants.catgitemName;
		AssertJUnit.assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test(priority = 3,groups= {"product tests"}, description = "To verify that review of the product is submitted successfully ")
	public void verifytheReviewOfproductIsAddedSuccessfully() throws IOException, InterruptedException {

		land = new LandingPage(driver);
		prodCategory=land.selectProductCategoryFromNavBar(Constants.catgProdName);
		product = prodCategory.selectAProduct(Constants.catgitemName);
		String actualMessage = product.addReviewOfTheProduct(ExcelRead.getStringData(4, 1, "ProductPageTest"), ExcelRead.getStringData(5, 1, "ProductPageTest"),
				ExcelRead.getStringData(6, 1, "ProductPageTest"));
		String expectedMessage = ExcelRead.getStringData(7, 1, "ProductPageTest");
		AssertJUnit.assertTrue(actualMessage.contains(expectedMessage));
	}

}
