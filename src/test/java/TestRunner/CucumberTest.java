package TestRunner;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {"Features//Task.feature"},
		glue = {"StepDefinition","AppHooks"},
		plugin = {"pretty",
				
				"html:target/cucumber-reports/cucumber-reports.html"},
		dryRun = false,
		monochrome = true
		)
public class CucumberTest {
	
	
	

}
