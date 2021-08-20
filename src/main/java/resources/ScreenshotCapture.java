package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotCapture {

	public void captureScreenOfPage(String fileName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destination= System.getProperty("user.dir")+"\\Output\\"+fileName+".jpg";
		FileUtils.copyFile(source, new File(destination));
	}
	
public void captureScreenOfElement(String fileName, WebElement element) throws IOException {
		
		//TakesScreenshot ts= (TakesScreenshot)driver;
		File source=element.getScreenshotAs(OutputType.FILE);
		String destination= System.getProperty("user.dir")+"\\Output\\"+fileName+".jpg";
		FileUtils.copyFile(source, new File(destination));
	}


}
