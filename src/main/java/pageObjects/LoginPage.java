package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.CommonFunctions;

/* Contains all methods regarding the Login Page on the web application*/

public class LoginPage extends BaseClass {
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[1]/div[1]/button")
	private static WebElement customerLoginBtn;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/form/div/select")
	private static WebElement userDropdown;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div[1]/div[2]/button")
	private static WebElement bankManagerLogin;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/form/button")
	private static WebElement customerLoginGo;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public static void customerLogin(String username) {
		/*Logs into Customer Account*/
		CommonFunctions.waitForElementToAppear(customerLoginBtn, "Customer Login Button");
		customerLoginBtn.click();
		
		CommonFunctions.waitForElementToAppear(userDropdown, "User Login Dropdown Menu");
		
		Select dropdown = new Select(userDropdown);
		
		dropdown.selectByVisibleText(username);
		
		CommonFunctions.waitForElementToAppear(customerLoginGo, "Login Confirmation Button");
		
		customerLoginGo.click();
		
		
		
		
	}
	
	
	public static void managerLogin() {
		/*Logs into Manager Account*/
		
		CommonFunctions.waitForElementToAppear(bankManagerLogin, "Manager Login Button");
		bankManagerLogin.click();
	}
	
	
}
