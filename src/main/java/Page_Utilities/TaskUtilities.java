package Page_Utilities;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DriverFactory.DriverFactory;
import Utilities.ActionUtilities;
import Utilities.ReadExcelData;


//this method is used to create task in TestCRM webpage


public class TaskUtilities extends DriverFactory{

private static 	By Menu = By.xpath("//span[contains(text(),'Home')]/ parent :: a");
private static	By rows= By.xpath("//div[@class='table-wrapper']/table//tbody//tr");
private static	By DeleteButton = By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/button/i[@class='trash icon']");
private static	By ConfirmDelete = By.xpath("//div[contains(text(),'Confirm Deletion')]/parent :: div // button[2]");
private static	By NoRecords = By.xpath("//div[@class='table-wrapper']/table/tbody/tr//p");
private static	By CreateButton= By.xpath("//button[starts-with(text(),'Create')]");
private static	By Title = By.xpath("//input[@name='title']");
private static	By AssingedTo =By.xpath("//div[@class='ui field']/label[text()='Assigned To']/ parent :: div /div");
private static	By AssingedToOmkar=By.xpath("//div[@class='ui field']/label[text()='Assigned To']/ parent :: div /div/div[2]");
private static	By SaveButton= By.xpath("//button[text()='Save']");
private static	By TaskName=By.xpath("//div[@id='dashboard-toolbar']//span");









	public static   WebDriver driver;
	public  static ActionUtilities action;
	public  TaskUtilities(WebDriver driver)
	{
		TaskUtilities.driver=driver;
		TaskUtilities.action= new ActionUtilities(driver);
	}

	public static void deleteTask(String DeleteTask)
	{


		try {

			Thread.sleep(3000);
			action.waitForElements(rows);
			List<WebElement> totalrows=driver.findElements(rows);
			logger.info("Deleting all data present in task Menu");
			if(DeleteTask.equalsIgnoreCase("true")) {
				if (totalrows.size()!=1)
				{
					for (int i=0;i<totalrows.size();i++)
					{
						
						action.clickButton(DeleteButton,"Delete Record");
						action.poolingWaitForElement(ConfirmDelete);
						action.clickButton(ConfirmDelete, "Confirm Delete Button");

					}

				}
				else
				{

					logger.info("No records available to delete");

				}
			}
			else
			{
				logger.info("Without Deleting Existing task will continue with next stepes");
			}
		}
		catch (Exception e) {
			logger.error("Exception occured while Deleting data "+ e.getMessage());
		}

	}

	public  String  fillTaskDetails(int row)
	{
		try {
			logger.info("Started Fill details on Task Page");
			ActionUtilities action = new ActionUtilities(driver);
			HashMap<String, String> map = new HashMap<>();
			map=ReadExcelData.readDataFromPool(row);
			System.out.println("Menu is : "+map.get("Menu"));
			logger.info("Read data from Excel and started using ");
			action.mouseHoverToElement(Menu);
			logger.info("Menu gets open ");
			driver.findElement(By.xpath("//div[@id='main-nav']/div/a/span[contains(text(),'"+map.get("Menu")+"')]")).click();
			logger.info(map.get("Menu")+" gets open");
			deleteTask(map.get("Delete"));
			logger.info("Trying to click on Create button");
			action.mouseHoverToElement(CreateButton);
			action.clickButton(CreateButton,"Create Button");
			action.sendValuesToField(map.get("Title"),"Title", Title);
			action.clickButton(AssingedTo, "Assinged_To");
			action.clickButton(AssingedToOmkar,"Assinged To Dropdown");
			action.clickButton(SaveButton, "Save Button");

		}
		catch (Exception e) {

			logger.info("Exception occure while filling details of tasks "+e.getMessage());
		}

		String Title=action.getTextValue(TaskName);
		logger.info("Title is created with Name "+Title);
		return Title;


	}




}
