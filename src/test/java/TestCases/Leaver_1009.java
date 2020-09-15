package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1009 extends ITAccess_Tasks {

    @Test(dataProvider="ModifyTerminationDateToEarlierDateData")
    public void ModifyTerminationDateToEarlierDate(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgLeaverForEmployees event is Triggered for User whose termination date modified as earlier date
        ValidateTriggeredEvent(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ModifyTerminationDateToEarlierDateData()
    {
        return new Object[][]{{"ej3160","pgLeaverForEmployees"}};
    }
}
