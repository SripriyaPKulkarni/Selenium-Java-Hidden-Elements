package com.lambdatest.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class HiddenElementsException {


    WebDriver driver=new ChromeDriver();






    @Test
    public void basicTest() throws InterruptedException {
        // navigating to the application under test
        driver.get("https://sripriyakulkarni.com/");

        // maximize window
        driver.manage().window().maximize();

        // explicit wait - to wait for the link to be click-able
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[normalize-space()='Automation Practice']"))).click();

        // navigating to section of hidden element
        driver.findElement(By.xpath("//span[normalize-space()='Automation Practice']")).click();
        Thread.sleep(1000);

        // Clicking on the Hide button
        driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/section[7]/div[2]/div[2]/div/div/div/div[1]/div[2]/fieldset/input[3]")).click();

    }

}
