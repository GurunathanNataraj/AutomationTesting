package SeleniumAutomation;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

	public WebDriver LaunchITAccess() throws Exception
	{
		properties=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumAutomation\\data.properties");
		properties.load(fis);

		String browsername=properties.getProperty("browser");

		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
			ChromeOptions capability = new ChromeOptions();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

			 driver = new ChromeDriver(capability);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(properties.getProperty("URL"));

		LoginPage loginPage=new LoginPage(driver);
		loginPage.getShortNameField().sendKeys(properties.getProperty("ShortName"));
		loginPage.getPasswordField().sendKeys(properties.getProperty("Password"));
		loginPage.getLoginButton().click();



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

	public String getDate(String format)
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}



}
