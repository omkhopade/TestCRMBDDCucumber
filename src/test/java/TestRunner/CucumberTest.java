package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"Features//Task.feature"},
		glue = {"StepDefinition","AppHooks"},
		plugin = {"pretty",
				
				"html:target/cucumber-reports/cucumber-reports.html"},
		dryRun = false
		)
public class CucumberTest {
	
	
	

}
