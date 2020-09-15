package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LOA_1003 extends ITAccess_Tasks {

    HomePage homepage;
    IdentitiesPage identitiesPage;

    @Test(dataProvider = "ErrorHandlingData")
    public void ErrorHandling_EventFailure(String User,String Event,String Action,String Source,String Target,
                                           String Value1,String Application1,String Application2,String Application3)throws Exception
    {
        driver=LaunchITAccess();
        homepage=new HomePage(driver);
        identitiesPage = new IdentitiesPage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);
        //Validation of triggered LOA event
        ValidateTriggeredEvent(Event);



        NavigateToAdvancedAnalytics();
        PerformFilteringInAdvancedAnalytics(Action,User);
        //PerformValidationOnAdvancedAnalyticsResults(Action,Source,Target,Value1,AccessRequestID,Application1,Application2,Application3);

    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ErrorHandlingData()
    {
        return new Object[][]{{"aa0216","pgLoaForEmployees"}};
    }
}
