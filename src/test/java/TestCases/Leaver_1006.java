package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1006 extends ITAccess_Tasks {

    String AccessRequestID="";
    @Test(dataProvider = "TerminateActiveUserData",priority = 1)
    public void TerminateAnActiveUser(String User,String Event,String AccessRequestFormat,String extReason,String extDisabled,String extStatus,String nsAccountLock,String Username)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLeaverForEmployees event is Triggered for user who is terminated
        String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);

        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        // pgLeaverForEmployees event is triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);

        NavigateToApplicationAccounts();

        //all other applications except for SAP HR, Workday, AzureActiveDirectory should be Disabled at this point
        ValidateAllSAPApplicationsDisabled();

        //validating Enterprise Directory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Disabled");

        //Validating Enterprise Directory Attributes
        EnterpriseDirectoryAttributesValidation(extReason,extDisabled,extStatus,nsAccountLock);


        //Validating Active Directory Status
        Assert.assertEquals(getActiveDirectoryStatus(),"Disabled");

        NavigateToAttributes();
        Assert.assertEquals(identitiesPage.getInActiveStatus().getText().trim(),"true");

        NavigateToAccessRequests();

        //Validate Access Request is Triggered
        ValidateAccessRequest(AccessRequestFormat);
        AccessRequestID = getAccessRequestID(Username);

    }

    @Test(dataProvider = "AuditEventValidationData",priority = 2)
    public void AuditEventValidation(String Action,String User,String Source,String Target,String Value1,String Application1, String Application2)throws Exception
    {
        NavigateToAdvancedAnalytics();
        PerformFilteringInAdvancedAnalytics(Action,User);
        PerformValidationOnAdvancedAnalyticsResults(Action,Source,Target,Value1,AccessRequestID);
        PerformApplicationValidationOnAdvancedAnalyticsForLeaver(Application1,Application2);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider()
    public static Object[][] TerminateActiveUserData()
    {
        String User="ej3160";
        String Event="pgLeaverForEmployees";
        String AccessRequestFormat="Create Leaver Request: mcneill.jf";
        String extReason="Expired-AS";
        String extDisabled="1";
        String extStatus="1";
        String nsAccountLock="true";
        String Username="mcneill.jf";

        return new Object[][]{{User,Event,AccessRequestFormat,extReason,extDisabled,extStatus,nsAccountLock,Username}};
    }

    @DataProvider()
    public static Object[][] AuditEventValidationData()
    {
        String Action="Leaver - Success";
        String User="ej3160";
        String Source="RequestHandler";
        String Target="mcneill.jf";
        String Value1="Leaver for ej3160 succeeded.";
        String Application1="ActiveDirectory";
        String Application2="Enterprise Directory";

        return new Object[][]{{Action,User,Source,Target,Value1,Application1,Application2}};
    }
}



