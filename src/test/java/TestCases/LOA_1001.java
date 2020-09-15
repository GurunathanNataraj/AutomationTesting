package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LOA_1001 extends ITAccess_Tasks {

    @Test(dataProvider="ValidateLOAforEmployeesData")
    public void EmployeeGoesOnLeaveOfAbsense_LeaveStartDateLessThan30DaysInPast(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLoaForEmployees event not triggered for user whose leave start date is less than 30 days in past
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
        return new Object[][]{{"aa0114","pgLoaForEmployees"}};
    }
}
