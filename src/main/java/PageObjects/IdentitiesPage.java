package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Attr;

import java.util.List;

public class IdentitiesPage {

    public WebDriver driver;
    public IdentitiesPage(WebDriver driver)
    {
        this.driver=driver;
    }
    By FieldforUserID = By.xpath("//td[@class='x-form-trigger-input-cell']/input[@type='text']");
    By SearchButtonInIdentitiesPage = By.xpath("//div[contains(@class,'search') and @role='button']");
    By UserResult = By.xpath("//table[@class='x-grid-table x-grid-table-resizer']/tbody/tr[2]");
    By EventsOption = By.xpath("//span[text()='Events']");
    By EventExecutedInLast = By.xpath("//table[@id='pastEventTbl']/tbody/tr[2]/td[2]");
    By TimeOfEventExecutedInLast = By.xpath("//table[@id='pastEventTbl']/tbody/tr[2]/td[1]");
    By AccessRequestType = By.xpath("//tr[@class=\" \"]/td[4]/div");
    By AccessRequestID = By.xpath("//tr[@class=\" \"]/td[2]/div");
    By AccessRequestExecutionStatus = By.xpath("//tr[@class=\" \"]/td[10]/div");
    By ApplicationAccountsOption = By.xpath("//span[text()='Application Accounts']");
    By EnterpriseDirectoryStatus = By.xpath("//a[normalize-space()='Enterprise Directory']/parent::td/following-sibling::td[3]/div");
    By ActiveDirectoryPGStatus = By.xpath("//a[normalize-space()='Active Directory - PG']/parent::td/following-sibling::td[3]/div");
    By ActiveDirectoryStatus = By.xpath("//a[normalize-space()='ActiveDirectory']/parent::td/following-sibling::td[3]/div");
    By EntitlementsOption = By.xpath("//span[text()='Entitlements']");
    By IntranetAccessRole = By.xpath("//a[text()='Intranet Access']");
    By O365BusinessRole = By.xpath("//a[contains(text(),'O365 for Office')]");
    By ED_GivenName = By.xpath("//td[normalize-space()='givenName']/parent::tr/td[2]");
    By ED_Initials = By.xpath("//td[normalize-space()='initials']/parent::tr/td[2]");
    By ED_Sn = By.xpath("//td[normalize-space()='sn']/parent::tr/td[2]");
    By ED_extHandle = By.xpath("//td[normalize-space()='extHandle']/parent::tr/td[2]");
    By ED_extShortName = By.xpath("//td[normalize-space()='extShortName']/parent::tr/td[2]");
    By ED_extStatus = By.xpath("//td[normalize-space()='extStatus']/parent::tr/td[2]");
    By ED_extDisabled = By.xpath("//td[normalize-space()='extDisabled']/parent::tr/td[2]");
    By ED_extGloPN = By.xpath("//td[normalize-space()='extGloPN']/parent::tr/td[2]");
    By ED_nsAccountLock = By.xpath("//td[normalize-space()='nsAccountLock']/parent::tr/td[2]");
    By ED_extStatusChangeDate = By.xpath("//td[normalize-space()='extStatusChgDate']/parent::tr/td[2]");
    By ED_extSiteType = By.xpath("//td[normalize-space()='extSiteType']/parent::tr/td[2]");
    By ED_MailRoutingAddress = By.xpath("//td[normalize-space()='mailRoutingAddress']/parent::tr/td[2]");
    By ED_Mail = By.xpath("//td[normalize-space()='mail']/parent::tr/td[2]");
    By ED_MailAlternateAddress = By.xpath("//td[normalize-space()='mailAlternateAddress']/parent::tr/td[2]");
    By ED_MailUserStatus = By.xpath("//td[normalize-space()='mailUserStatus']/parent::tr/td[2]");
    By ED_ExtReason = By.xpath("//td[normalize-space()='extReason']/parent::tr/td[2]");
    By AD_Mail = By.xpath("//td[normalize-space()='mail']/parent::tr/td[2]");
    By AD_MailNickName = By.xpath("//td[normalize-space()='mailNickname']/parent::tr/td[2]");
    By AD_ProxyAddresses = By.xpath("//td[normalize-space()='proxyAddresses']/parent::tr/td[2]");
    By AD_TargetAddress = By.xpath("//td[normalize-space()='targetAddress']/parent::tr/td[2]");
    By AD_UserPrincipalName = By.xpath("//td[normalize-space()='userPrincipalName']/parent::tr/td[2]");
    By ActiveDirectory = By.xpath("//a[contains(text(),'Active Directory')]");
    By EnterpriseDirectory = By.xpath("//a[contains(text(),'Enterprise Directory')]");
    By WorkDayStatus = By.xpath("//span[text()='Workday Status']/parent::td/parent::tr/td[2]/span");
    By AttributesTab = By.xpath("//span[text()='Attributes']");
    By ActiveApplicationsUnderApplicationAccounts = By.xpath("//div[text()='Active']/parent::td/preceding-sibling::td[3]");
    By InactiveStatus = By.xpath("//span[text()='Inactive']/parent::td/parent::tr/td[2]/span");
    By ApplicationsInApplicationAccount = By.xpath("//td[@class='identityLinkListApplicationColumn']/a");



    public WebElement getFieldforUserID() {return driver.findElement(FieldforUserID);}
    public WebElement getSearchButtonInIdentitiesPage(){return driver.findElement(SearchButtonInIdentitiesPage);}
    public WebElement getUserResult() {return  driver.findElement(UserResult);}
    public WebElement getEventsOption() {return driver.findElement(EventsOption);}
    public WebElement getEventExecutedInLast() {return driver.findElement(EventExecutedInLast);}
    public WebElement getTimeOfEventExecutedInLast(){return driver.findElement(TimeOfEventExecutedInLast);}
    public WebElement getAccessRequestType() {return driver.findElement(AccessRequestType);}
    public WebElement getAccessRequestID() {return driver.findElement(AccessRequestID);}
    public WebElement getAccessRequestExecutionStatus() {return driver.findElement(AccessRequestExecutionStatus);}
    public WebElement getApplicationAccountsOptions() {return driver.findElement(ApplicationAccountsOption);}
    public WebElement getEnterpriseDirectoryStatus() {return driver.findElement(EnterpriseDirectoryStatus);}
    public WebElement getActiveDirectoryPGStatus() {return driver.findElement(ActiveDirectoryPGStatus);}
    public WebElement getActiveDirectoryStatus() {return driver.findElement(ActiveDirectoryStatus);}
    public WebElement getEntitlementsOption() {return driver.findElement(EntitlementsOption);}
    public WebElement getIntranetAccessRole() {return driver.findElement(IntranetAccessRole);}
    public WebElement getO365BusinessRole() {return driver.findElement(O365BusinessRole);}
    public WebElement getED_GivenName() {return driver.findElement(ED_GivenName);}
    public WebElement getED_Initials() {return driver.findElement(ED_Initials);}
    public WebElement getED_Sn() {return driver.findElement(ED_Sn);}
    public WebElement getED_extHandle(){return driver.findElement(ED_extHandle);}
    public WebElement getED_extShortName(){return driver.findElement(ED_extShortName);}
    public WebElement getED_extStatus(){return driver.findElement(ED_extStatus);}
    public WebElement getED_extDisabled(){return driver.findElement(ED_extDisabled);}
    public WebElement getED_extGloPN() {return driver.findElement(ED_extGloPN);}
    public WebElement getED_nsAccountLock() {return driver.findElement(ED_nsAccountLock);}
    public WebElement getED_extStatusChangeDate() {return driver.findElement(ED_extStatusChangeDate);}
    public WebElement getED_extSiteType() {return driver.findElement(ED_extSiteType);}
    public WebElement getED_MailRoutingAddress() {return driver.findElement(ED_MailRoutingAddress);}
    public WebElement getED_Mail() {return driver.findElement(ED_Mail);}
    public WebElement getED_MailAlternateAddress() {return driver.findElement(ED_MailAlternateAddress);}
    public WebElement getED_MailUserStatus() {return driver.findElement(ED_MailUserStatus);}
    public WebElement getED_ExtReason(){return driver.findElement(ED_ExtReason);}
    public WebElement getAD_Mail(){return driver.findElement(AD_Mail);}
    public WebElement getAD_MailNickName() {return driver.findElement(AD_MailNickName);}
    public WebElement getAD_ProxyAddresses() {return driver.findElement(AD_ProxyAddresses);}
    public WebElement getAD_TargetAddress() {return driver.findElement(AD_TargetAddress);}
    public WebElement getAD_UserPrincipalName() {return driver.findElement(AD_UserPrincipalName);}
    public WebElement getActiveDirectory(){return driver.findElement(ActiveDirectoryStatus);}
    public WebElement getEnterpriseDirectory(){return driver.findElement(EnterpriseDirectory);}
    public WebElement getWorkDayStatus() {return driver.findElement(WorkDayStatus);}
    public WebElement getAttributesTab() {return driver.findElement(AttributesTab);}
    public List<WebElement> getActiveApplicationsUnderApplicationAccounts() {return driver.findElements(ActiveApplicationsUnderApplicationAccounts);}
    public WebElement getInActiveStatus() {return driver.findElement(InactiveStatus);}
    public List<WebElement> getApplicationsInApplicationAccount() {return driver.findElements(ApplicationsInApplicationAccount);}





}
