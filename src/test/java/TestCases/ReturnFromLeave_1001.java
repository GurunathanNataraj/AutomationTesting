package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReturnFromLeave_1001 extends ITAccess_Tasks {

    IdentitiesPage identitiesPage;
    String AccessRequestID="";
    @Test(dataProvider = "EmployeeReturnsFromLeaveTodayData")
    public void EmployeeReturnsFromLeaveToday(String User,String Event,String ExtDisabled,String ExtStatus,String nsAccountLock,
                                              String AccessRequestFormat,String Username)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        //Validation of triggered Reactivation event
        String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        //Reactivation Event triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);

        NavigateToApplicationAccounts();

        //Validate Enterprise Directory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Active");

        //Validating Enterprise Directory Attributes
        EnterPriseDirectoryAttributesValidation(ExtDisabled,ExtStatus,nsAccountLock);



        NavigateToAttributes();

        //Validate Inactive Status
        Assert.assertEquals(identitiesPage.getInActiveStatus().getText(),"");

        NavigateToAccessRequests();

        //Validate AccessRequest Creation
        ValidateAccessRequest(AccessRequestFormat);
        AccessRequestID = getAccessRequestID(Username);


    }

    @Test
    public void AuditEventValidation(String Action,String User,String Source,String Target,String Value1,
                                     String Application1,String Application2,String Application3)throws Exception
    {
        PerformFilteringInAdvancedAnalytics(Action,User);
        PerformValidationOnAdvancedAnalyticsResults(Action,Source,Target,Value1,AccessRequestID);
        PerformApplicationValidationOnAdvancedAnalyticsForLOA(Application1,Application2,Application3);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] EmployeeReturnsFromLeaveTodayData()
    {
        String User = "AA1721";
        String Event = "pgReactivationEventTrigger";
        String ExtDisabled = "0";
        String ExtStatus = "0";
        String nsAccountLock = "false";
        String AccessRequestFormat = "Reactivation: cobb.pj";
        String Username = "cobb.pj";
        return new Object[][]{{User,Event,ExtDisabled,ExtStatus,nsAccountLock,AccessRequestFormat,Username}};
    }

    @DataProvider
    public static Object[][] AuditEventValidationData()
    {
        String Action = "Reactivation - Success";
        String User = "aa1721";
        String Source = "RequestHandler";
        String Target = "cobb.pj";
        String Value1 = "Reactivation for aa1721 succeeded.";
        String Application1 = "Active Directory - PG";
        String Application2 = "ActiveDirectory";
        String Application3 = "Enterprise Directory";

        return new Object[][]{{Action,User,Source,Target,Value1,Application1,Application2,Application3}};
    }

}
