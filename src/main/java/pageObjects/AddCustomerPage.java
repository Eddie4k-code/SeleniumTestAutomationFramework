package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.CommonFunctions;

public class AddCustomerPage {

	@FindBy(xpath="/html/body/div/div/div[2]/div/div[1]/button[1]")
	private static WebElement addCustomerBtn;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")
	private static WebElement enterFName;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")
	private static WebElement enterLName;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input")
	private static WebElement enterPCode;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div/form/button")
	private static WebElement addCustomerSubmit;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[1]/button[3]")
	private static WebElement customersBtn;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/form/div/div/input")
	private static WebElement customerSearch;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]")
	private static WebElement searchedFirstName;
	
	
	
	
	
	public static void clickAddCustomer() {
		/* Clicks Add Customer Button */
		CommonFunctions.waitForElementToAppear(addCustomerBtn, "Add Customer Button");
		addCustomerBtn.click();
		
	}
	
	
	public static void enterFirstName(String firstName) {
		/* Enters first name into input */
		CommonFunctions.waitForElementToAppear(enterFName, "First Name Input");
		enterFName.sendKeys(firstName);
	}
	
	public static void enterLastName(String lastName) {
		/* Enters last name into input */
		
		CommonFunctions.waitForElementToAppear(enterLName, "Last Name Input");
		enterLName.sendKeys(lastName);
		
	}
	
	public static void enterPostCode (String postCode) {
		/* Enters post code into input */
		CommonFunctions.waitForElementToAppear(enterPCode, "Zip Code Input");
		enterPCode.sendKeys(postCode);
	}
	
	public static void clickAddCustomerSubmit() {
		/* Clicks add customer submit button */
		CommonFunctions.waitForElementToAppear(addCustomerSubmit, "Add Customer Submit Button");
		addCustomerSubmit.click();
	}
	
	public static void switchToAlert() {
		/* Switches to alert in browser and presses ok */
		CommonFunctions.driver.switchTo().alert().accept();
	}
	
	public static void clickCustomers() {
		CommonFunctions.waitForElementToAppear(customersBtn, "Customers Button");
		customersBtn.click();
	}
	
	public static boolean checkCustomerAdded(String firstName) {
		/* Checks if customer first name is added to list */
		CommonFunctions.waitForElementToAppear(customerSearch, "Customer Search Input");
		customerSearch.sendKeys(firstName);
		CommonFunctions.waitForElementToAppear(searchedFirstName, "Searched First Name");
		
		System.out.println(searchedFirstName.getText());
		
		if(searchedFirstName.getText().contains(firstName)) {
			return true;
		} else {
			return false;
		}
	
		
	}
	

}
