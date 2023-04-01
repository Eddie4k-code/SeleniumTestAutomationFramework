package testScripts;

import org.testng.annotations.Test;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utils.Constants;
import utils.ExcelUtility;

public class AddCustomer extends BaseClass {
	@Test(dataProvider="addCustomerTestData")
	public void addCustomerTest(Map<String, String> testData) {
		/* Tests the functionality of adding a customer*/
		
		driver.get(Constants.BASEURL);
		logger.info("Starting " + getClass().getSimpleName());
		pageObjects.LoginPage.managerLogin();
		logger.info("Logged in as manager");
		pageObjects.AddCustomerPage.clickAddCustomer();
		logger.info("Clicked Add Customer");
		pageObjects.AddCustomerPage.enterFirstName(testData.get("FirstName"));
		logger.info("Entered First Name " + testData.get("FirstName"));
		pageObjects.AddCustomerPage.enterLastName(testData.get("LastName"));
		logger.info("Entered Last Name " + testData.get("lastName"));
		pageObjects.AddCustomerPage.enterPostCode(testData.get("PostCode"));
		logger.info("Entered Postal Code " + testData.get("PostCode"));
		pageObjects.AddCustomerPage.clickAddCustomerSubmit();
		
		//If there is a null value in testData then we must check that mandatory fields pop up shows.
		
		if (testData.get("nullValue").equals("PostCode")) {
			
			logger.info("Post code is null value, checking for mandatory field pop up.");
			
			String s = pageObjects.AddCustomerPage.checkForMandatoryFieldError("PostCode");
			logger.info(s);
			
			Assert.assertEquals(s, "Please fill out this field.");
		} else if (testData.get("nullValue").equals("FirstName")) {
			logger.info("FirstName code is null value, checking for mandatory field pop up.");
			String s = pageObjects.AddCustomerPage.checkForMandatoryFieldError("FirstName");
			logger.info(s);
			
			Assert.assertEquals(s, "Please fill out this field.");
		} else if (testData.get("nullValue").equals("LastName")) {
			logger.info("LastName code is null value, checking for mandatory field pop up.");
			String s = pageObjects.AddCustomerPage.checkForMandatoryFieldError("LastName");
			logger.info(s);
			
			try {
			
			Assert.assertEquals(s, "Please fill out this field.");
			} catch(AssertionError e) {
				logger.info(e.getMessage());
				Assert.fail();
			}
		}
		
		
		
			
			
			
		//If there is not a null value then proceed as normal
		if(testData.get("nullValue").contains("No")) {
			logger.info("CLicked Add Customer Submit Button");
			pageObjects.AddCustomerPage.switchToAlert();
			logger.info("Alert Appeared, Pressed OK");
			//Check if customer is added
			pageObjects.AddCustomerPage.clickCustomers();
			logger.info("Checking if customer was added");
			boolean res = pageObjects.AddCustomerPage.checkCustomerAdded(testData.get("FirstName"));
			
			Assert.assertEquals(res, true);
		} 
	
	
	}
	
	
	
	

	@DataProvider(name="addCustomerTestData")
	public static Iterator<Object[]> csvReader() throws IOException {
		
		List<Object[]> list = new ArrayList<Object[]>();
	
		int rows = ExcelUtility.getRowCount(Constants.customerTestDataPath, Constants.addCustomerSheetName);
		int colCount = ExcelUtility.getCellCount(Constants.customerTestDataPath, Constants.addCustomerSheetName, 1);
		int subtractRows = 1;
		String[] keys = new String[colCount];
		
		//Get keys for hash map
		for (int i=0; i<colCount; i++) {
			keys[i] = ExcelUtility.getCellData(Constants.customerTestDataPath, Constants.addCustomerSheetName, 0, i);
		}
		
		

		for (int i=0; i < rows; i ++) {
			if (ExcelUtility.getCellData(Constants.customerTestDataPath, Constants.addCustomerSheetName, i, 1).toLowerCase().contains("no")) {
				subtractRows ++;
			}
		}
		for (int row = 1; row < rows; row++) {
			if (ExcelUtility.getCellData(Constants.customerTestDataPath, Constants.addCustomerSheetName, row, 1).toLowerCase().contains("no")) {
				continue;
			}
			Map<String, String> testData = new HashMap<String, String>();
			int keyCounter = 0;
			for (int col = 0; col < colCount; col++) {	
				testData.put(keys[keyCounter], ExcelUtility.getCellData(Constants.customerTestDataPath, Constants.addCustomerSheetName, row, col)); 
				keyCounter ++;
			}
			list.add(new Object[] {testData});	
		}
		return list.iterator();
		
	}

	

}
