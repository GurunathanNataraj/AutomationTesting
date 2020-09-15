package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewHire_1002 extends ITAccess_Tasks {

    @Test(dataProvider = "ValidateJoinerEventTriggerData")
    public void ValidateJoinerEventTrigger_StartDateWithin28Days(String User,String Event,String RehireEvent,String ConversionEvent)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage=new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        //Validating Joiner Event is Triggered or not for employee whose start date within 28 days
       String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);

        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        //Joiner Event triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);

        //Validate Rehire Event, Conversion Event not triggered
        ValidateEventNotTriggered(RehireEvent);
        ValidateEventNotTriggered(ConversionEvent);

        //Check WorkDay Status
        NavigateToAttributes();
        Assert.assertEquals(identitiesPage.getWorkDayStatus().getText().trim(),"Active");
    }

    @Test
    public void EnterpriseDirectoryValidation()
    {
        NavigateToApplicationAccounts();

        //validating Enterprise Directory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Active");
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ValidateJoinerEventTriggerData()
    {
        //Employee ID : 50000044
        return new Object[][]{{"IR9377","pgJoinerForEmployees","pgRehireEventTrigger","pgNon-Employee_to_EmployeeConversion"}};
    }
}
