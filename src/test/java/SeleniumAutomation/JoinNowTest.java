package SeleniumAutomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.HomePage;

public class JoinNowTest extends BaseClass {
	
	@BeforeTest
	public void Initialize() throws Exception
	{
		driver=InitiateWebDriver();
		driver.get(properties.getProperty("URL"));
	}

	
	@Test
	public void Test()
	{
		HomePage homepage=new HomePage(driver);
		homepage.JoinNowButton().click();
	}
	
	@AfterTest
	public void Teardown()
	{
		driver.close();
	}
}
