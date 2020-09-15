package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReturnFromLeave_1003 extends ITAccess_Tasks {

    @Test(dataProvider = "EmployeeReturnsFromLeaveInFutureData")
    public void EmployeeReturnsFromLeaveInFuture(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate Reactivation Event not triggered for the user whose leave end date in future, more than 2 weeks
        ValidateEventNotTriggered(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] EmployeeReturnsFromLeaveInFutureData()
    {
        String User = "AA0110";
        String Event = "pgReactivationEventTrigger";
        return new Object[][]{{User,Event}};
    }
}
