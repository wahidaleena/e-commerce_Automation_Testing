package elementRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class LandingPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits=new WaitUtility();

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Create an Account')]")
	WebElement createAccountLink;
	@FindBy(xpath = "//a[contains(text(),'Sign')]")
	WebElement signInLink;
	@FindBy(css = "[id='ui-id-6']")
	WebElement GearLink;
	//@FindBy(css = "ul.submenu li.category-item a span")
	//List<WebElement> GearCategoryLinks;
	@FindBy(css = "[id='ui-id-25']")
	WebElement bags;
	@FindBy(css = "[id='ui-id-27']")
	WebElement watches;

	public CreateAccountPage clickOnCreateAccount() {

		createAccountLink.click();
		return new CreateAccountPage(driver);
	}

	public LoginPage clickOnSignIn() {

		signInLink.click();
		return new LoginPage(driver);
	}

	public ProductsUnderACategoryPage selectProductCategoryFromNavBar(String prodName) throws InterruptedException {

		
		/*gl.mouseHover(driver, GearLink);
		gl.addSleep(500);
		//waits.waitForAllElements("ul.submenu li.category-item a span",driver);
		List<WebElement> GearCategoryLinks=driver.findElements(By.cssSelector("ul.submenu *"));
		boolean found=false;
		for (WebElement link : GearCategoryLinks) {
			gl.mouseHover(driver, GearLink);
			String actualProdName=link.getText().trim();
			System.out.println(actualProdName);
			
			if (actualProdName.equalsIgnoreCase(prodName)) {
				System.out.println("Clicking on product: " + actualProdName);
				link.click();
				found =true;
				break;	
			}
			if(!found) {
				throw new RuntimeException(prodName+" is not found under Gear Category");
			}
		}*/
		gl.mouseHover(driver, GearLink);

		// Give it a moment to render submenu (temp debug)
		Thread.sleep(1000);

		// Try printing submenu items manually
		List<WebElement> submenuItems = driver.findElements(By.cssSelector("ul.submenu li"));
		System.out.println("Submenu items found: " + submenuItems.size());
		boolean found =false;
		for (WebElement link : submenuItems) {
		    String actualProdName = link.getText().trim();
		    if (!actualProdName.isEmpty()) {
		        System.out.println("-> " + actualProdName);
		    }
		   if (actualProdName.equalsIgnoreCase(prodName)) {
				System.out.println("Clicking on product: " + actualProdName);
				WebElement prodLink=link.findElement(By.tagName("a"));
				prodLink.click();
				found =true;
				break;	
			}
			/*if(!found) {
				throw new RuntimeException(prodName+" is not found under Gear Category");
			}
		}*/
		}
		return new ProductsUnderACategoryPage(driver);

	}
	}



	
