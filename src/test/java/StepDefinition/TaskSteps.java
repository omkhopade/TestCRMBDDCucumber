package StepDefinition;

import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Page_Utilities.LoginPage;
import Page_Utilities.TaskUtilities;
import Utilities.ActionUtilities;
import Utilities.ContactUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskSteps extends DriverFactory{
	
	WebDriver driver;
	ContactUtilities contact;
	LoginPage loginutils;
	TaskUtilities task_utils;
	
	@Given("Login into Applicationss")
	public void login_into_applicationss() {
		driver=DriverFactory.getDriver();
		contact = new ContactUtilities();
		task_utils = new TaskUtilities(driver);
		loginutils= new LoginPage(driver);
		loginutils.login();
	}

	@Given("Home Page is Open")
	public void home_page_is_open() {
	 
	  ActionUtilities.compareValues("Free CR", driver.getTitle());
	}
	@When("User opens the Task take and Create new Task")
	public void user_opens_the_task_take_and_create_new_task() {
		task_utils.fillTaskDetails(1);
		
	    
	}
	@Then("User is able to search newly created task")
	public void user_is_able_to_search_newly_created_task() {
	  
		System.out.println("Record found successfully ");
		loginutils.logout(true);
	}
	
	@When("User opens the Contact page and create new contact")
	public void user_opens_the_contact_page_and_create_new_contact() {
		
		contact.fillContactDetails(2);
	}
	
	
}
