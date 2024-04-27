package com.skcet;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTestProfessional {
    Logger log;
    Actions actions;
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Constants
    private static final String WEBSITE_URL = "https://www.moneycontrol.com";
    private static final String MUTUAL_FUNDS_LINK_TEXT = "Mutual Funds";
    private static final String SIP_RETURNS_CSS_SELECTOR = "#common_header > div.header_desktop > div.bottom_nav > nav > div > ul > li:nth-child(10) > div > div > ul > li:nth-child(2) > ul > li:nth-child(5) > a";
    private static final String AXIS_BANK_VALUE = "AA";
    private static final String SCHEME_VALUE = "MAA587";
    private static final String AMOUNT_TO_BE_FILLED = "100000";
    private static final String START_DATE = "2021-08-02";
    private static final String END_DATE = "2023-08-17";

    @BeforeTest
    public void setup() {
        driver = new BraveDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        log = LogManager.getLogger(getClass());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testMoneyControl() {
        openMoneyControl();
        hoverMutualFunds();
        navigateToSIPReturns();
        selectAxisFund();
        selectScheme();
        setAmount();
        setStartDate();
        setEndDate();
        calculateMutualFund();
        getInvestmentPeriod();
        getInvestedAmount();
    }

    private void openMoneyControl() {
        driver.get(WEBSITE_URL);
        log.info("Opened Website");
    }

    private void hoverMutualFunds() {
        WebElement mutualFund = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(MUTUAL_FUNDS_LINK_TEXT)));
        actions.moveToElement(mutualFund);
        log.info("mutual funds hovered without clicking");
    }

    private void navigateToSIPReturns() {
        WebElement sipReturns = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SIP_RETURNS_CSS_SELECTOR)));
        sipReturns.click();
        log.info("navigated to SIP Returns");
    }

    private void selectAxisFund() {
        selectDropdownOption("//*[@id=\"ff_id\"]", AXIS_BANK_VALUE);
        log.info("selected Aixs Bank");
    }

    private void selectScheme() {
        selectDropdownOption("//*[@id=\"im_id\"]", SCHEME_VALUE);
        log.info("selected given scheme");
    }

    private void setAmount() {
        setInputValue("//*[@id=\"invamt\"]", AMOUNT_TO_BE_FILLED);
        log.info("amount setted as 100000");
    }

    private void setStartDate() {
        setInputValue("//*[@id=\"stdt\"]", START_DATE);
        log.info("start date setted as 2021-08-02");
    }

    private void setEndDate() {
        setInputValue("//*[@id=\"endt\"]", END_DATE);
        log.info("end date setted as 2023-08-17");
    }

    private void calculateMutualFund() {
        WebElement calculateButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")));
        calculateButton.click();
        log.info("calculating Mutual Funds");
    }

    private void getInvestmentPeriod() {
        WebElement investment = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]")));
        log.info(investment.getText());
    }

    private void getInvestedAmount() {
        WebElement amount = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]")));
        log.info(amount.getText());
    }

    private void selectDropdownOption(String xpath, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    private void setInputValue(String xpath, String value) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        input.sendKeys(value);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}