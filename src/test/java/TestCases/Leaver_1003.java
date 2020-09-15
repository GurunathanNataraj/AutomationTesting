package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1003 extends ITAccess_Tasks {

    @Test(dataProvider="ActiveUserToDeceasedData")
    public void ActiveUserToDeceased(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLeaverForEmployees event is Triggered for Active user to deceased
        ValidateTriggeredEvent(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ActiveUserToDeceasedData()
    {
        return new Object[][]{{"aa0250","pgLeaverForEmployees"}};
    }
}
