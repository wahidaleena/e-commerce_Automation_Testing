package elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class ProductsUnderACategoryPage {
	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits=new WaitUtility();

	public ProductsUnderACategoryPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='https://magento.softwaretestingboard.com/overnight-duffle.html' and contains(@class,'product-item-link')]")	
	WebElement productLink;
	@FindBy(xpath="//a[contains(text(),'Didi Sport Watch')]")
	WebElement product2Link;
	        
	
	public ProductPage selectAProduct(String prdName) {
		
		//productLink.click();
		String prodNameWithQuotes=gl.stringWithQuotes(prdName);	
		WebElement prodLink=driver.findElement(By.xpath("//a[@class='product-item-link' and contains(text(),"+prodNameWithQuotes+")]"));
		gl.scrollByUsingJSExecutor(driver, prodLink);
		prodLink.click();
		return new ProductPage(driver);
		
		
	}
	public ProductPage selectProduct2() {
		
		product2Link.click();
		return new ProductPage(driver);
		
	}
	
}
