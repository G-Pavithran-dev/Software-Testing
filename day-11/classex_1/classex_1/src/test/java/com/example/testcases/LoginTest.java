package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.pageobject.LoginPage;

public class LoginTest extends BaseClass {
   
    @Test
    public void loginTest()
    {
        driver.get(baseUrl);

        logger.info("URL Opened");
        LoginPage lpage=new LoginPage(driver);
        lpage.setUserName(uername);
        logger.info("username Entered");


        lpage.setPassword(password);
        logger.info("passwrod Entered");
        lpage.clickButton();
        logger.info("Button Clicked");
        if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else
        {
            Assert.assertFalse(false);
            logger.error("Loging test failed");
        }
    }

    
}
