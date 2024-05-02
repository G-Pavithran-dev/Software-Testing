package com.example.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.example.util.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    ReadConfig config=new ReadConfig();
    
//    / public String baseUrl="https://demo.guru99.com/v3/index.php";
//     public String uername="mngr562996";
//     public String password="avAvYbe";
public String baseUrl=config.getUrl();
public String uername=config.getUsename();
public String password=config.getPassword();

    public static WebDriver driver;
public static Logger logger;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        logger = LogManager.getLogger(getClass());
    }
    @AfterClass
    public void close()
    {
        driver.quit();
    }
    

}
