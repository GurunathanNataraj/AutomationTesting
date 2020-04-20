package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import PageObjects.SignInPage;

public class InvokeTest extends BaseClass{
	
	@BeforeTest
	public void Initialize() throws Exception
	{
		driver=InitiateWebDriver();
		driver.get(properties.getProperty("URL"));
	}

	@Test(dataProvider="getData")
	public void invoke(String EmailId,String Password) throws Exception
	{
		HomePage homepage=new HomePage(driver);
		SignInPage signinpage=new SignInPage(driver);
		
		wait.until(ExpectedConditions.elementToBeClickable(homepage.SignIn()));
		homepage.SignIn().click();
		signinpage.EmailId().sendKeys(EmailId);
		signinpage.Password().sendKeys(Password);
		signinpage.LogInButton().click();
		
	}
	
	@AfterTest
	public void TearDown()
	{
		driver.close();
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data=new Object[1][2];
		data[0][0]="gurunathannataraj@gmail.com";
		data[0][1]="guruspartan77";
		
		return data;
	}
	
	
}

