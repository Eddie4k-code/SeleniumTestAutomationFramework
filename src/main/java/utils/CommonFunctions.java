package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import pageObjects.BaseClass;

public class CommonFunctions extends BaseClass{
	
	public static void waitForElementToAppear(WebElement element, String elementName) {
		/*Waits for element to appear*/
		try {
			
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
		
		} catch(Exception e) {
			Assert.fail();
			e.printStackTrace();
			logger.info("Element not appearing", e);
		}
	}
	
	
	public static File failedScreenShot() {
		/* Takes screenshot of current web page*/
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
	
		return source;

	}
	
	


}
