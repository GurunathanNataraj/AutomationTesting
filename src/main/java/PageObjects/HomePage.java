package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
public WebDriver driver;


	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	By HomeButton = By.xpath("//li[normalize-space()='Home']");
	By MyWorkDropDown = By.xpath("//li[@class='dropdown hidden-xs hidden-sm'][1]");
	By SetupDropdown = By.xpath("//li[@class='dropdown hidden-xs hidden-sm'][5]");
	By IdentitiesDropdown = By.xpath("//li[@class='dropdown hidden-xs hidden-sm'][2]");
	By IntelligenceDropdown = By.xpath("//li[@class='dropdown hidden-xs hidden-sm'][4]");
	By AccessRequestsOption = By.xpath("//a[normalize-space()='Access Requests']");
	By TasksOption = By.xpath("//li[normalize-space()='Tasks']");
	By IdentityWarehouseOption = By.xpath("//li[normalize-space()='Identity Warehouse']");
	By AdvancedAnalytics = By.xpath("//li[normalize-space()='Advanced Analytics']");



	public WebElement getHomeButton()
	{
		return driver.findElement(HomeButton);
	}
	public WebElement getSetupDropdown() { return driver.findElement(SetupDropdown); }
	public WebElement getTasksOption() { return driver.findElement(TasksOption); }
	public WebElement getIdentitiesDropdown() { return driver.findElement(IdentitiesDropdown);}
	public WebElement getIdentityWarehouseOption() { return driver.findElement(IdentityWarehouseOption);}
	public WebElement getMyWorkDropDown() { return driver.findElement(MyWorkDropDown);}
	public WebElement getAccessRequestOption() { return driver.findElement(AccessRequestsOption);}
	public WebElement getIntelligenceDropdown() {return driver.findElement(IntelligenceDropdown);}
	public WebElement getAdvancedAnalyticsOption() {return driver.findElement(AdvancedAnalytics);}

	
	

	
	
}




