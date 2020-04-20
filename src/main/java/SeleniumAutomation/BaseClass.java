package SeleniumAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties properties;
	
	
	public WebDriver InitiateWebDriver() throws Exception
	{
		properties=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumAutomation\\data.properties");
		properties.load(fis);
		
		String browsername=properties.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
			driver=new ChromeDriver();
			
		}
		
		else if(browsername.equalsIgnoreCase("IE"))
		{
			
		}
		
		else if(browsername.equalsIgnoreCase("edge"))
		{
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,30);
		
		
		
		return driver;
	}
	
	public void TakeScreenshot(String MethodName) throws Exception
	{
		System.out.println("inside screenshot method");
		TakesScreenshot image=(TakesScreenshot)(driver);
		File SourceFile=image.getScreenshotAs(OutputType.FILE);
		System.out.println(System.getProperty("user.dir")+"/Result/Screenshots/");
		File DestinationFile=new File(System.getProperty("user.dir")+"\\Result\\Screenshots\\"+MethodName+System.currentTimeMillis()+".png");
		FileUtils.copyFile(SourceFile, DestinationFile);
	}

}
