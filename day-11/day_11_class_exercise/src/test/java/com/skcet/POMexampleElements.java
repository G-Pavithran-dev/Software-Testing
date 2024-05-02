package com.skcet;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class POMexampleElements {
    public WebDriver driver;

    //  WebElement searchBox= driver.findElement(By.name("q"));
    @FindBy(name = "q") //locates the searchbox element with name locator with value 'q'
    WebElement searchBox;

    @FindBy(className = "gNO89b")  //locates the googleSearchButton element with className locator with value 'gNO89b'
    WebElement googleSearchButton;

    public POMexampleElements(WebDriver driver)  //constructor to pass the driver from POMExample class 
    {   this.driver=driver;
        PageFactory.initElements(driver, this); //initializes elements from 'this' to driver
    }
    public void searchContent(String content) 
    {   searchBox.sendKeys(content);    }

    public void clickSubmit() 
    {  googleSearchButton.submit();  }

    public void AssertGivenTitle (String expectedTitle) 
    {  Assert.assertTrue(driver.getTitle().contains(expectedTitle)); } // nothing new just pass the testcase if the title contains the expected title
}
