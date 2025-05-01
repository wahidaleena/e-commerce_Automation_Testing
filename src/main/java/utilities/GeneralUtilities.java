package utilities;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralUtilities {

	// to get the text of any element
	public String getTextOfElement(WebElement element) {

		return element.getText();
	}

	// to check if displayed
	public boolean isElementDisplayed(WebElement element) {

		return element.isDisplayed();
	}

	// to check if selected
	public boolean isElementSelected(WebElement element) {

		return element.isSelected();
	}

	// get attribute
	public String getAttributeOfElement(WebElement element, String attributeName) {

		return element.getAttribute(attributeName);
	}

	// get css property
	public String getCSSValueOfElement(WebElement element, String propertyName) {

		return element.getCssValue(propertyName);
	}

	// javascript executor click
	public void clickUsingJSExecutor(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollIntoViewUsingJSExecutor(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollByUsingJSExecutor(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000);", element);
	}

	public void smoothScrollIntoViewUsingJSExecutor(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", element);
	}

	// mouse hover an element
	public void mouseHover(WebDriver driver, WebElement element) {

		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	// drag n drop
	public void dragAndDrop(WebDriver driver, WebElement source, WebElement destination) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).perform();
	}

	// key down using the class-'Keys' so any key can be passed as arguments
	public void keyDown(WebDriver driver, Keys key) {

		Actions actions = new Actions(driver);
		actions.keyDown(key).perform();
	}

	// key up
	public void keyUp(WebDriver driver, Keys key) {

		Actions actions = new Actions(driver);
		actions.keyUp(key).perform();
	}

	// double click
	public void doubleClick(WebDriver driver, WebElement element) {

		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	// file upload
	public void fileUpload(WebElement element, String filepath) {

		element.sendKeys(filepath);

	}

	// select by index indropdown
	public void selectByIndex_dropdown(WebElement element, int index) {

		Select select = new Select(element);
		select.selectByIndex(index);

	}

//select by value in dropdown
	public void selectByValue_dropdown(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByValue(value);

	}

	// select by visibletext in dropdown
	public void selectByVisibleText_dropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	// isMultiple return type boolean
	public boolean isMultiple_dropdown(WebElement element) {

		Select select = new Select(element);
		return select.isMultiple();
	}

	public String getFirstSelectedOption_dropdown(WebElement element) {

		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();

	}

	public List<String> getAllSelectedOptions(WebElement element) {

		Select select = new Select(element);
		// using stream method,we take the getText() of elements and store it to a list
		return select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList());

	}

	public List<String> getAllOptions(WebElement element) {

		Select select = new Select(element);
		// using stream method,we take the getText() of elements and store it to a list
		return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());

	}

//Sleep
	public void addSleep(long timeout) throws InterruptedException {

		Thread.sleep(timeout);
	}

//Accept Alert
	public void acceptAlert(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

//cancel Alert
	public void dismissAlert(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}

//Alert sendkeys
	public void alertusingSendkeys(WebDriver driver, String input) {

		driver.switchTo().alert().sendKeys(input);
		driver.switchTo().alert().accept();
	}

//Alert getText()
	public String getTextOfAlert(WebDriver driver, String text) {

		text = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return text;
	}

//switch to frame by nameorid,"webelement",index
	public void switchToFrameUsingIndex(WebDriver driver, int index) {

		driver.switchTo().frame(index);
	}

	public void switchToFrameUsingNameOrId(WebDriver driver, String nameOrId) {

		driver.switchTo().frame(nameOrId);
	}

	public void switchToFrameUsingWebElement(WebDriver driver, WebElement element) {

		driver.switchTo().frame(element);
	}

//switch back from frame to main page
	public void switchBackToMainFromFrame(WebDriver driver) {

		driver.switchTo().defaultContent();
	}

//press enter in search box to initialize search
	public void pressEnterInSearchBox(WebElement searchButtonAsElement) {

		searchButtonAsElement.sendKeys(Keys.ENTER);

	}

//refresh the page
	public void refreshThePage(WebDriver driver) {

		driver.navigate().refresh();
	}

	public void safeClick(WebDriver driver, WebElement element) {
		try {
			element.click();
			System.out.println("Standard click successful.");
		} catch (ElementClickInterceptedException e) {
			System.out.println("Standard click failed, using JS click.");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public String stringWithQuotes(String stmt) {

		String quotedStmt = "'" + stmt + "'";
		return quotedStmt;

	}

	public static String capitalizeFirstLetter(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	public static String sanitizePostalCode(String postalCode) {

		return postalCode.replace("-", "").substring(0, 5);
	}

	public static String getOnlyDigits(String input) {
		return input.replaceAll("\\D+", "").trim();
	}

	public static String getOtherNames(String fullName) {
		if (fullName == null || fullName.trim().isEmpty()) {
			return "";
		}

		String[] parts = fullName.trim().split("\\s+");

		if (parts.length <= 1) {
			return ""; // No other names
		}

		// Join all parts except the first
		StringBuilder otherNames = new StringBuilder();
		for (int i = 1; i < parts.length; i++) {
			otherNames.append(parts[i]);
			if (i < parts.length - 1) {
				otherNames.append(" ");
			}
		}

		return otherNames.toString();
	}
	public boolean isElementPresent(By locator, int timeoutInSeconds,WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	        return true;
	    } catch (TimeoutException e) {
	        return false;
	    }
	}


}