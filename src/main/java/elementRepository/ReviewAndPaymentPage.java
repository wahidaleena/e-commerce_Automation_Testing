package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class ReviewAndPaymentPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public ReviewAndPaymentPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='checkbox' and @name='billing-address-same-as-shipping']")
	WebElement checkbox;
	@FindBy(xpath = "//span[contains(text(),'Place Order')]")
	WebElement placeOrder;

	public boolean isCheckboxSelected() {

		waits.waitForElementToBeVisible(checkbox,driver);
		return gl.isElementSelected(checkbox);

	}

	public CheckoutSuccessPage PlaceTheOrder() {

		waits.waitForElementToBeClickableUsingFluentWait("//span[contains(text(),'Place Order')]",driver);
		gl.clickUsingJSExecutor(driver, placeOrder);
		return new CheckoutSuccessPage(driver);

	}
}
