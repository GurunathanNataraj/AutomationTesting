package TestCases;

import BusinessUtils.ITAccess_Tasks;
import freemarker.template.SimpleDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestCase1 extends ITAccess_Tasks {

    //public HttpServletRequest request;

    @Test
    public void mtd() throws Exception {

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://facebook.com");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("gurunathan@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("gurunathan");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        }
    }


