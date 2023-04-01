package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utils.CommonFunctions;

public class OpenAccountPage {
	
	/* All functionality and web elements for Open Account Page */
	
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/form/div[1]/select")
	private static WebElement customerNameSelector;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/form/div[2]/select")
	private static WebElement currencySelector;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[2]/div/div/form/button")
	private static WebElement processBtn;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[1]/button[2]")
	private static WebElement clickOpenAccount;
 
	
	public static void selectCustomerName(String name) {
		
		//Selects customer name from dropdown
		
		CommonFunctions.waitForElementToAppear(customerNameSelector, "Customer Name Selector / Dropdown Menu");
		
		Select selectName = new Select(customerNameSelector);
		
		selectName.selectByVisibleText(name);
		
	}
	
	public static void selectCurrency(String currency) {
		//Selects currency from dropdown
		CommonFunctions.waitForElementToAppear(currencySelector, "Currency Selector / Dropdown Menu");
		
		Select selectCurrency = new Select(currencySelector);
		
		selectCurrency.selectByVisibleText(currency);
		
	}
	
	public static void clickProcessBtn() {
		//Clicks Process Button
		CommonFunctions.waitForElementToAppear(processBtn, "Process Button");
		processBtn.click();
	}
	
	public static void clickOpenAccountBtn() {
		//Clicks Open Account
		
		CommonFunctions.waitForElementToAppear(clickOpenAccount, "Open Account Button");
		clickOpenAccount.click();
		
	}
	
	
	public static Boolean checkConfirmationAlert(Alert alert) {
		String alertText = alert.getText();
		
		Boolean confirmText = alertText.contains("Account created successfully with account Number");
		
		return confirmText;
	}
	
	
	
	

}
