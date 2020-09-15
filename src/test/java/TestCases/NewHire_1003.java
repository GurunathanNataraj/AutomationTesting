package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewHire_1003 extends ITAccess_Tasks {

    @Test(dataProvider = "ValidateJoinerEventTriggerData")
    public void ValidateJoinerEventTrigger_StartDateIs60DaysInFuture(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        IdentitiesPage identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate Joiner event not triggered for user whose hire date is 60 days in future from today
        ValidateEventNotTriggered(Event);
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ValidateJoinerEventTriggerData()
    {
        //Employee ID : 50000125
        return new Object[][]{{"IR9373","pgJoinerForEmployees"}};
    }
}
