package com.jqueryscript;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jqueryscript.HomePageObject;
import pageObjects.jqueryscript.PageGenerator;

public class Level_11_1_Data_Table extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = openBrowserWithUrl(browserName, url);
        homePage = PageGenerator.getHomePage(driver);
    }

    @Test
    public void TC_01_Paging() {
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageSelectedByNumber("5"));

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageSelectedByNumber("10"));

        homePage.openPageByNumber("7");
        Assert.assertTrue(homePage.isPageSelectedByNumber("7"));
    }

    @Test
    public void TC_02_Search() {
        homePage.clearAllSearchTextboxes();
        homePage.searchValueByHeader("Country","Eastern Asia");
        Assert.assertTrue(homePage.isDataRowDisplayed("8235727", "Eastern Asia", "9283824", "17519560"));

        homePage.clearAllSearchTextboxes();
        homePage.searchValueByHeader("Females","147677");
        Assert.assertTrue(homePage.isDataRowDisplayed("147677", "Ecuador", "152733", "300410"));

        homePage.clearAllSearchTextboxes();
        homePage.searchValueByHeader("Males","97641");
        Assert.assertTrue(homePage.isDataRowDisplayed("95905", "Dominican Republic", "97641", "193547"));
    }

    @Test
    public void TC_03_Action() {
        homePage.clearAllSearchTextboxes();
        homePage.searchValueByHeader("Country", "Dominican Republic");
        Assert.assertTrue(homePage.isDataRowDisplayed("95905", "Dominican Republic", "97641", "193547"));
        homePage.removeOrEditDataRowByValue("remove", "Country", "Dominican Republic");
        Assert.assertFalse(homePage.isDataRowDisplayed("95905", "Dominican Republic", "97641", "193547"));

        homePage.clearAllSearchTextboxes();
        homePage.searchValueByHeader("Country", "Eastern Europe");
        Assert.assertTrue(homePage.isDataRowDisplayed("1279622", "Eastern Europe", "1344642", "2624253"));
        homePage.removeOrEditDataRowByValue("edit", "Country","Eastern Europe");
        homePage.clickOnSubmitButton();

        homePage.clearAllSearchTextboxes();
        homePage.openPageByNumber("1");
        Assert.assertTrue(homePage.isPageSelectedByNumber("1"));
        homePage.searchValueByHeader("Females", "12253515");
        Assert.assertTrue(homePage.isDataRowDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.removeOrEditDataRowByValue("remove", "Females", "12253515");
        Assert.assertFalse(homePage.isDataRowDisplayed("12253515", "AFRICA", "12599691", "24853148"));
    }

    @Test
    public void TC_04_Get_All_Value_Of_Row_Or_Column() {
        homePage.refreshCurrentPage(driver);

        homePage.getAllValueOfColumn("Country");
        homePage.getAllValueOfRow("3");
//        Assert.assertEquals(homePage.getAllValueOfColumn("Country").get(2), homePage.getAllValueOfRow("3").get(1));

        homePage.getAllValueOfColumn("Males");
        homePage.getAllValueOfRow("5");
//        Assert.assertEquals(homePage.getAllValueOfColumn("Males").get(4), homePage.getAllValueOfRow("5").get(2));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
