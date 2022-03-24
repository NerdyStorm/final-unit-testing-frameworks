package com.techfios.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    
    static WebDriver driver;
    static String browser;
    static String url;

    public static WebDriver initDriver (){

        try {
            InputStream ios = new FileInputStream("Properties/properties.config");
            Properties p = new Properties();
            p.load(ios);

            url = p.getProperty("url");
            System.out.println(url);
            browser = p.getProperty("browser");
            System.out.println(browser);

                
            if (browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
            else{
                throw new InvalidBrowserProperty("Invalid Browser in properties file.");
            }


        } catch (InvalidBrowserProperty i) {
            //TODO: handle exception
            i.printStackTrace();
        }
        catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }



        driver.manage().deleteAllCookies();
        // driver.manage().window().maximize();

        return driver;

    }

}
