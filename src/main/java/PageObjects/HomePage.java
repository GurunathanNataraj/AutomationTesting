package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
private WebDriver driver;


	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	By SignIn = By.xpath("//a[text()='Login']");
	By FeaturedCourses = By.xpath("//h2[text()='Featured Courses']");
	By JoinNowButton = By.xpath("//a[text()='JOIN NOW']");
	
	
	public WebElement SignIn()
	{
		return driver.findElement(SignIn);
	}
	
	public WebElement FeaturedCourses()
	{
		return driver.findElement(FeaturedCourses);
	}
	
	public WebElement JoinNowButton()
	{
		return driver.findElement(JoinNowButton);
	}
	
	
}




