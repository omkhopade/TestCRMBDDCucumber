package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Utilities.ActionUtilities;


public class LoginPage extends DriverFactory {
	
	private WebDriver driver;
	private ActionUtilities action = new ActionUtilities(DriverFactory.getDriver());
	
	By username =By.name("email");
	By password = By.name("password");
	By Loginbtn = By.xpath("//div[contains(text(),'Login')]");
	By Setting = By.xpath("//div[@class='ui buttons']/div");
	By Logout = By.xpath("//div[@class='ui buttons']/div/div/a/span[contains(text(),'Log Out')]/parent :: a");
	
	
	public LoginPage(WebDriver driver ) {
		this.driver=driver;
	}
	
	
	
	public void login()
	{
		action.sendValues(driver.findElement(username), prop.getProperty("email"));
		action.sendValues(driver.findElement(password), prop.getProperty("password"));
		driver.findElement(Loginbtn).click();
		
	}
	
	public void logout()
	{
		action.clickButton(driver.findElement(Setting));
		action.clickButton(driver.findElement(Logout));
	}
}
