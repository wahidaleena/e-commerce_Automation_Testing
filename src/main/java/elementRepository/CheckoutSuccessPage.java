package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class CheckoutSuccessPage {
	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public CheckoutSuccessPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h1[@class='page-title']")
	WebElement heading;
	@FindBy(xpath = "//div[@class='checkout-success']/p")
	WebElement ordernumberMsg;
	@FindBy(xpath = "//a[@class='order-number']")
	WebElement orderNumber;
	@FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/' and contains(@class,'primary continue')]")
	WebElement continueShopping;

	public String getTheSucessMessageAfterplacingTheOrder() {

		return gl.getTextOfElement(heading);

	}

	public String getTheOrderMsgAndOrderNumb() {

		waits.waitForElementToBeVisible(ordernumberMsg, driver);
		String msg = gl.getTextOfElement(ordernumberMsg);
		//String num = gl.getTextOfElement(orderNumber);
		return msg;

	}

	public HomePage clickOnContinueShopping() {

		continueShopping.click();
		return new HomePage(driver);

	}

}
