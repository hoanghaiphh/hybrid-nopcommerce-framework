package com.jQuery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.DataTable2PO;
import pageObjects.jQuery.PageGenerator;

public class Level_11_Data_Table_2 extends BaseTest {
    private WebDriver driver;
    private DataTable2PO dataTable;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = openBrowserWithUrl(browserName, url);
        dataTable = PageGenerator.getDataTable2Page(driver);
    }

    @Test
    public void TC_01() {
        dataTable.loadDataToTable();

        dataTable.enterValueToTextbox("3", "Contact Person", "PERSON 03");
        dataTable.sleepInSeconds(1);
        dataTable.enterValueToTextbox("1", "Company", "COMPANY 01");
        dataTable.sleepInSeconds(1);

        dataTable.enterAllValueToRow("5", "COMPANY 05", "PERSON 05",
                "Japan", false, "005", "05/25/2020");
        dataTable.sleepInSeconds(1);
        dataTable.enterAllValueToRow("2", "COMPANY 02", "PERSON 02",
                "Germany", true, "002", "02/22/2020");
        dataTable.sleepInSeconds(1);

        dataTable.rowForAction("5", "Move Up");
        dataTable.sleepInSeconds(1);
        dataTable.rowForAction("4", "Move Down");
        dataTable.sleepInSeconds(1);
        dataTable.rowForAction("2", "Insert");
        dataTable.sleepInSeconds(1);
        dataTable.rowForAction("2", "Remove");
        dataTable.sleepInSeconds(1);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
