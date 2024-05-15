package com.lambdatest.automation;
 
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
 
public class HiddenElementsDemo {




    public String username = "sripriyapkulkarni";
    public String accesskey = "0JtfDYxpML64zCQoyNfb9RBRkZSry5Q1LZ6LXPrJxbVJmVjx4M";

    public static RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
	 
    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {

        String hub = "@hub.lambdatest.com/wd/hub";
 
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("124.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "sripriyapkulkarni");
        ltOptions.put("accessKey", "0JtfDYxpML64zCQoyNfb9RBRkZSry5Q1LZ6LXPrJxbVJmVjx4M");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
 
    }
 
    @Test
    public void basicTest() throws InterruptedException {
        // navigating to the application under test
        driver.get("http://sripriyakulkarni.com/");
 
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
 
        JavascriptExecutor jse = (JavascriptExecutor) driver;
 
        WebElement element = driver.findElement(By.xpath("//input[@id='displayed-text']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','visibility:visible;');",
                element);
 
        jse.executeScript("document.getElementById('displayed-text').value='LambdaTest';");
 
        driver.findElement(By.id("show-textbox")).click();
 
    }
 
    @AfterMethod
    public void tearDown() {
 
        driver.quit();
    }
}
