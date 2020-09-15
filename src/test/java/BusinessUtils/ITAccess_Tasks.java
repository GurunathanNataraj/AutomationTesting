package BusinessUtils;
import PageObjects.*;
import SeleniumAutomation.BaseClass;

import junit.framework.Assert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ITAccess_Tasks extends BaseClass {
HomePage homepage;
TasksPage taskspage;
IdentitiesPage identitiesPage;
MyworkPage myworkPage;
IntelligencePage intelligencepage;
public void NavigateToTasksPage()throws Exception
{
     homepage =new HomePage(driver);
    taskspage = new TasksPage(driver);
    homepage.getSetupDropdown().click();
    homepage.getTasksOption().click();
    Assert.assertTrue(taskspage.getTasksNameField().isDisplayed());

}
    public void ExecuteIdentityRefreshTaskForSingleUser(String User)throws Exception
    {
        Thread.sleep(10000);
        taskspage = new TasksPage(driver);
        taskspage.getTasksNameField().sendKeys("PG - Identity refresh and role refresh - Single user");
        taskspage.getSearchButton().click();
        taskspage.getIdentityRefreshTaskForSingleUser().click();
        taskspage.getUserFieldInTasksPage().clear();
        taskspage.getUserFieldInTasksPage().sendKeys("name==\""+User+"\"");
        if(!taskspage.getProcessEventsCheckbox().isSelected())
        {
            taskspage.getProcessEventsCheckbox().click();
        }
        taskspage.getSaveandExecuteButton().click();
        Thread.sleep(2000);
        taskspage.getOKButtonInPopup().click();
        Thread.sleep(2000);

    }

    public void SearchUserInIdentitiesPage(String user)throws Exception
    {
        Thread.sleep(2000);
        homepage = new HomePage(driver);
        identitiesPage = new IdentitiesPage(driver);
        homepage.getIdentitiesDropdown().click();
        homepage.getIdentityWarehouseOption().click();
        identitiesPage.getFieldforUserID().sendKeys(user);
        identitiesPage.getSearchButtonInIdentitiesPage().click();
        Thread.sleep(5000);
        identitiesPage.getUserResult().click();


    }

    public String ValidateTriggeredEvent(String Event)throws Exception
    {
        Thread.sleep(120000);
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEventsOption().click();
        Assert.assertEquals(Event,identitiesPage.getEventExecutedInLast().getText().trim());
        //Assert.assertTrue(identitiesPage.getTimeOfEventExecutedInLast().getText().contains(getDate("MMM d, yyyy h")));
        String CurrentTime = getDate("MMM d, yyyy h");
        Integer hour = Integer.parseInt(CurrentTime.substring(12).trim());
        Integer hourplusone;
        if(hour==12)
        {
            hourplusone = 1;
        }
        else
        {
            hourplusone = hour+1;
        }

        if(!(identitiesPage.getTimeOfEventExecutedInLast().getText().contains(getDate("MMM d, yyyy")+" "+hour)
        || identitiesPage.getTimeOfEventExecutedInLast().getText().contains(getDate("MMM d, yyyy")+" "+hourplusone)))
        {
            Assert.assertTrue(false);
        }
       /* System.out.println(getDate("MMM d, yyyy")+" "+hour);
        System.out.println(getDate("MMM d, yyyy")+" "+hourplusone);*/

        return identitiesPage.getTimeOfEventExecutedInLast().getText().trim();
    }

    public void ValidateEventNotTriggered(String Event)throws Exception
    {
        Boolean flag=false;
        identitiesPage = new IdentitiesPage(driver);
        Thread.sleep(60000);
        identitiesPage.getEventsOption().click();

        try
        {
            identitiesPage.getEventExecutedInLast().isDisplayed();
            flag = true;
        }
        catch (Exception e)
        {
            System.out.println("Element not present");
        }
        if(flag) {
            Assert.assertNotSame(Event, identitiesPage.getEventExecutedInLast().getText().trim());
        }


    }

    public void AccessRequestType(String AccessRequest)
    {

        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEventsOption().click();
        Assert.assertEquals(AccessRequest,identitiesPage.getAccessRequestType().getText().trim());
    }
    public void NavigateToApplicationAccounts()
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getApplicationAccountsOptions().click();
    }

    public String getEnterPriseDirectoryStatus()
    {
        identitiesPage = new IdentitiesPage(driver);
        return identitiesPage.getEnterpriseDirectoryStatus().getText();
    }
    public void EnterpriseDirectoryAttributesValidation(String givenName,String initials,String sn,String extHandle,String extShortName,String extStatus,
                                                        String extDisabled,String nsAccountLock,String extSiteType,String mailRoutingAddress,
                                                        String mail,String mailAlternateAddress1,String mailAlternateAddress2,String mailAlternateAddress3,String mailUserStatus)
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEnterpriseDirectory().click();
        Assert.assertEquals(givenName,identitiesPage.getED_GivenName().getText().trim());
        Assert.assertEquals(initials,identitiesPage.getED_Initials().getText().trim());
        Assert.assertEquals(sn,identitiesPage.getED_Sn().getText().trim());
        Assert.assertEquals(extHandle,identitiesPage.getED_extHandle().getText().trim());
        Assert.assertEquals(extShortName,identitiesPage.getED_extHandle().getText().trim());
        Assert.assertEquals(extStatus ,identitiesPage.getED_extStatus().getText().trim());
        Assert.assertEquals(extDisabled,identitiesPage.getED_extDisabled().getText().trim());
        Assert.assertNotNull(identitiesPage.getED_extGloPN().getText().trim());
        Assert.assertEquals(nsAccountLock,identitiesPage.getED_nsAccountLock().getText().trim());
        Assert.assertEquals(getDate("yyyyMMdd"),identitiesPage.getED_extStatusChangeDate().getText().trim());
        Assert.assertEquals(extSiteType ,identitiesPage.getED_extSiteType().getText().trim());
        Assert.assertEquals(mailRoutingAddress ,identitiesPage.getED_MailRoutingAddress().getText().trim());
        Assert.assertEquals(mail,identitiesPage.getED_Mail().getText().trim());
        Assert.assertEquals(mailUserStatus ,identitiesPage.getED_MailUserStatus().getText().trim());
        Assert.assertTrue(identitiesPage.getED_MailAlternateAddress().getText().contains(mailAlternateAddress1));
        Assert.assertTrue(identitiesPage.getED_MailAlternateAddress().getText().contains(mailAlternateAddress2));
        Assert.assertTrue(identitiesPage.getED_MailAlternateAddress().getText().contains(mailAlternateAddress3));
    }

    public void EnterpriseDirectoryAttributesValidationForCancelEvent(String extDisabled,String extStatus)
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEnterpriseDirectory().click();
        Assert.assertEquals(getDate("yyyyMMdd"),identitiesPage.getED_extStatusChangeDate().getText().trim());
        Assert.assertEquals(extDisabled,identitiesPage.getED_extDisabled().getText().trim());
        Assert.assertEquals(extStatus,identitiesPage.getED_extStatus().getText().trim());
        Assert.assertFalse(identitiesPage.getED_MailRoutingAddress().isDisplayed());
        Assert.assertFalse(identitiesPage.getED_Mail().isDisplayed());
        Assert.assertFalse(identitiesPage.getED_MailAlternateAddress().isDisplayed());



    }

    public String getActiveDirectoryPGStatus()
    {
        identitiesPage = new IdentitiesPage(driver);
        return identitiesPage.getActiveDirectoryPGStatus().getText();
    }

    public String getActiveDirectoryStatus()
    {
        identitiesPage = new IdentitiesPage(driver);
        return identitiesPage.getActiveDirectory().getText().trim();
    }

    public void ValidateActiveDirectoryAttributes(String mail,String mailnickname,String proxyAddress1,String proxyAddress2,String proxyAddress3,String proxyAddress4,
                                                  String targetAddress,String UserPrincipalName)
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getActiveDirectory().click();
        Assert.assertEquals(mail,identitiesPage.getAD_Mail().getText().trim());
        Assert.assertEquals(mailnickname,identitiesPage.getAD_MailNickName().getText().trim());
        Assert.assertTrue(identitiesPage.getAD_ProxyAddresses().getText().contains(proxyAddress1));
        Assert.assertTrue(identitiesPage.getAD_ProxyAddresses().getText().contains(proxyAddress2));
        Assert.assertTrue(identitiesPage.getAD_ProxyAddresses().getText().contains(proxyAddress3));
        Assert.assertTrue(identitiesPage.getAD_ProxyAddresses().getText().contains(proxyAddress4));
        Assert.assertEquals(targetAddress,identitiesPage.getAD_TargetAddress().getText().trim());
        Assert.assertEquals(UserPrincipalName,identitiesPage.getAD_UserPrincipalName().getText().trim());

    }

    public void NavigateToEntitlements()
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEntitlementsOption().click();
    }

    public void NavigateToAccessRequests()
    {
        homepage = new HomePage(driver);
        homepage.getMyWorkDropDown().click();
        homepage.getAccessRequestOption().click();
    }

    public void ValidateAccessRequest(String request)throws Exception
    {
        myworkPage = new MyworkPage(driver);

        Thread.sleep(5000);
        List<WebElement> elelist = myworkPage.getAccessRequestExecutedAtLast();
        int i=0;
        Boolean flag=false;
        for(WebElement ele : elelist)
        {
            String req = ele.getText();
            if(req.equalsIgnoreCase(request))
            {
                flag=true;
                break;
            }

        }
        Assert.assertTrue(flag);
    }

    public void NavigateToAttributes()
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getAttributesTab().click();
    }

    public void EnterpriseDirectoryAttributesValidation(String ExtReason,String ExtDisabled,String ExtStatus,String nsAccountLock)
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEnterpriseDirectory().click();
        Assert.assertEquals(getDate("yyyyMMdd"),identitiesPage.getED_extStatusChangeDate().getText());
        Assert.assertEquals(ExtReason,identitiesPage.getED_ExtReason().getText());
        Assert.assertEquals(ExtDisabled,identitiesPage.getED_extDisabled().getText());
        Assert.assertEquals(ExtStatus,identitiesPage.getED_extStatus().getText());
        Assert.assertEquals(nsAccountLock,identitiesPage.getED_nsAccountLock().getText());

    }

    public void EnterPriseDirectoryAttributesValidation(String ExtDisabled,String ExtStatus,String nsAccountLock)
    {
        identitiesPage = new IdentitiesPage(driver);
        identitiesPage.getEnterpriseDirectory().click();
        Assert.assertEquals(getDate("yyyyMMdd"),identitiesPage.getED_extStatusChangeDate().getText());
        Assert.assertEquals(ExtDisabled,identitiesPage.getED_extDisabled().getText().trim());
        Assert.assertEquals(ExtStatus,identitiesPage.getED_extStatus().getText().trim());
        Assert.assertEquals(nsAccountLock,identitiesPage.getED_nsAccountLock().getText().trim());
    }

    public void ValidateAllSAPApplicationsDisabled()
    {
        identitiesPage = new IdentitiesPage(driver);
        List<WebElement> list = identitiesPage.getActiveApplicationsUnderApplicationAccounts();
        List<String> ActiveApplications = new ArrayList<>();
        for(WebElement ele: list)
        {
            ActiveApplications.add(ele.getText());
        }

        for(String str : ActiveApplications)
        {
            if(!(str.equalsIgnoreCase("Active Directory - EU")
            ||str.equalsIgnoreCase("AzureActiveDirectory")||str.equals("Workday") || str.contains("SAP HR")))
            {
                Assert.assertTrue(false);
            }
        }
    }

    public String ValidatingAccessRequestCompletion(String username)throws Exception
    {
        myworkPage = new MyworkPage(driver);
        myworkPage.getSearchField().sendKeys(username);
        myworkPage.getSearchButton().click();
        Thread.sleep(3000);
        String AccessRequestIDParameter = myworkPage.getRequestID().getAttribute("id");
        String AccessRequestID = AccessRequestIDParameter.split("-")[1];
        String ExpectedAlertMessage = "Request completed on "+getDate("M/d/yy");
        Assert.assertEquals(ExpectedAlertMessage,myworkPage.getAccessRequestAlert().getText());
        return AccessRequestID;

    }

    public String getAccessRequestID(String username)throws Exception
    {
        myworkPage = new MyworkPage(driver);
        myworkPage.getSearchField().sendKeys(username);
        myworkPage.getSearchButton().click();
        Thread.sleep(3000);
        String AccessRequestIDParameter = myworkPage.getRequestID().getAttribute("id");
        String AccessRequestID = AccessRequestIDParameter.split("-")[1];
        return AccessRequestID;
    }

    public void NavigateToAdvancedAnalytics()
    {
        homepage=new HomePage(driver);
        homepage.getIntelligenceDropdown().click();
        homepage.getAdvancedAnalyticsOption().click();
    }

    public void PerformFilteringInAdvancedAnalytics(String ActionType,String User)
    {
        intelligencepage=new IntelligencePage(driver);
        intelligencepage.getSearchType().click();
        intelligencepage.getAuditOption().click();
        intelligencepage.getActionDropdown().sendKeys(ActionType);
        intelligencepage.getRespectiveOption().click();
        intelligencepage.getTargetUserField().sendKeys(User);
        intelligencepage.getRunSearchButton().click();
    }

    public void PerformValidationOnAdvancedAnalyticsResults(String Action,String Source,String Target,String Value1,String Value4)throws Exception
    {
        intelligencepage=new IntelligencePage(driver);
        Thread.sleep(2000);
        if(intelligencepage.getDateHeader().getAttribute("class").contains("ASC"))
        {
            intelligencepage.getDateHeader().click();
        }
        Thread.sleep(2000);
        intelligencepage.getAdvancedAnalyticsResult().click();
        Assert.assertEquals(Action,intelligencepage.getAuditEventDeatil_Action().getText());
        Assert.assertEquals(Source,intelligencepage.getAuditEventDetail_Source().getText());
        Assert.assertEquals(Target,intelligencepage.getAuditEventDetail_Target().getText());
        Assert.assertEquals(Value1,intelligencepage.getAuditEventDetail_Value1().getText().trim());
        Assert.assertEquals(Value4,intelligencepage.getAuditEventDetail_Value4().getText().trim());
    }

    public void PerformApplicationValidationOnAdvancedAnalyticsForLOA(String Application1,String Application2,String Application3)
    {
        intelligencepage=new IntelligencePage(driver);
        Assert.assertTrue(intelligencepage.getAuditEventDetail_LOASuccessfulForApplications().getText().contains(Application1));
        Assert.assertTrue(intelligencepage.getAuditEventDetail_LOASuccessfulForApplications().getText().contains(Application2));
        Assert.assertTrue(intelligencepage.getAuditEventDetail_LOASuccessfulForApplications().getText().contains(Application3));
        intelligencepage.getAuditEventDetail_CloseButton().click();
    }

    public void PerformApplicationValidationOnAdvancedAnalyticsForLeaver(String Application1,String Application2)
    {
        intelligencepage = new IntelligencePage(driver);
        Assert.assertTrue(intelligencepage.getAuditEventDetail_LeaverSuccessfulForApplications().getText().contains(Application1));
        Assert.assertTrue(intelligencepage.getAuditEventDetail_LeaverSuccessfulForApplications().getText().contains(Application2));
        intelligencepage.getAuditEventDetail_CloseButton().click();

    }

    public void ValidateApplicationsInApplicationAccounts()
    {
        identitiesPage = new IdentitiesPage(driver);
        List<WebElement> list =identitiesPage.getApplicationsInApplicationAccount();
        for(WebElement ele : list)
        {
            if(!("Azure Active Directory , SAP HR , Enterprise Directory , Workday".contains(ele.getText().trim())))
            {
                Assert.assertTrue(false);
            }
        }
    }


}
