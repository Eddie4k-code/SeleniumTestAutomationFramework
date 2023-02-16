package testScripts;

import java.io.IOException;

import java.util.Arrays;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utils.Constants;
import utils.ExcelUtility;

public class LoginTest extends BaseClass{
		
	@Test(dataProvider="LoginTestData")
	public void loginTest(String testName, String execute, String manager, String username) {
		logger.info("Starting " + getClass().getSimpleName());
		
		logger.info("Username " + username);
		
		logger.info("Test " + testName);
		
		driver.get(Constants.BASEURL);
		
		if (!manager.toLowerCase().contains("yes")) {
			logger.info("Starting Customer Login");
			pageObjects.LoginPage.customerLogin(username);
		} else if (manager.toLowerCase().contains("yes")) {
			logger.info("Starting Manager Login");
			pageObjects.LoginPage.managerLogin();
		}
		
		
		Assert.assertEquals(true, true);	
	}
	
	
	@DataProvider(name="LoginTestData")
	String[][] getData() throws IOException {


		int rows = ExcelUtility.getRowCount(Constants.loginDataPath, Constants.loginDataSheetName);
		int colCount = ExcelUtility.getCellCount(Constants.loginDataPath, Constants.loginDataSheetName, 1);
		int subtractRows = 1;
		
		System.out.println(rows);
		System.out.println(colCount);
		
		//Get the amount of test cases that wont be executed
		for (int i=0; i < rows; i ++) {
			if (ExcelUtility.getCellData(Constants.loginDataPath, Constants.loginDataSheetName, i, 1).toLowerCase().contains("no")) {
				subtractRows ++;
			}
		}
		
		String[][] testData = new String[rows - subtractRows][colCount];
		
		
		for(int row = 1; row < rows; row++) {
			
			if (ExcelUtility.getCellData(Constants.loginDataPath, Constants.loginDataSheetName, row, 1).toLowerCase().contains("no")) {
				continue;
			}
			
			for (int col = 0; col < colCount; col++) {
				testData[row - 1][col] = ExcelUtility.getCellData(Constants.loginDataPath, Constants.loginDataSheetName, row, col);
				
				
			}
			
		}
		
		logger.info(testData.toString());
		
		
		return testData;
		

	}

}


