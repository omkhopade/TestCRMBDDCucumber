package Utilities;
 
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Page_Utilities.TaskUtilities;

public class ContactUtilities  extends DriverFactory{
	
	
	WebDriver driver;
	
	By Menu = By.xpath("//span[contains(text(),'Home')]/ parent :: a");
	By CreateButton= By.xpath("//button[starts-with(text(),'Create')]");
	By Title = By.xpath("//input[@name='title']");
	
	
	
	
	
	
	public void fillContactDetails(int row)
	{
		driver = DriverFactory.getDriver();
		try
		{
			logger.info("Started Fill Contact details Page");
			HashMap<String, String> map = new HashMap<>();
			map=ReadExcelData.readDataFromPool(row);
			logger.info("Read data from Excel and started using ");
			logger.info(map.get("Menu")+"  is open ");
			ActionUtilities.mouseHoverToElement(Menu);
			logger.info(map.get("Menu")+"  is open ");
			driver.findElement(By.xpath("//div[@id='main-nav']/div/a/span[contains(text(),'"+map.get("Menu")+"')]")).click();
			logger.info(map.get("Menu")+" gets open");
			TaskUtilities.deleteTask(map.get("Delete"));
			logger.info("Trying to click on Create button");
			ActionUtilities.mouseHoverToElement(CreateButton);
			ActionUtilities.clickButton(CreateButton, "Create Button");
			
			
			
			
			
		}
		catch (Exception e) {
			logger.error("Exceptions Occured while filling details in Cotact form "+e.getMessage());
		}
	}

}
