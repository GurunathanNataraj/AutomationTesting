package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RehireTerminatedUser_1003 extends ITAccess_Tasks {

    @Test(dataProvider = "ValidateRehireEventNotTriggeredData")
    public void ValidateRehireEventNotTriggered(String user,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);

        //Validate Rehire event not triggered for user whose hire date is more than 28 days from today
        ValidateEventNotTriggered(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ValidateRehireEventNotTriggeredData()
    {
        return new Object[][]{{"IQ0473","pgRehireEventTrigger"}};
    }
}
