package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"Features"},
		glue = {"StepDefinition","AppHooks"},
		plugin = {"pretty"},
		dryRun = false
		
		
		)
public class LoginTest {

}
