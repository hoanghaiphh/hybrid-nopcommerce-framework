package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

import static com.nopcommerce.commons.Register_And_Login.*;

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

        assertFalse(homePage.isHeaderLinkByTextDisplayed("My account")); // failed
    }

    @Description("User_01_MyAccount")
    @Test
    public void User_01_MyAccount() {
        homePage.clickOnHeaderLinkByText("My account");
        customerInfoPage = PageGenerator.getCustomerInfoPage(driver);

        assertTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("gender-male"));
        assertEquals(customerInfoPage.getValueInTextboxByID("FirstName"), firstName);
        assertEquals(customerInfoPage.getValueInTextboxByID("LastName"), lastName);
        assertEquals(customerInfoPage.getValueInTextboxByID("Email"), emailAddress);
        assertEquals(customerInfoPage.getValueInTextboxByID("Company"), companyName);
        assertTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("Newsletter"));
    }

    @Description("User_02_")
    @Test
    public void User_02_() {

    }

    @Description("User_03_")
    @Test
    public void User_03_() {

    }

}
