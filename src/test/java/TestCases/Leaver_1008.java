package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Leaver_1008 extends ITAccess_Tasks {

    @Test(dataProvider="ModifyTerminationDateToLaterDateData")
    public void ModifyTerminationDateToLaterDate(String User,String Event)throws Exception
    {
        driver=LaunchITAccess();
        HomePage homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(User);
        SearchUserInIdentitiesPage(User);

        //Validate pgRehireEventTrigger event is Triggered for User in terminated status whose  termination date modified to later date
        ValidateTriggeredEvent(Event);
    }


    @AfterClass
    public void Close()
    {
        driver.quit();
    }


    @DataProvider
    public static Object[][] ModifyTerminationDateToLaterDateData()
    {
        return new Object[][]{{"ej3160","pgRehireEventTrigger"}};
    }
}
