package elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;

public class WishListPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();

	public WishListPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public boolean checkTheItemInWishlist(String pname) {
		
		String quotedprodName=gl.stringWithQuotes(pname);
		WebElement prodLink=driver.findElement(By.xpath("//a[contains(text(),"+quotedprodName+")]"));
		return gl.isElementDisplayed(prodLink);
			
	}
}
