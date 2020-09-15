package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IntelligencePage {

    public WebDriver driver;
    public IntelligencePage(WebDriver driver)
    {
        this.driver=driver;
    }

    By SearchType = By.xpath("//input[@id='advAnalyticsSelector-inputEl']");
    By AuditOption = By.xpath("//li[@role='option' and text()='Audit']");
    By ActionDropdown = By.xpath("//input[@id='auditActionSuggestCmp-inputEl']");
    By RespectiveOption = By.xpath("//div[@class='baseSearch x-boundlist-item']");
    By TargerUserField = By.xpath("//input[@id='targetSuggestCmp-inputEl']");
    By RunSearchButton = By.xpath("//span[@id='preAuditSearchBtn-btnInnerEl']");
    By DateHeader = By.xpath("//div[@id='auditSearchResultsGrid']/div[contains(@class,'header')]/div/div/div[2]");
    By AdvancedAnalyticsResult = By.xpath("//table[@class='x-grid-table x-grid-table-resizer']/tbody/tr[2]");
    By AuditEventDeatil_Action = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Action']/following-sibling::td");
    By AuditEventDetail_Source = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Source']/following-sibling::td");
    By AuditEventDetail_Target = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Target']/following-sibling::td");
    By AuditEventDetail_Value1 = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Value 1']/following-sibling::td");
    By AuditEventDetail_Value4 = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Value 4']/following-sibling::td");
    By AuditEventDetail_LOASuccessfulForApplications = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Attributes']/following-sibling::td/table/tbody/tr/td[text()='LOA Successful For Applications']/parent::tr/td[2]");
    By AuditEventDetail_LeaverSuccessfulForApplications = By.xpath("//table[@class='spPaddedTable']/tbody/tr/td[normalize-space()='Attributes']/following-sibling::td/table/tbody/tr/td[text()='Leaver Successful For Applications']/parent::tr/td[2]");
    By AuditEventDetail_CloseButton = By.xpath("//span[text()='Close']");


    public WebElement getSearchType(){return driver.findElement(SearchType);}
    public WebElement getAuditOption(){return driver.findElement(AuditOption);}
    public WebElement getActionDropdown(){return driver.findElement(ActionDropdown);}
    public WebElement getRespectiveOption(){return driver.findElement(RespectiveOption);}
    public WebElement getTargetUserField(){return driver.findElement(TargerUserField);}
    public WebElement getRunSearchButton(){return driver.findElement(RunSearchButton);}
    public WebElement getAdvancedAnalyticsResult(){return driver.findElement(AdvancedAnalyticsResult);}
    public WebElement getDateHeader(){return driver.findElement(DateHeader);}
    public WebElement getAuditEventDeatil_Action(){return driver.findElement(AuditEventDeatil_Action);}
    public WebElement getAuditEventDetail_Source(){return driver.findElement(AuditEventDetail_Source);}
    public WebElement getAuditEventDetail_Target(){return driver.findElement(AuditEventDetail_Target);}
    public WebElement getAuditEventDetail_Value1(){return driver.findElement(AuditEventDetail_Value1);}
    public WebElement getAuditEventDetail_Value4(){return driver.findElement(AuditEventDetail_Value4);}
    public WebElement getAuditEventDetail_LOASuccessfulForApplications(){return driver.findElement(AuditEventDetail_LOASuccessfulForApplications);}
    public WebElement getAuditEventDetail_LeaverSuccessfulForApplications(){return driver.findElement(AuditEventDetail_LeaverSuccessfulForApplications);}
    public WebElement getAuditEventDetail_CloseButton(){return driver.findElement(AuditEventDetail_CloseButton);}




}
