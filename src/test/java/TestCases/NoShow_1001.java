package TestCases;


import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NoShow_1001 extends ITAccess_Tasks {
HomePage homepage;
    @Test(dataProvider = "TriggerNoShowEventData")
    public void TriggerNoShowEvent(String User, String Event)throws Exception
    {
        driver=LaunchITAccess();
        homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        //Validating Event is Triggered or not
        ValidateTriggeredEvent(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] TriggerNoShowEventData()
    {
        return new Object[][]{{"IR9395","pgLeaverForEmployees"}};
    }
}
