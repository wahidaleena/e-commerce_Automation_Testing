package elementRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constants;
import utilities.GeneralUtilities;
import utilities.SharedUserData;
import utilities.WaitUtility;

public class HomePage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Home Page']")
	WebElement homeTitle;
	@FindBy(css = ".logged-in")
	WebElement welcomeMsg;
	@FindBy(css = "[id='search']")
	WebElement searchProductButton;
	@FindBy(css = "[id='ui-id-6']")
	WebElement GearCategory;
	@FindBy(css = "[id='ui-id-27']")
	WebElement watches;
	@FindBy(css = "[data-action='customer-menu-toggle']")
	WebElement welcomeToggleButton;
	@FindBy(xpath = "//div[@class='customer-menu']/child::ul/child::li/child::a[contains(text(),'My Account')]")
	WebElement myAccount;
	@FindBy(xpath = "//a[contains(@class,'showcart')]")
	WebElement cartIcon;
	@FindBy(xpath = "//*[@id='shopping-cart-table']/tbody[1]/tr[2]/td/div/a[3]")
	WebElement removeItem;
	@FindBy(xpath = "//button[contains(@class,'action-accept')]")
	WebElement confirmDelete;
	@FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/checkout/cart/']/span[contains(text(),'View and Edit Cart')]")
	WebElement viewCart;
	@FindBy(css = "[id='top-cart-btn-checkout']")
	WebElement checkoutButton;
	@FindBy(xpath = "//ul[@class='header links']/li[3]/a[@href='https://magento.softwaretestingboard.com/customer/account/logout/']")
	WebElement signOut;
	@FindBy(xpath = "//a[contains(text(),'Sign In')]")
	WebElement signInAfterLogOut;
	
	public String getHeadingOfHomePage() {

		return gl.getTextOfElement(homeTitle);

	}

		public String getTextOfWelcomeMessage() throws InterruptedException {
	
			gl.addSleep(500);
			String expectedResult = Constants.welcomeMsg+" "+SharedUserData.getFullname()+"!";
			waits.waitForTextToBeVisible(welcomeMsg, expectedResult, driver);
			return gl.getTextOfElement(welcomeMsg);
	
		}

	public CatalogSearch searchForAProduct(String searchForAnItemInSearchBox) {

		searchProductButton.click();
		searchProductButton.sendKeys(searchForAnItemInSearchBox);
		gl.pressEnterInSearchBox(searchProductButton);
		return new CatalogSearch(driver);

	}

	public ProductsUnderACategoryPage selectProductCategory2FromNavBar() {

		gl.mouseHover(driver, GearCategory);
		watches.click();
		return new ProductsUnderACategoryPage(driver);

	}

	public AccountPage selectMyAccount() {

		welcomeToggleButton.click();
		myAccount.click();
		return new AccountPage(driver);

	}

	public ShoppingCartPage clickOnViewCartToremoveItemFromCart() throws InterruptedException {

		gl.addSleep(500);
		cartIcon.click();
		//gl.addSleep(500);
		//waits.waitForElementToBeVisibleElementLocated("//a[@href='https://magento.softwaretestingboard.com/checkout/cart/']/span[contains(text(),'View and Edit Cart')]", driver);
		gl.scrollIntoViewUsingJSExecutor(driver, viewCart);
		//waits.waitForElementToBeClickableUsingWebElement(viewCart, driver);
		gl.addSleep(500);
		gl.clickUsingJSExecutor(driver, viewCart);
		//viewCart.click();
		return new ShoppingCartPage(driver);

	}

	public ShoppingCartPage checkItemAfterCartRemoval() throws InterruptedException {

		gl.refreshThePage(driver);
		gl.clickUsingJSExecutor(driver, cartIcon);
		gl.addSleep(500);
		gl.smoothScrollIntoViewUsingJSExecutor(driver, viewCart);
		// waits.waitForElementToBeVisible(viewCart);
		gl.clickUsingJSExecutor(driver, viewCart);
		// gl.addSleep(500);
		return new ShoppingCartPage(driver);

	}

	public ProductPage clickOnEditIconInCart(String prodToBeEdited) {

		gl.refreshThePage(driver);
		cartIcon.click();
		List<WebElement> itemsInCartWhenCartIsClicked=driver.findElements(By.cssSelector("[data-role='product-item']"));
		for (WebElement item : itemsInCartWhenCartIsClicked) {
			String itemName=item.findElement(By.cssSelector("strong.product-item-name a")).getText();
			if(itemName.equalsIgnoreCase(prodToBeEdited)) {
				item.findElement(By.cssSelector("div.primary a")).click();
				break;
			}
		}

		return new ProductPage(driver);
	}

	public CheckoutPage clickOnproceedToCheckout() {

		gl.refreshThePage(driver);
		waits.waitForElementToBeVisibleElementLocated("//a[contains(@class,'showcart')]", driver);
		gl.clickUsingJSExecutor(driver, cartIcon);
		checkoutButton.click();
		return new CheckoutPage(driver);

	}
	public boolean clickOnSignOutButton() throws InterruptedException {
		
		welcomeToggleButton.click();	
		signOut.click();
		gl.addSleep(500);
		return gl.isElementDisplayed(signInAfterLogOut);
		
	}

}