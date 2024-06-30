package com.jqueryscript;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jqueryscript.HomePageObject;
import pageObjects.jqueryscript.PageGenerator;

public class Level_11_2_Data_Table extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = openBrowserWithUrl(browserName, url);
        homePage = PageGenerator.getHomePage(driver);
    }

    @Test
    public void TC_01() {
        homePage.loadDataToTable();

        homePage.sendKeyToCellTextbox("3", "Contact Person", "PERSON 03");
        homePage.sendKeyToCellTextbox("1", "Company", "COMPANY 01");

        homePage.enterAllValueToRow("5", "COMPANY 05", "PERSON 05",
                "Japan", false, "005", "05/25/2020");
        homePage.enterAllValueToRow("2", "COMPANY 02", "PERSON 02",
                "Germany", true, "002", "02/22/2020");

        homePage.clickOnCellActionButton("5", "Move Up");
        homePage.clickOnCellActionButton("4", "Move Down");
        homePage.clickOnCellActionButton("2", "Insert");
        homePage.clickOnCellActionButton("2", "Remove");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
