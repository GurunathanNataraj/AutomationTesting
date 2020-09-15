package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RehireTerminatedUser_1002 extends ITAccess_Tasks {

    @Test(dataProvider = "RehireTerminatedUser_1002", priority = 1)
    public void ValidateRehireEventTrigger(String user,String Event,String JoinerEvent,String ConversionEvent) throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);
        //Validation of triggered Rehire event
        String FirstTriggeredEventTime = ValidateTriggeredEvent(Event);
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);
        String SecondTriggerEventTime = ValidateTriggeredEvent(Event);

        //Rehire Event triggered Only once
        Assert.assertEquals(FirstTriggeredEventTime,SecondTriggerEventTime);

        //Validate Joiner Event, Conversion Event not triggered
        ValidateEventNotTriggered(JoinerEvent);
        ValidateEventNotTriggered(ConversionEvent);




        //Check WorkDay Status
        NavigateToAttributes();
        Assert.assertEquals(identitiesPage.getWorkDayStatus().getText().trim(),"Active");

    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }



    @DataProvider(name = "RehireTerminatedUser_1002")
    public static Object[][] ValidateRehireEventTriggerData()
    {
        return new Object[][]{{"ej6562","pgRehireEventTrigger","pgJoinerForEmployees","pgNon-Employee_to_EmployeeConversion"}};

    }


}
