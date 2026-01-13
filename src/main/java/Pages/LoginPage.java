package Pages;

import java.time.Duration;

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
		action.sendValues(username, prop.getProperty("email"));
		action.sendValues(password, prop.getProperty("password"));
		driver.findElement(Loginbtn).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

	}

	
	public void logout(Boolean closeDriver)
	{
		if(closeDriver.equals(false))
		{
			action.clickButton(Setting);
			action.clickButton(Logout);
		}
		else
		{
			action.clickButton(Setting);
			logger.info("clicked on Setting button ");
			action.clickButton(Logout);
			logger.info("successfullt clicked on Logout button");
			driver.quit();
		}
	}
}
