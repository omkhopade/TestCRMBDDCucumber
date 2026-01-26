package Utilities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import DriverFactory.DriverFactory;

public class ActionUtilities extends DriverFactory{

	WebDriver driver;
	Actions action;
	public ActionUtilities(WebDriver driver) {
		this.driver=driver;
		this.action= new Actions(driver);
	}



	public void sendValues(By bylocator, String text)
	{
		try {
			logger.info("text box started editing");
			driver.findElement(bylocator).clear();
			logger.info("text box filling details");
			driver.findElement(bylocator).sendKeys(text);
		}
		catch (Exception e) {

			logger.error("Exceptio occured at enter value in text box "+e.getMessage());
		}
	}

	public void clickButton(By locator, String buttonName)
	{
		try 
		{
			logger.info("waiting for button get visible "+buttonName);
			poolingWaitForElement(locator);
			logger.info("Trying to click "+buttonName);
			action.click(driver.findElement(locator)).build().perform();
			logger.info("Element is clicked succcessfully ");	
		}
		catch (TimeoutException e) {
			logger.error(buttonName+" is not clicked and Exception occure "+e.getMessage());
			logger.info("Retrying to click an elemet  "+buttonName);
		
			try 
			{
				logger.info("waiting for button get visible "+buttonName);
				poolingWaitForElement(locator);
				logger.info("Trying to click "+buttonName);
				action.click(driver.findElement(locator)).build().perform();
				logger.info(buttonName+" is clicked succcessfully ");
			}
			catch (Exception e1) {
				logger.error("Element is not clicked and Exception occure "+e1.getMessage());
				
			}
		}


	}





	public void waitForElement(By locator)
	{
		try {

			logger.info("waiting for element ");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
			logger.info("Element found and performing next action");
		}
		catch (Exception e) {
			logger.error("Exception occure while waiting for element "+e.getMessage());
			
		}
	}
	public void waitForElements(By locator)
	{
		try {

			logger.info("waiting for elements ");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
			logger.info("Element found and pweforming next action");
		}
		catch (Exception e) {
			logger.error("Exception occure while waiting for element "+e.getMessage());
			
		}
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
			
		}


	}




	public static void browserPopUp(WebDriver driver)
	{
		try 
		{

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			Alert alert= wait.until(ExpectedConditions.alertIsPresent());

			logger.info("Alert ms is : \n"+alert.getText());

			alert.dismiss();
			logger.info("Accepted Alert ");
		}
		catch (Exception e) {

			logger.error("Error occure while handling alert "+e.getMessage());
		}
	}



	public void mouseHoverToElement(By locator) {

		try {

			logger.info("Mouse is move to the element");
			waitForElement(locator);
			action.moveToElement(driver.findElement(locator));
			logger.info("Mouse is on the element");
		}

		catch (Exception e) {
			logger.error("Exception ossure while Mouse hover action "+e.getMessage());
		
		}

	}



	public void sendValuesToField(String value, String TextFieldname, By locator)
	{
		try {
			waitForElement(locator);
			logger.info("Started editing "+TextFieldname);
			driver.findElement(locator).clear();
			logger.info(value +" entering to "+TextFieldname);
			driver.findElement(locator).sendKeys(value);
			logger.info(value+" is updated in field  "+TextFieldname);
		}

		catch (Exception e) {

			logger.error("Exception occure while send value to "+TextFieldname);
		}
	}


	public String getTextValue(By locator)
	{
		String Title="";
		try
		{
			waitForElement(locator);
			logger.info("Fetching text value ");
			Thread.sleep(3000);
			Title=driver.findElement(locator).getText();
			logger.info("Value Fetch successfully s");
		}
		catch (Exception e) {
			logger.error(e +" Exception occure while fetching value ");
		}

		return Title;

	}

}
