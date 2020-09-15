package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TasksPage {

    public WebDriver driver;
    public TasksPage(WebDriver driver)
    {
        this.driver=driver;
    }

    By TasksNameField = By.xpath("//input[@id='tasksSearchField-inputEl']");
    By SearchButton = By.xpath("//div[@role='button' and contains(@class,'search')]");
    By IdentityRefreshTaskforSingleUser = By.xpath("//div[text()='PG - Identity refresh and role refresh - Single user']");
    By UserFieldInTasksPage = By.xpath("//input[@id='editForm:filter_str']");
    By SaveAndExecuteButton = By.xpath("//input[@id='editForm:validateBeforeExecuteButton']");
    By ProcessEventsCheckBox = By.xpath("//td[normalize-space()='Process events']/following-sibling::td[2]/input[@type='checkbox']");
    By OKbuttonInPopup = By.xpath("//button[@role='button' and @hidefocus='true']");

    public WebElement getTasksNameField()
    {
        return driver.findElement(TasksNameField);
    }
    public WebElement getSearchButton()
    {
        return driver.findElement(SearchButton);
    }
    public WebElement getIdentityRefreshTaskForSingleUser()
    {
        return driver.findElement(IdentityRefreshTaskforSingleUser);
    }
    public WebElement getUserFieldInTasksPage()
    {
        return driver.findElement(UserFieldInTasksPage);
    }
    public WebElement getSaveandExecuteButton()
    {
        return driver.findElement(SaveAndExecuteButton);
    }
    public WebElement getProcessEventsCheckbox()
    {
        return driver.findElement(ProcessEventsCheckBox);
    }
    public WebElement getOKButtonInPopup()
    {
        return driver.findElement(OKbuttonInPopup);
    }
}
