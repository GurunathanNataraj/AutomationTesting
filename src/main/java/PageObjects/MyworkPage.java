package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyworkPage {
    public WebDriver driver;
    public MyworkPage(WebDriver driver)
    {
        this.driver=driver;
    }
    By AccessRequestsExecutedAtLast = By.xpath("//div[contains(@class,'text-ellipsis identity-request-type-header')]");
    By SearchField = By.xpath("//input[@id='cardListSearchInput']");
    By SearchButton = By.xpath("//button[@id='cardListSearchBtn']");
    By RequestID = By.xpath("//span[contains(@id,'identityRequestId')]");
    By AccessRequestAlert = By.xpath("//div[contains(@class,'alert-success alert')]");


    public List<WebElement> getAccessRequestExecutedAtLast(){return driver.findElements(AccessRequestsExecutedAtLast);}
    public WebElement getSearchField(){return driver.findElement(SearchField);}
    public WebElement getSearchButton() {return  driver.findElement(SearchButton);}
    public WebElement getRequestID() {return driver.findElement(RequestID);}
    public WebElement getAccessRequestAlert() {return driver.findElement(AccessRequestAlert);}


}
