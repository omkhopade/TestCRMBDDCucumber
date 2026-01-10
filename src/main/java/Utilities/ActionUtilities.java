package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DriverFactory.DriverFactory;

public class ActionUtilities extends DriverFactory{
	
	WebDriver driver;
	
	public ActionUtilities(WebDriver driver) {
		this.driver=driver;
	}

	
	
	public void sendValues(WebElement element, String text)
	{
		try {
			logger.info("text box started editing ");
			element.clear();
			logger.info("text box filling details");
			element.sendKeys(text);
		}
		catch (Exception e) {
			
			logger.error(e+ " Exceptio occured at enter value in text box");
		}
	}
	
	public void clickButton(WebElement ele)
	{
		try 
		{
			logger.info("waiting for button get visible ");
			waitForElement(ele);
			Actions action = new Actions(driver);
			logger.info("Trying to click "+ ele);
			action.click(ele).build().perform();
			logger.info("Element is clicked succcessfully ");	
		}
		catch (Exception e) {
			
			logger.error("Element is not clicked and Exception occure "+e);
			Assert.fail();
		}
		
	}
	
	
	public void waitForElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public static void compareValues(String expected, String actual)
	{
		try
		{
			if(expected .equals(actual))
			{
				logger.info("Expected value "+expected + " Actual Value "+actual +" are match ");
			}
			else
			{
				logger.error("Expected value "+expected + " Actual Value "+actual +" are not match ");
			}
		}
		
		catch (Exception e) {
			logger.error("Expected value "+expected + " Actual Value "+actual +" are not match ");
			Assert.fail();
		}
		
		
	}
	
}
