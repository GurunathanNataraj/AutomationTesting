package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewHire_1005 extends ITAccess_Tasks {

    @Test(dataProvider = "ModifyHireDateData")
    public void ModifyHireDateWithIn28Days(String user,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);
        //Validating pgJoinerForEmployees Event is Triggered or not for employee whose Hire date modified within 28 days future
        ValidateTriggeredEvent(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ModifyHireDateData()
    {
        //User Used in NewHire_1004
        return new Object[][]{{"IR9373","pgJoinerForEmployees"}};
    }
}
