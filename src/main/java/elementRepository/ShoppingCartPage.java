package elementRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constants;
import utilities.GeneralUtilities;
import utilities.WaitUtility;

public class ShoppingCartPage {

	private WebDriver driver;
	GeneralUtilities gl = new GeneralUtilities();
	WaitUtility waits = new WaitUtility();

	public ShoppingCartPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/jupiter-all-weather-trainer.html' and contains(text(),'Jupiter')]")
	WebElement checkRemovedItemName;
	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody[2]/tr[1]/td[3]/div/div/label/input[@id='cart-702566-qty']")
	WebElement editedQtyInCart;
	@FindBy(css = "[name=update_cart_action]")
	WebElement updateCart;
	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody[@class='cart item']")
	List<WebElement> itemsInCart;
	@FindBy(xpath = "//tbody[@class='cart item']/tr[@class='item-info']/td/div[@class='product-item-details']/strong/a")
	List<WebElement> productsInCart;

	public void deleteItemFromCart(String prodToBeDeleted, String sizeToBeDeleted, String colorToBeDeleted) {

		for (WebElement item : itemsInCart) {
			String itemName = item.findElement(By.cssSelector("strong.product-item-name a")).getText().trim();
			System.out.println(itemName);

			List<WebElement> itemsizeOrColorList = item.findElements(By.cssSelector("dl.item-options dt"));
			List<WebElement> itemsizeOrColorValuesList = item.findElements(By.cssSelector("dl.item-options dd"));

			String size = "";
			String color = "";
			for (int i = 0; i < itemsizeOrColorList.size(); i++) {

				String itemsizeOrColor = itemsizeOrColorList.get(i).getText().trim();
				String itemsizeOrColorValue = itemsizeOrColorValuesList.get(i).getText().trim();
				if (itemsizeOrColor.equalsIgnoreCase("Size")) {

					size = itemsizeOrColorValue;
				} else if (itemsizeOrColor.equalsIgnoreCase("Color")) {
					color = itemsizeOrColorValue;
				}

			}

			if (itemName.equalsIgnoreCase(prodToBeDeleted) && size.equalsIgnoreCase(sizeToBeDeleted)
					&& color.equalsIgnoreCase(colorToBeDeleted)) {
				WebElement deleteButton = item.findElement(By.cssSelector("a.action-delete"));
				deleteButton.click();
				System.out.println("Product deleted: " + itemName + ", Size: " + size + ", Color: " + color);
				break;
			}
		}

	}

	public boolean checkTheItemIsNotPresentInTheCartAfterRemoval(String prodtoBeChecked) {

		for (WebElement prod : productsInCart) {
			String prodName = gl.getTextOfElement(prod);
			if (!prodName.equalsIgnoreCase(prodtoBeChecked)) {

				return true;
			}
		}
		return false;

	}

	public boolean checkTheEditedFieldInCartAfterUpdate(String prodName, String qty, String prodSize,
			String prodColor) {

		gl.refreshThePage(driver);
		// gl.smoothScrollIntoViewUsingJSExecutor(driver, updateCart);
		// updateCart.submit();
		List<WebElement> itemsInCart = driver.findElements(By.xpath("//table[@id='shopping-cart-table']/tbody"));
		for (WebElement item : itemsInCart) {
			String itemName = item.findElement(By.cssSelector("strong.product-item-name a")).getText();
			if (itemName.equalsIgnoreCase(prodName)) {
				List<WebElement> itemsizeOrColorList = item.findElements(By.cssSelector("dl.item-options dt"));
				List<WebElement> itemsizeOrColorValuesList = item.findElements(By.cssSelector("dl.item-options dd"));

				String size = "";
				String color = "";
				for (int i = 0; i < itemsizeOrColorList.size(); i++) {

					String itemsizeOrColor = itemsizeOrColorList.get(i).getText().trim();
					String itemsizeOrColorValue = itemsizeOrColorValuesList.get(i).getText().trim();
					if (itemsizeOrColor.equalsIgnoreCase("Size")) {

						size = itemsizeOrColorValue;
					} else if (itemsizeOrColor.equalsIgnoreCase("Color")) {
						color = itemsizeOrColorValue;
					}

					

				}
				
				WebElement input = item.findElement(By.tagName("input"));
				gl.smoothScrollIntoViewUsingJSExecutor(driver, input);
				String actualqty = gl.getAttributeOfElement(input, "value");
				System.out.println("Expected size: " + prodSize + ", Found: " + size);
				System.out.println("Expected color: " + prodColor + ", Found: " + color);
				System.out.println("Expected qty: "+qty+", Found: "+actualqty);
				
				return size.equalsIgnoreCase(prodSize) && color.equalsIgnoreCase(prodColor)
						&& qty.equalsIgnoreCase(actualqty);

			}

		}
		return false;

	}

}