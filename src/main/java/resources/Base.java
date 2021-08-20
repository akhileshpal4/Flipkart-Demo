package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		
		//Get Property value
		prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		//String browserName=System.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Akhilesh\\Selenium\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "C:\\Akhilesh\\Selenium\\geckodriver-v0.29.1-win64\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("ie")) {
			//System.setProperty("webdriver.ie.driver", "C:\\Akhilesh\\Selenium\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe");
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}else if(browserName.equals("saucelab")) {
			MutableCapabilities sauceOptions = new MutableCapabilities();
			
			sauceOptions.setCapability("username", System.getenv("oauth-achieversbyap-3c32f"));
			sauceOptions.setCapability("access_key", System.getenv("fcf3b1d9-1acd-4171-aef9-155cac3802ac"));
			//sauceOptions.setCapability("name", testInfo.getDisplayName());
			//sauceOptions.setCapability("browserVersion", "latest");
			URL url = new URL("https://oauth-achieversbyap-3c32f:fcf3b1d9-1acd-4171-aef9-155cac3802ac@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			
			SafariOptions browserOptions = new SafariOptions();
			browserOptions.setCapability("platformName", "macOS 11");
			browserOptions.setCapability("browserVersion", "14");
			browserOptions.setCapability("sauce:options", sauceOptions);
			
			driver=new RemoteWebDriver(url,browserOptions);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

}
