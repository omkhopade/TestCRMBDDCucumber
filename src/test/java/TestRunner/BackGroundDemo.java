package TestRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
			features = {"Features/CreateContact.feature"},
			glue = {"StepDefinition" ,"AppHooks"},
			dryRun = false,
			plugin = {"pretty"},
			monochrome = true
		
		
		)

public class BackGroundDemo extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
	
	
}
