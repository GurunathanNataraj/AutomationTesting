package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1004 extends ITAccess_Tasks {

    @Test(dataProvider="UserOnLeaveOfAbsenseToBeDeceasedData")
    public void UserOnLeaveOfAbsenseToBeDeceased(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLeaverForEmployees event is Triggered for User on Leave of Absense to be deceased
        ValidateTriggeredEvent(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] UserOnLeaveOfAbsenseToBeDeceasedData()
    {
        return new Object[][]{{"IR0493","pgLeaverForEmployees"}};
    }
}
