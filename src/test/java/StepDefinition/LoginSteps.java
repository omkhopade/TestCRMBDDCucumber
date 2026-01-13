package StepDefinition;

import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Pages.LoginPage;
import Utilities.ActionUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends DriverFactory{

	WebDriver driver;
	LoginPage loginpage = new LoginPage(DriverFactory.getDriver());

	@Given("Verify Login Page Title with {string}")
	public void verify_login_page_title_with(String string) {
		driver=DriverFactory.getDriver();
		ActionUtilities.compareValues("Free CR", driver.getTitle());


	}

	@When("User Login into Application")
	public void User_Login_into_Application() {

		loginpage.login();
	}


	@Then("Home Page Open")
	public void home_page_open() throws InterruptedException {

		ActionUtilities.compareValues("Free CRM", driver.getTitle());
		Thread.sleep(10000);
	}
	
	@Then("Logout from Application")
	public void logout_from_application() {
	   loginpage.logout(false);
	  
	   loginpage.login();
	   loginpage.logout(true);
	}

}
