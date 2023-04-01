package pageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.Constants;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeMethod
	public void initalizePages() {
		PageFactory.initElements(driver, pageObjects.LoginPage.class);
		PageFactory.initElements(driver, AddCustomerPage.class);
		PageFactory.initElements(driver, OpenAccountPage.class);
	}
	
	@BeforeClass
	public WebDriver setup() {
		String browser = "chrome";
		
		/*Sets up driver*/
		logger = Logger.getLogger("XYZ BANK");
		PropertyConfigurator.configure("log4j.properties");
		
		if (browser.toLowerCase() == "chrome") {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		} else {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		}
	}
	
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
}
