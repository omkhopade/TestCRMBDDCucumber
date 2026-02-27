package TestRunner;

import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		features = {"Features"},
		glue = {"StepDefinition","AppHooks"},
		plugin = {"pretty"},
		dryRun = false
		
		
		)
public class LoginTest {

}
