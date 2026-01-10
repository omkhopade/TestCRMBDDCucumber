package AppHooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends DriverFactory{

	WebDriver driver;
	
	@Before
	public void setup()
	{
		driver =DriverFactory.initialized();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		logger.info("launches url "+prop.getProperty("url"));
		
	}
	
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
