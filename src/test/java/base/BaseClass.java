package base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import extentReport.ExtentManager;
import extentReport.TestListener;
import utilities.Constants;
import utilities.SharedUserData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import javax.net.ssl.X509ExtendedTrustManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	/*
	 * protected static boolean isDriverManagedAtClassLevel = false;
	 * 
	 * public static boolean isDriverManagedAtClassLevel() { return
	 * isDriverManagedAtClassLevel; }
	 * 
	 * public static void setDriverManagedAtClassLevel(boolean value) {
	 * isDriverManagedAtClassLevel = value; }
	 */
	public static void readProperty() throws IOException {

		prop = new Properties();
		FileInputStream f = new FileInputStream(System.getProperty("user.home")
				+ "//eclipse-workspace//com.magento_e-commerce//src//main//resources//config//config.properties");
		prop.load(f);
	}

	@BeforeSuite
	public void createExtentReport() {

		ExtentManager.createInstance();

	}

	@BeforeMethod(groups = { "Launch" })
	// @Parameters("browser")
	public void beforeMethod() throws IOException {

		readProperty();
		/*
		 * if (browserValue.equalsIgnoreCase("chrome")) {
		 * System.out.println("Running test in Chrome"); driver = new ChromeDriver();
		 * 
		 * } else if (browserValue.equalsIgnoreCase("firefox")) {
		 * System.out.println("Running test in Firefox"); driver = new FirefoxDriver();
		 * 
		 * } else {
		 * 
		 * throw new IllegalArgumentException("Browser not supported: " + browserValue);
		 * }
		 */

		driver = new ChromeDriver();
		TestListener.driver = driver;
		System.out.println("TestListener driver set in test: " + driver);
		driver.get(prop.getProperty("base_url"));
		// maximize the window
		driver.manage().window().maximize();
		// wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.implicitwaitTimeout));

		// Make the driver accessible to the listener

	}

	@AfterMethod(groups = { "End" },alwaysRun = true)
	public void afterMethod() {
		if(driver!=null) {
		// close the driver
		driver.quit();
		driver=null;

	}
		else {
			System.out.println("Driver is null in AfterMethod, skipping quit...");
		}

}
}
