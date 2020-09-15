package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LOA_1004 extends ITAccess_Tasks {

    @Test(dataProvider="ValidateLOAforEmployeesData")
    public void EmployeeGoesOnLeaveOfAbsense_LeaveStartDateInFuture(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLoaForEmployees event not triggered for user whose leave start date in future
        ValidateEventNotTriggered(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ValidateLOAforEmployeesData()
    {
        return new Object[][]{{"aa0216","pgLoaForEmployees"}};
    }
}
