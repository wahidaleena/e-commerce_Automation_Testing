package elementRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class CatalogSearch {
	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public CatalogSearch(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @FindBy(xpath =
	// "//a[@href='https://magento.softwaretestingboard.com/radiant-tee.html' and
	// contains(@class, 'product-item-link')]")
	// WebElement selectedProduct;

	@FindBy(xpath = "//a[@class='product-item-link']")
	List<WebElement> products;

	public WebElement clickOnDynamicSelectedProduct(String prodName) {

		for (WebElement product : products) {
			if (gl.getTextOfElement(product).equalsIgnoreCase(prodName)) {
				WebElement selectedProduct = product.findElement(By.tagName("a"));
				return selectedProduct;

			}
		}
		return null;

	}

	public static String wordWithQuote(String word) {

		return "'" + word + "'";
	}

	public ProductPage selectAProductFromSearchResult(String prodName) throws InterruptedException {

		for (WebElement product : products) {
			waits.waitForElementToBeVisible(product,driver);
			if (gl.getTextOfElement(product).equalsIgnoreCase(prodName)) {
				gl.smoothScrollIntoViewUsingJSExecutor(driver, product);
				System.out.println(product.getText());
				System.out.println("Each product is displayed: " + product.isDisplayed());
				String quotedProdName = wordWithQuote(prodName);
				WebElement selectedProduct = driver.findElement(
						By.xpath("//a[@class='product-item-link' and contains(text()," + quotedProdName + ")]"));
				gl.addSleep(500);
				System.out.println("The required product is displayed: " + selectedProduct.isDisplayed());
				gl.clickUsingJSExecutor(driver, selectedProduct);
				break;

			}
		}

		return new ProductPage(driver);

	}
}
