package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;

public class AccountPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".logged-in")
	WebElement welcomeMsg;
	@FindBy(xpath = "//div[contains(@class,'box-information')]/child::div/child::p/child::br")
	WebElement emailInContactInfo;
	@FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/customer/account/edit/' and contains(@class,'edit')]")
	WebElement editLink;
	@FindBy(xpath = "//div[contains(text(),'You saved the account information.')]")
	WebElement editSuccessMessage;
	@FindBy(xpath = "//div[contains(@class,'box-information')]/child::div/child::p")
	WebElement fullName;
	

	public String getWelcomeMsg() {

		return gl.getTextOfElement(welcomeMsg);

	}

	public String getEmailIdfromContactInfo() {

		return gl.getTextOfElement(emailInContactInfo);

	}

	public EditAccountPage clickOnEdit() {

		editLink.click();
		return new EditAccountPage(driver);

	}
	
	public String getFullName() {
		
		
		String fullText= gl.getTextOfElement(fullName);
		String name=fullText.split("\n")[0];
		return name;
				
	}

	public String getUpdatedFieldDetails() {

		return gl.getTextOfElement(fullName);

	}

}
