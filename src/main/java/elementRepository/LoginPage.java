package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constants;
import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class LoginPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits=new WaitUtility();

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[id='email']")
	WebElement usernameAsEmail;
	@FindBy(css = "[id='pass']")
	WebElement password;
	@FindBy(xpath = "//span[contains(text(),'Sign')]")
	WebElement signInButton;
	@FindBy(xpath = "//div[@class='messages']/child::div/child::div")
	WebElement loginError;
	@FindBy(css="[id='email-error']")
	WebElement emailError;
	
	
	
	
	public HomePage userLogin(String uname, String pword) {

		waits.waitForElementToBeVisible(usernameAsEmail,driver);
		usernameAsEmail.sendKeys(uname);
		waits.waitForElementToBeVisible(password,driver);
		password.sendKeys(pword);
		waits.waitForElementToBeVisible(signInButton,driver);
		signInButton.submit();
		return new HomePage(driver);

	}
	
	public String getTextOfloginError() {
		
		String expectedError = Constants.loginErrMsgForCorrectEmail;
		waits.waitForTextToBeVisible(loginError, expectedError, driver);
		return gl.getTextOfElement(loginError);
		
	}
public String getTextOfEmailError() {
		
	    String Error = Constants.loginErrMsgForInCorrectEmail;
	    waits.waitForTextToBeVisible(emailError, Error, driver);
		return gl.getTextOfElement(emailError);
		
	}
}
