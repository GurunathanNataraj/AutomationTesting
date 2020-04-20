package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {
	
	private WebDriver driver;
	
	public SignInPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By EmailId = By.id("user_email");
	By Password = By.id("user_password");
	By LogInButton = By.name("commit");
	
	public WebElement EmailId()
	{
		return driver.findElement(EmailId);
	}
	
	public WebElement Password()
	{
		return driver.findElement(Password);
	}
	
	public WebElement LogInButton()
	{
		return driver.findElement(LogInButton);
	}
	
	
	

}
