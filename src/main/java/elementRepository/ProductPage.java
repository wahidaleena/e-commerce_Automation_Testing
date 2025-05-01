package elementRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class ProductPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public ProductPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[id='option-label-size-143-item-169']")
	WebElement largeSizeProduct;
	@FindBy(css = "[id='option-label-size-143-item-168']")
	WebElement mediumSizeProduct;
	@FindBy(css = "[option-label='Blue']")
	WebElement blueColor;
	@FindBy(css = "[option-label='Orange']")
	WebElement orangeColor;
	@FindBy(css = "[option-label='Purple']")
	WebElement purpleColor;
	@FindBy(css = "[id='option-label-color-93-item-49']")
	WebElement blackColor;
	@FindBy(css = "[id='qty']")
	WebElement prodQuantity;
	@FindBy(css = "[id='product-addtocart-button']")
	WebElement addToCartButton;
	@FindBy(xpath = "//h1/span[@itemprop='name']")
	WebElement productTitle;
	@FindBy(xpath = "//div[contains(text(),'You added')]")
	WebElement addToCartSuccessMsg;
	@FindBy(xpath = "//a[contains(@class,'showcart')]")
	WebElement cartIcon;
	@FindBy(xpath = "//a[contains(text(), 'Overnight Duffle')]")
	WebElement productNameInCart;
	@FindBy(css = "[id='top-cart-btn-checkout']")
	WebElement checkoutButton;
	@FindBy(xpath = "//a[contains(text(),'review this product')]")
	WebElement addReview;
	@FindBy(css = ".rating-3")
	WebElement starRating;
	@FindBy(css = "[name='nickname']")
	WebElement nickname;
	@FindBy(css = "[id='summary_field']")
	WebElement summary;
	@FindBy(css = "[id='review_field']")
	WebElement review;
	@FindBy(xpath = "//button[contains(@class,'action submit')]")
	WebElement reviewSubmit;
	@FindBy(xpath = "//div[contains(text(),'You submitted your review')]")
	WebElement reviewSuccessMessage;
	@FindBy(xpath = "//a[contains(@class,'towishlist')]")
	WebElement wishlist;
	@FindBy(css = "[id='product-updatecart-button']")
	WebElement updateCart;
	@FindBy(css = "[id='qty']")
	WebElement editFieldOfProduct;
	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody[@class='cart item']")
	List<WebElement> itemsInCart;

	public String getNameOfTheSelectedProduct() {

		return gl.getTextOfElement(productTitle);

	}

	public String addDressProductToCartFromDirectSearch(String prodQty) {

		largeSizeProduct.click();
		orangeColor.click();
		prodQuantity.clear();
		prodQuantity.sendKeys(prodQty);
		addToCartButton.click();
		waits.waitForElementToBeVisible(addToCartSuccessMsg,driver);
		gl.scrollIntoViewUsingJSExecutor(driver, addToCartSuccessMsg);
		return gl.getTextOfElement(addToCartSuccessMsg);

	}

	public String addBagproductToCartFromCategory() {

		addToCartButton.click();
		gl.scrollIntoViewUsingJSExecutor(driver, addToCartSuccessMsg);
		return gl.getTextOfElement(addToCartSuccessMsg);

	}

	public String checkIfProductIsPresentInCart() {

		cartIcon.click();
		return gl.getTextOfElement(productNameInCart);

	}

	public String addReviewOfTheProduct(String nick, String summaryMsg, String reviewMsg) {

		addReview.click();
		gl.scrollIntoViewUsingJSExecutor(driver, starRating);
		waits.waitForElementToBeClickableUsingWebElement(starRating, driver);
		//starRating.click();
		gl.clickUsingJSExecutor(driver, starRating);
		nickname.sendKeys(nick);
		summary.sendKeys(summaryMsg);
		review.sendKeys(reviewMsg);
		reviewSubmit.click();
		return gl.getTextOfElement(reviewSuccessMessage);

	}

	public WishListPage addProductToWishlist() {

		wishlist.click();
		return new WishListPage(driver);

	}

	public ShoppingCartPage editTheProdDetailsInCart(String editQty) throws InterruptedException {

		if(gl.getAttributeOfElement(mediumSizeProduct, "class").contains("selected")) {
			mediumSizeProduct.click();//deselect
			gl.addSleep(500);
			mediumSizeProduct.click();//select
			
		}
		else {
		mediumSizeProduct.click();
		}
		blackColor.click();
		editFieldOfProduct.clear();
		editFieldOfProduct.sendKeys(editQty);
		updateCart.click();
		return new ShoppingCartPage(driver);
	}

}
