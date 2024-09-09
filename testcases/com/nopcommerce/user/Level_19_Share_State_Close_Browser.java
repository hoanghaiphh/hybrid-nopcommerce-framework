package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

import static com.nopcommerce.commons.Login.*;

@Feature("User")
public class Level_19_Share_State_Close_Browser extends BaseTest {
    private CustomerInfoPageObject customerInfoPage;
    private HomePageObject homePage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = openBrowserWithUrl(browserName, GlobalConstants.NOPCOMMERCE_LOCAL);
        homePage = PageGenerator.getHomePage(driver);

        homePage.setCookies(driver, nopcommerceCookies);
        homePage.refreshCurrentPage(driver);

        verifyTrue(homePage.isHeaderLinkByTextDisplayed("My account"));
        Assert.assertTrue(false); // failed
    }

    @Description("User_01_MyAccount")
    @Test
    public void User_01_MyAccount() {
        homePage.clickOnHeaderLinkByText("My account");
        customerInfoPage = PageGenerator.getCustomerInfoPage(driver);

        verifyTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("gender-male"));
        verifyEquals(customerInfoPage.getValueInTextboxByID("FirstName"), firstName);
        verifyEquals(customerInfoPage.getValueInTextboxByID("LastName"), lastName);
        verifyEquals(customerInfoPage.getValueInTextboxByID("Email"), emailAddress);
        verifyEquals(customerInfoPage.getValueInTextboxByID("Company"), companyName);
        verifyTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("Newsletter"));
    }

    @Description("User_02_")
    @Test
    public void User_02_() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
