package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewHire_1001 extends ITAccess_Tasks {

    @Test(dataProvider = "ValidateJoinerEventTriggerData")
    public void ValidateJoinerEventTrigger_StartDateInPast(String user,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);
        //Validating Joiner Event is Triggered or not for employee whose start date in past
        ValidateTriggeredEvent(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ValidateJoinerEventTriggerData()
    {
        //Employee ID : 50000195
        return new Object[][]{{"IR9376","pgJoinerForEmployees"}};
    }
}
