package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Cancel_1001 extends ITAccess_Tasks {

    String AccessRequestID="";
    @Test(dataProvider = "TriggerCancelEventData")
    public void TriggerCancelEvent(String User,String Event,String extDisabled,String extStatus,String AccessRequestFormat,String Username)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate Cancel event is Triggered for user
       String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);

        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        // Cancel event is triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);

        NavigateToApplicationAccounts();
        //Validate all SAP Accounts were deleted
        ValidateApplicationsInApplicationAccounts();

        //Validating EnterpriseDirectory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Disabled");

        //Validating Enterprise Directory Attributes
        EnterpriseDirectoryAttributesValidationForCancelEvent(extDisabled, extStatus);
        //Validating Active directory removed from Application Accounts
        Assert.assertFalse(identitiesPage.getActiveDirectory().isDisplayed());

        //Validate Access Request is Triggered
        ValidateAccessRequest(AccessRequestFormat);
        AccessRequestID = getAccessRequestID(Username);

    }

    @Test(dataProvider = "ValidateAuditEventsData")
    public void ValidatingAuditEvents(String Action,String User,String Source,String Target,String Value1)throws Exception
    {
        NavigateToAdvancedAnalytics();
        PerformFilteringInAdvancedAnalytics(Action,User);
        PerformValidationOnAdvancedAnalyticsResults(Action,Source,Target,Value1,AccessRequestID);
        //PerformApplicationValidationOnAdvancedAnalyticsForLeaver(Application1,Application2);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] TriggerCancelEventData()
    {
        String User = "IR5009";
        String Event = "pgCancelAccounts";
        String extDisabled = "1";
        String extStatus = "2";
        String AccessRequestFormat = "Cancel User: jane.rj";
        String Username = "jane.rj";
        return new Object[][]{{User,Event,extDisabled,extStatus,AccessRequestFormat,Username}};
    }

    @DataProvider
    public static Object[][] ValidateAuditEventsData()
    {
        String Action = "Cancel User - Success";
        String User = "IR5009";
        String Source = "RequestHandler";
        String Target = "jane.rj";
        String Value1 = "Cancel User for IR5009 succeeded.";

        return new Object[][]{{Action,User,Source,Target,Value1}};
    }
}
