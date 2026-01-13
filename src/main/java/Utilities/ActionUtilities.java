package Utilities;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DriverFactory.DriverFactory;

public class ActionUtilities extends DriverFactory{
	
	WebDriver driver;
	
	public ActionUtilities(WebDriver driver) {
		this.driver=driver;
	}

	
	
	public void sendValues(By bylocator, String text)
	{
		try {
			logger.info("text box started editing ");
			driver.findElement(bylocator).clear();
			logger.info("text box filling details");
			driver.findElement(bylocator).sendKeys(text);
		}
		catch (Exception e) {
			
			logger.error(e+ " Exceptio occured at enter value in text box");
		}
	}
	
	public void clickButton(By locator)
	{
		try 
		{
			logger.info("waiting for button get visible ");
			poolingWaitForElement(locator);
			Actions action = new Actions(driver);
			logger.info("Trying to click webElement");
			action.click(driver.findElement(locator)).build().perform();
			logger.info("Element is clicked succcessfully ");	
		}
		catch (Exception e) {
			
			logger.error("Element is not clicked and Exception occure "+e);
			Assert.fail();
		}
		
	}
	   
	
	public void waitForElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}
	
	public void poolingWaitForElement(By locator)
	{
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver)
		
				.withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofMillis(400))
				.ignoring(TimeoutException.class);
		logger.info("Started polling time");
		fluent.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		logger.info("Element is found and try to click");
		
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
