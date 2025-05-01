package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;

public class CreateAccountPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();

	public CreateAccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "[id='firstname']")
	WebElement firstName;
	@FindBy(css = "[name='lastname']")
	WebElement lastName;
	@FindBy(css = "[name='email']")
	WebElement emailTextbox;
	@FindBy(css = "[id='password']")
	WebElement password;
	@FindBy(css = "[id='password-confirmation']")
	WebElement confirmPassword;
	@FindBy(xpath = "//button[@title='Create an Account']")
	WebElement createAccountButton;
	@FindBy(xpath = "//img[@src='https://magento.softwaretestingboard.com/pub/static/version1695896754/frontend/Magento/luma/en_US/images/logo.svg']")
	WebElement logo;

	public boolean getLogoOfThePage() {

		return logo.isDisplayed();

	}

	

	public AccountPage createCustomerAccount(String fname, String lname, String email, String pword, String confpword) {

		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		emailTextbox.sendKeys(email);
		password.sendKeys(pword);
		confirmPassword.sendKeys(confpword);
		createAccountButton.submit();
		return new AccountPage(driver);

	}
}
