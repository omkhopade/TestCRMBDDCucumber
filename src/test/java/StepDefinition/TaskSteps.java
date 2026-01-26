package StepDefinition;

import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Pages.LoginPage;
import Utilities.ActionUtilities;
import Utilities.TaskUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskSteps extends DriverFactory{
	
	WebDriver driver;
	String Task=null;
	TaskUtilities  task= new TaskUtilities(DriverFactory.getDriver());
	LoginPage login = new LoginPage(DriverFactory.getDriver());
	
	@Given("Login into Applicationss")
	public void login_into_applicationss() {
		login.login();
	}

	@Given("Home Page is Open")
	public void home_page_is_open() {
	  driver=DriverFactory.getDriver();
	  ActionUtilities.compareValues("Free CR", driver.getTitle());
	}
	@When("User opens the Task take and Create new Task")
	public void user_opens_the_task_take_and_create_new_task() {
		Task=task.fillTaskDetails(1);
		System.out.println("task name is "+Task);
	    
	}
	@Then("User is able to search newly created task")
	public void user_is_able_to_search_newly_created_task() {
	  
		login.logout(true);
	}
	
	
	
}
