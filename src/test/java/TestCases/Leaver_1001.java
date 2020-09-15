package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1001 extends ITAccess_Tasks {

    @Test(dataProvider="RetireActiveUserData")
    public void RetireAnActiveUser(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLeaverForEmployees event is Triggered for user who is going to be retire
        ValidateTriggeredEvent(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] RetireActiveUserData()
    {
        return new Object[][]{{"aa0249","pgLeaverForEmployees"}};
    }
}
