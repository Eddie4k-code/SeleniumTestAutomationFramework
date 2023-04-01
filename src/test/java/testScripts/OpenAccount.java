package testScripts;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.OpenAccountPage;
import utils.Constants;

public class OpenAccount extends BaseClass {
	
	
	@Test
	public void OpenAccountTest() {
		
		/* Tests the functionality for opening a new account */
		
		try {
		
		driver.get(Constants.BASEURL);
		logger.info("Starting " + getClass().getSimpleName());
		pageObjects.LoginPage.managerLogin();
		logger.info("Logged in as manager");
		OpenAccountPage.clickOpenAccountBtn();
		logger.info("Clicked Open Account Button");
		OpenAccountPage.selectCustomerName("Ron Weasly");
		logger.info("Selected Customer Name");
		OpenAccountPage.selectCurrency("Dollar");
		logger.info("Selected Currency");
		OpenAccountPage.clickProcessBtn();
		logger.info("Process Button Clicked, Account Should Be Added");
		Alert confirmationAlert = driver.switchTo().alert();
		
		Boolean confirmation = OpenAccountPage.checkConfirmationAlert(confirmationAlert);
		Assert.assertEquals((boolean) confirmation, true);
		} catch (AssertionError e) {
			logger.info(e.getMessage());
			Assert.fail();
		}

		
	}
	
	

}
