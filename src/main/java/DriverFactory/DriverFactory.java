package DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j 
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
	public static Properties prop;
	public static Logger logger;
	
	
	public static WebDriver initialized ()
	{
		logger = LogManager.getLogger(DriverFactory.class);
		try {
			FileInputStream file = new FileInputStream(new File(".\\src\\test\\java\\Config\\Config.properties"));
			logger.info("Started reading config file ");
			prop= new Properties();
			prop.load(file);
			logger.info("properties object loaded and reading data from config properties ");
		} catch (Exception e) {
			logger.error("caught exception "+e.getMessage());
			
		}
		
		if(prop.get("browser").equals("chrome")) {
			
			logger.info("Started Chrome Driver");
			WebDriverManager.chromedriver().setup();
			logger.info("set up tl driver to Chromedriver");
			tldriver.set(new ChromeDriver());
			
		}
		else if (prop.get("browser").equals("msedge")) {
			WebDriverManager.edgedriver().setup();
			tldriver.set(new EdgeDriver());
		}
		
		return getDriver();

	}
	
	
	
	public static WebDriver getDriver()
	{
		return tldriver.get();
	}

}
