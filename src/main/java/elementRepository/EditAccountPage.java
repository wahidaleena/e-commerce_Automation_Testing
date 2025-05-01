package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;

public class EditAccountPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();

	public EditAccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[id='lastname']")
	WebElement editField;
	@FindBy(css = "[title='Save']")
	WebElement saveButton;

	public AccountPage EditTheDetails(String data) {

		editField.sendKeys(data);
		saveButton.click();
		return new AccountPage(driver);

	}

}
