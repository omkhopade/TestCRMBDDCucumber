package Utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;

public class ScreenShotsUtils extends DriverFactory{

	public WebDriver driver;


	public ScreenShotsUtils(WebDriver driver)
	{
		this.driver=driver;
	}


	public String getCaptureScreenShot(String actionName)
	{
		String pathfile=null;
		String pathuri=null;
		String BuildUrl=null;
		try
		{
			if(actionName.contains(" "))
			{
				actionName=actionName.replaceAll(" ", "_");
			}

			Path screenshotPath = Paths.get("target", "screenshots", actionName + ".png");
			Files.createDirectories(screenshotPath.getParent());
			pathfile = screenshotPath.toString();
			pathuri  = screenshotPath.toUri().toString();
			logger.info("Catpturing screenshot for "+actionName);
			TakesScreenshot ts =(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(pathfile));
			logger.info("screen shot cature successfully for ");
			BuildUrl=System.getenv("BUILD_URL");
			if(BuildUrl!=null)
			{
				System.out.println("inside build path");
				logger.info(BuildUrl+pathfile);
			}
			logger.info("screen shot cature successfully for "+actionName);

		}

		catch (Exception e) {
			logger.error("Exception occured while caturing screenshots "+e.getMessage());
		}

		return BuildUrl+pathfile;
	}


}
