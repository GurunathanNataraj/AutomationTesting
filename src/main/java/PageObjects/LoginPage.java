package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    By ShortNameField = By.xpath("//input[@id='loginForm:accountId']");
    By PasswordField = By.xpath("//input[@id='loginForm:password']");
    By LoginButton = By.xpath("//input[@id='loginForm:loginButton']");

    public WebElement getShortNameField()
    {
        return driver.findElement(ShortNameField);
    }

    public WebElement getPasswordField()
    {
        return driver.findElement(PasswordField);
    }

    public WebElement getLoginButton()
    {
        return driver.findElement(LoginButton);
    }
}
