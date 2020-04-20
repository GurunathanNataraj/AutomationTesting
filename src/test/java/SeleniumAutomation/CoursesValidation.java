package SeleniumAutomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.HomePage;

public class CoursesValidation extends BaseClass{
	
	@BeforeTest()
	public void Initialize() throws Exception
	{
		driver=InitiateWebDriver();
		driver.get(properties.getProperty("URL"));
	}

	@Test
	public void TextValidation()
	{
		HomePage homepage=new HomePage(driver);
		if(homepage.FeaturedCourses().isDisplayed())
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
}
