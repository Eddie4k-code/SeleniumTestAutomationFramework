package testScripts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

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
		pageObjects.AddCustomerPage.clickAddCustomer();
		pageObjects.AddCustomerPage.enterFirstName(testData.get("FirstName"));
		pageObjects.AddCustomerPage.enterLastName(testData.get("LastName"));
		pageObjects.AddCustomerPage.enterPostCode(testData.get("PostCode"));
		pageObjects.AddCustomerPage.clickAddCustomerSubmit();
		pageObjects.AddCustomerPage.switchToAlert();
		
		//Check if customer is added
		pageObjects.AddCustomerPage.clickCustomers();
		boolean res = pageObjects.AddCustomerPage.checkCustomerAdded(testData.get("FirstName"));
		
		Assert.assertEquals(res, true);
		
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
