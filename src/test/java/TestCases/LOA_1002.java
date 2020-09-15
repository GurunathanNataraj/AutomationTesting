package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import PageObjects.IntelligencePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LOA_1002 extends ITAccess_Tasks {
    String AccessRequestID="";
    HomePage homepage;
    IdentitiesPage identitiesPage;

    @Test(dataProvider="ValidateLOAforEmployeesData",priority=1)
    public void EmployeeGoesOnLeaveOfAbsense_LeaveStartDateGreaterThan30DaysInPast(String User,String Event,String AccessRequestFormat,String extReason,String extDisabled,
                                                                                    String extStatus,String nsAccountLock,String Username)throws Exception
    {
        driver=LaunchITAccess();
        homepage=new HomePage(driver);
        identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        //Validation of triggered LOA event
        String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        //LOA Event triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);


        NavigateToApplicationAccounts();

        //all other applications except for SAP HR, Workday, AzureActiveDirectory should be Disabled at this point
        ValidateAllSAPApplicationsDisabled();

        //validating Enterprise Directory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Disabled");

        //Validating Enterprise Directory Attributes
        EnterpriseDirectoryAttributesValidation(extReason,extDisabled,extStatus,nsAccountLock);


        //Validating Active Directory Status
        Assert.assertEquals(getActiveDirectoryPGStatus(),"Disabled");

        NavigateToAttributes();
        Assert.assertEquals(identitiesPage.getInActiveStatus().getText().trim(),"true");


        NavigateToAccessRequests();

        //Validate Access Request is Triggered
        ValidateAccessRequest(AccessRequestFormat);

        //Verify Access request is completed and get Access Request ID
       AccessRequestID = ValidatingAccessRequestCompletion(Username);

    }

    @Test(dataProvider = "ValidateSuccessAuditEventsData",priority = 2)
    public void ValidateSuccessAuditEvents(String Action,String User,String Source,String Target,String Value1,String Application1,String Application2,String Application3)throws Exception
    {
        NavigateToAdvancedAnalytics();
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
    public static Object[][] ValidateLOAforEmployeesData()
    {
        String User = "aa0206";
        String Event = "pgLoaForEmployees";
        String AccessRequestFormat = "LOA: reymond.s";
        String ExtReason = "LA";
        String ExtDisabled = "1";
        String ExtStatus = "1";
        String nsAccountLock="true";
        String Username = "reymond.s";
        return new Object[][]{{User,Event,AccessRequestFormat,ExtReason,ExtDisabled,ExtStatus,nsAccountLock,Username}};

    }

    @DataProvider
    public static Object[][] ValidateSuccessAuditEventsData()
    {
        String Action = "LOA - Success";
        String User = "aa0206";
        String Source = "RequestHandler";
        String Target = "reymond.s";
        String Value1 = "LOA for aa0206 succeeded.";
        String Application1 = "Active Directory - PG";
        String Application2 = "ActiveDirectory";
        String Application3 = "Enterprise Directory";

        return new Object[][]{{Action,User,Source,Target,Value1,Application1,Application2,Application3}};
    }

}

