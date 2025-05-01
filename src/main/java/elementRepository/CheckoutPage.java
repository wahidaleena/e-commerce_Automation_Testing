package elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class CheckoutPage {
	
	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public CheckoutPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@class='input-text' and @type='text' and @name='street[0]']")
	WebElement streetAddressLine1;
	@FindBy(xpath="//input[@class='input-text' and @type='text' and @name='city']")
	WebElement city;
	@FindBy(xpath="//input[@class='input-text' and @type='text' and @name='postcode']")
	WebElement postcode;
	@FindBy(xpath="//select[@class='select' and @name='region_id']")
	WebElement stateDropdown;
	@FindBy(xpath="//select[@class='select' and @name='country_id']")
	WebElement countryDropdown;
	@FindBy(xpath="//input[@class='input-text' and @type='text' and @name='telephone']")
	WebElement telephone;
	@FindBy(xpath="//input[@type='radio' and @name='ko_unique_2']")
	WebElement flatRateRadioButton;
	@FindBy(xpath="//span[contains(text(),'Next')]")
	WebElement nextButton;
	//@FindBy(xpath="//div[@class='shipping-address-items']")
	//WebElement savedShipAddress;
	@FindBy(xpath="//input[@type='radio' and @name='ko_unique_1']")
	WebElement flatRateRadioButtonForSavedAddress;
	
	
	
	
		public ReviewAndPaymentPage addShippingDetails(String addr,String cityName,String country,String state,String post,String phone) throws InterruptedException {
			
			By savedShipAddressLocator = By.xpath("//div[@class='shipping-address-items']");
			
			if(gl.isElementPresent(savedShipAddressLocator, 5,driver)) {
				System.out.println("Saved address found. Using it.");
				waits.waitForElementToBeVisibleElementLocated("//input[@type='radio' and @name='ko_unique_1']", driver);
				flatRateRadioButtonForSavedAddress.click();
				nextButton.click();
			}
			else {
				System.out.println("Saved address not found. Filling new shipping address form.");
			gl.addSleep(500);
			streetAddressLine1.sendKeys(addr);
			city.sendKeys(cityName);
			gl.selectByValue_dropdown(countryDropdown, country);
			gl.selectByVisibleText_dropdown(stateDropdown, state);
			gl.addSleep(500);
			postcode.sendKeys(post);	
			telephone.sendKeys(phone);
			flatRateRadioButton.click();
			gl.addSleep(600);
			waits.waitForElementToBeClickableUsingWebElement(nextButton, driver);
			gl.scrollIntoViewUsingJSExecutor(driver, nextButton);
			nextButton.click();
			
			}
			
			return new ReviewAndPaymentPage(driver);	
		}

}
