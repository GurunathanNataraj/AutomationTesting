package TestCases;

import BusinessUtils.ITAccess_Tasks;
import PageObjects.HomePage;
import PageObjects.IdentitiesPage;
import PageObjects.MyworkPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RehireTerminatedUser_1001 extends ITAccess_Tasks {
    HomePage homepage;
    MyworkPage myworkPage;
    @Test(dataProvider = "ValidateRehireEventTriggerData", priority=1)
    public void ValidateRehireEventTrigger(String user,String Event,String AccessRequest) throws Exception
    {
        driver=LaunchITAccess();
        homepage=new HomePage(driver);
        Assert.assertTrue(homepage.getHomeButton().isDisplayed());
        NavigateToTasksPage();
        ExecuteIdentityRefreshTaskForSingleUser(user);
        SearchUserInIdentitiesPage(user);
        //Validating Event is Triggered or not
        ValidateTriggeredEvent(Event);
        driver.navigate().refresh();
        //Validating Access Request Type
        AccessRequestType(AccessRequest);

    }

    @Test(dataProvider = "ValidatingEnterpriseDirectoryAttributesData", priority = 2)
    public void ValidatingEnterpriseDirectoryAttributes(String user,String AccessRequestFormat,String givenName,String initials,String sn,String extHandle,String extShortName,String extStatus,
                                                        String extDisabled,String nsAccountLock,String extSiteType,String mailRoutingAddress,String mail,
                                                        String mailAlternateAddress1,String mailAlternateAddress2,String mailAlternateAddress3,String mailUserStatus) throws Exception
    {
        myworkPage = new MyworkPage(driver);
        NavigateToAccessRequests();
        //Validating Access Request format
        ValidateAccessRequest(AccessRequestFormat);
        SearchUserInIdentitiesPage(user);
        NavigateToApplicationAccounts();

        //validating Enterprise Directory Status
        Assert.assertEquals(getEnterPriseDirectoryStatus(),"Active");

        //Validating Enterprise Directory Attributes
        EnterpriseDirectoryAttributesValidation(givenName,initials,sn,extHandle,extShortName,extStatus,extDisabled,nsAccountLock,extSiteType,mailRoutingAddress,mail,mailAlternateAddress1,mailAlternateAddress2,mailAlternateAddress3,mailUserStatus);


    }

    @Test(dataProvider = "ValidatingActiveDirectoryAttributesData", priority = 3)
    public void ValidatingActiveDirectoryAttributes(String mail,String mailnickname,String proxyAddress1,String proxyAddress2,String proxyAddress3,String proxyAddress4,
                                                    String targetAddress,String UserPrincipalName)
    {
        //Validating Active Directory Status
        Assert.assertEquals(getActiveDirectoryPGStatus(),"Active");

        //Validating Active Directory Attributes
        ValidateActiveDirectoryAttributes(mail,mailnickname, proxyAddress1, proxyAddress2, proxyAddress3, proxyAddress4, targetAddress, UserPrincipalName);
    }

    @Test(priority = 4)
    public void VerifyEntitlements()
    {
        IdentitiesPage identitiesPage=new IdentitiesPage(driver);
        NavigateToEntitlements();
        //Validating Entitlements for the user
        Assert.assertTrue(identitiesPage.getIntranetAccessRole().isDisplayed());
        Assert.assertTrue(identitiesPage.getO365BusinessRole().isDisplayed());
    }

    @AfterClass
    public void Close()
    {
        driver.quit();
    }

    @DataProvider
    public static Object[][] ValidateRehireEventTriggerData()
    {
        return new Object[][]{{"ej3733","pgRehireEventTrigger","Rehire"}};

    }

    @DataProvider
    public static Object[][] ValidatingEnterpriseDirectoryAttributesData()
    {
        /*
         * User = EJ3733
         * AccessRequestFormat = Rehire: crawford.sa
         * givenName = Stephen
         * initials = SA
         * sn = Crawford
         * extHandle = crawford.sa
         * extShortName = crawford.sa
         * extStatus = 0
         * extDisabled = 0
         * extGloPn = notnull
         * nsAccountLock = false
         * extStatusChgDate = current date
         * extSiteType = P
         * mailRoutingAddress = crawford.sa@pgdevone.mail.onmicrosoft.com
         * mail = crawford.sa@pgdev.zone
         * mailAlternateAddress = crawford.sa@exchangemail.pgdev.com
         * mailAlternateAddress = crawford.sa@pgdev.zone
         * mailAlternateAddress = ej3733@pgdev.zone
         * mailUserStatus = active
         */
        return new Object[][]{{"ej3733","Rehire: crawford.sa","Stephen","SA","Crawford","crawford.sa","crawford.sa","0","0","false",
                                "P","crawford.sa@pgdevone.mail.onmicrosoft.com","crawford.sa@pgdev.zone",
                                "crawford.sa@exchangemail.pgdev.com","crawford.sa@pgdev.zone","ej3733@pgdev.zone",
                                "active"}};
    }

    @DataProvider
    public Object[][] ValidatingActiveDirectoryAttributesData()
    {
        /*
         *mail=crawford.sa@pgdev.zone
         *mailnickname=crawford.sa
         *proxyAddresses=smtp:crawford.sa@exchangemail.pgdev.com
         *proxyAddresses=SMTP:crawford.sa@pgdev.zone
         *proxyAddresses=smtp:crawford.sa@pgdevone.mail.onmicrosoft.com
         *proxyAddresses=smtp:ej3733@pgdev.zone
         *targetAddress=SMTP:crawford.sa@pgdevone.mail.onmicrosoft.com
         *UserPrincipalName=crawford.sa@pgdev.wtf.pg.com
         */
        return new Object[][]{{"crawford.sa@pgdev.zone","crawford.sa","smtp:crawford.sa@exchangemail.pgdev.com",
        "SMTP:crawford.sa@pgdev.zone","smtp:crawford.sa@pgdevone.mail.onmicrosoft.com","smtp:ej3733@pgdev.zone",
        "SMTP:crawford.sa@pgdevone.mail.onmicrosoft.com","crawford.sa@pgdev.wtf.pg.com"}};
    }

}
