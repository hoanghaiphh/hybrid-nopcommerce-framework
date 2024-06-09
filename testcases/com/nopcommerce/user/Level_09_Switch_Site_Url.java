package com.nopcommerce.user;

import commons.BaseTest;
import commons.DataTest;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.DashboardAdminPageObject;
import pageObjects.admin.LoginAdminPageObject;
import pageObjects.user.*;
import pageObjects.user.myAccount.AddressesPageObjectMyAccount;
import pageObjects.user.myAccount.ChangePasswordPageObjectMyAccount;
import pageObjects.user.myAccount.CustomerInfoPageObjectMyAccount;
import pageObjects.user.myAccount.OrdersPageObjectMyAccount;

public class Level_09_Switch_Site_Url extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInfoPageObjectMyAccount customerInfoPage;
    private AddressesPageObjectMyAccount addressesPage;
    private ChangePasswordPageObjectMyAccount changePasswordPage;
    private OrdersPageObjectMyAccount ordersPage;
    private LoginAdminPageObject loginAdminPage;
    private DashboardAdminPageObject dashboardAdminPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        driver.get(DataTest.USER_URL);

        // Register (Pre-conditions)
        homePage = PageGenerator.getHomePage(driver);

        registerPage = homePage.clickOnRegisterLink();
        registerPage.clickOnGenderRadio();
        registerPage.sendKeyToFirstnameTextbox(DataTest.FIRST_NAME);
        registerPage.sendKeyToLastnameTextbox(DataTest.LAST_NAME);
        registerPage.selectItemInDayDropdown(DataTest.DAY_OF_BIRTH);
        registerPage.selectItemInMonthDropdown(DataTest.MONTH_OF_BIRTH);
        registerPage.selectItemInYearDropdown(DataTest.YEAR_OF_BIRTH);
        DataTest.emailAddress = DataTest.FIRST_NAME + DataTest.LAST_NAME + getRandomNumber() + "@gmail.com";
        registerPage.sendKeyToEmailTextbox(DataTest.emailAddress);
        registerPage.sendKeyToCompanyTextbox(DataTest.COMPANY_NAME);
        registerPage.sendKeyToPasswordTextbox(DataTest.PASSWORD);
        registerPage.sendKeyToConfirmPasswordTextbox(DataTest.PASSWORD);
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickOnLogoutLink();
    }

    @Test
    public void User_01_Login() {
        loginPage = homePage.clickOnLoginLink();

        homePage = loginPage.loginToSystem(DataTest.emailAddress, DataTest.PASSWORD);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_02_MyAccount() {
        customerInfoPage = homePage.clickOnMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getValueInFirstnameTextbox(), DataTest.FIRST_NAME);
        Assert.assertEquals(customerInfoPage.getValueInLastnameTextbox(), DataTest.LAST_NAME);
        Assert.assertEquals(customerInfoPage.getValueInDayDropdown(), DataTest.DAY_OF_BIRTH);
        Assert.assertEquals(customerInfoPage.getValueInMonthDropdown(), DataTest.MONTH_OF_BIRTH);
        Assert.assertEquals(customerInfoPage.getValueInYearDropdown(), DataTest.YEAR_OF_BIRTH);
        Assert.assertEquals(customerInfoPage.getValueInCompanyTextbox(), DataTest.COMPANY_NAME);
    }

    @Test
    public void User_03_Switch_Page() {
        addressesPage = customerInfoPage.clickOnAddressesLink();
        customerInfoPage = addressesPage.clickOnCustomerInfoLink();
        changePasswordPage = customerInfoPage.clickOnChangePasswordLink();
        ordersPage = changePasswordPage.clickOnOrdersLink();
        customerInfoPage = ordersPage.clickOnCustomerInfoLink();
    }

    @Test
    public void User_04_Switch_Site_Url() {
        customerInfoPage.openPageUrl(driver, DataTest.ADMIN_URL);
        loginAdminPage = PageGenerator.getLoginAdminPage(driver);

        dashboardAdminPage = loginAdminPage.loginToSystem(DataTest.ADMIN_EMAIL, DataTest.ADMIN_PASSWORD);
        Assert.assertTrue(dashboardAdminPage.isLogoutLinkDisplayed());

        dashboardAdminPage.openPageUrl(driver, DataTest.USER_URL);
        homePage = PageGenerator.getHomePage(driver);

        homePage.openPageUrl(driver, DataTest.ADMIN_URL);
        dashboardAdminPage = PageGenerator.getDashboardAdminPage(driver);
        Assert.assertTrue(dashboardAdminPage.isLogoutLinkDisplayed());

        dashboardAdminPage.openPageUrl(driver, DataTest.USER_URL);
        homePage = PageGenerator.getHomePage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
