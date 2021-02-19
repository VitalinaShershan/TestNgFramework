package com.hrms.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CommonMethods {
    /**
     * this method will open a browser, set up configuration and navigate to the URL
     */
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setUp() {

       ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

       switch (ConfigsReader.getPropertyValue("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser");

        }
        driver.get(ConfigsReader.getPropertyValue("url"));
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

  @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        /**
         * This method will close any open browser
         */

        if (driver != null) {
            driver.quit();
        }

    }
}
