package utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	WebDriverWait wait;
	WebDriver driver;
	FluentWait<WebDriver> waits;

	public void waitForElementToBeVisible(WebElement element,WebDriver driver) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.visibilityOf(element));
		

	}
	public void waitForElementToBeVisibleElementLocated(String locator,WebDriver driver) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		

	}
	public void waitForTextToBeVisible(WebElement element,String text,WebDriver driver) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		

	}
	public void waitForElementToBeClickableUsingLocator(String locatorValue) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
		
	}
	
	public void waitForElementToBeClickableUsingWebElement(WebElement element,WebDriver driver) {
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	public void waitForElementToBeSelected(WebElement element) {
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}
	public void waitForAlert() {
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForPresenceOfElement(String locatorValue) {
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
	}
	
	public void waitForElementToBeClickableUsingFluentWait(String locatorName,WebDriver driver) {
		
		waits=new FluentWait<>(driver).withTimeout(Duration.ofSeconds(Constants.explicitwaitTimeout))
				.pollingEvery(Duration.ofSeconds(Constants.explicitwaitTimeout))
				.ignoring(NoSuchElementException.class);
		waits.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorName)));
				
	}
	public void waitForElements(List<WebElement> element) {
		waits=new FluentWait<>(driver).withTimeout(Duration.ofSeconds(Constants.explicitwaitTimeout))
				.pollingEvery(Duration.ofSeconds(Constants.explicitwaitTimeout))
				.ignoring(NoSuchElementException.class);
		waits.until(ExpectedConditions.visibilityOfAllElements(element));
		
	}
	public void waitForAllElements(String locatorName,WebDriver driver) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locatorName)));
	}
	public boolean waitForElementToBeInvisible(WebElement element,WebDriver driver) {
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.explicitwaitTimeout));
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
