package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopcommerce.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.admin.DashboardAdminPageObject;
import pageObjects.nopcommerce.admin.LoginAdminPageObject;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.AddressesPageObject;
import pageObjects.nopcommerce.user.myAccount.ChangePasswordPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;
import pageObjects.nopcommerce.user.myAccount.OrdersPageObject;

public class Level_10_Dynamic_Locator extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInfoPageObject customerInfoPage;
    private AddressesPageObject addressesPage;
    private ChangePasswordPageObject changePasswordPage;
    private OrdersPageObject ordersPage;
    private LoginAdminPageObject loginAdminPage;
    private DashboardAdminPageObject dashboardAdminPage;

    private String firstName = "Automation";
    private String lastName = "Testing";
    private String dayOfBirth = "15";
    private String monthOfBirth = "October";
    private String yearOfBirth = "1989";
    private String companyName = "Online 29";
    private String emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";
    private String password = "Abcd@1234";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        driver.get(GlobalConstants.USER_URL);

        // Register (Pre-conditions)
        homePage = PageGenerator.getHomePage(driver);

        registerPage = (RegisterPageObject) homePage.clickOnHeaderLink("Register");
        registerPage.clickOnGenderMaleRadio();
        registerPage.sendKeyToFirstnameTextbox(firstName);
        registerPage.sendKeyToLastnameTextbox(lastName);
        registerPage.selectItemInDayDropdown(dayOfBirth);
        registerPage.selectItemInMonthDropdown(monthOfBirth);
        registerPage.selectItemInYearDropdown(yearOfBirth);
        registerPage.sendKeyToEmailTextbox(emailAddress);
        registerPage.sendKeyToCompanyTextbox(companyName);
        registerPage.sendKeyToPasswordTextbox(password);
        registerPage.sendKeyToConfirmPasswordTextbox(password);
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = (HomePageObject) registerPage.clickOnHeaderLink("Log out");
    }

    @Test
    public void User_01_Login() {
        loginPage = (LoginPageObject) homePage.clickOnHeaderLink("Log in");

        homePage = loginPage.loginToSystem(emailAddress, password);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_02_MyAccount() {
        customerInfoPage = (CustomerInfoPageObject) homePage.clickOnHeaderLink("My account");

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);
        Assert.assertEquals(customerInfoPage.getValueInLastnameTextbox(), lastName);
        Assert.assertEquals(customerInfoPage.getValueInDayDropdown(), dayOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInYearDropdown(), yearOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @Test
    public void User_03a_Switch_Page() {
//        ClassCastException solution #1
        if (customerInfoPage.clickOnSidebarLink("Addresses") instanceof AddressesPageObject) {
            addressesPage = (AddressesPageObject) customerInfoPage.clickOnSidebarLink("Addresses");
        } else {
            System.out.println("Cannot cast to SubClass");
        }

//        ClassCastException solution #2
        try {
            customerInfoPage = (CustomerInfoPageObject) addressesPage.clickOnSidebarLink("Customer info");
        } catch (ClassCastException e) {
            System.out.println("Cannot cast to SubClass: " + e.getMessage());
        }

        changePasswordPage = (ChangePasswordPageObject) customerInfoPage.clickOnSidebarLink("Change password");
        ordersPage = (OrdersPageObject) changePasswordPage.clickOnSidebarLink("Orders");
        customerInfoPage = (CustomerInfoPageObject) ordersPage.clickOnSidebarLink("Customer info");
    }

    @Test
    public void User_03b_Switch_Page() {
        customerInfoPage.clickOnSidebarLink2("Addresses");
        addressesPage = PageGenerator.getAddressesPage(driver);

        addressesPage.clickOnSidebarLink2("Customer info");
        customerInfoPage = PageGenerator.getCustomerInfoPage(driver);

        customerInfoPage.clickOnSidebarLink2("Change password");
        changePasswordPage = PageGenerator.getChangePasswordPage(driver);

        changePasswordPage.clickOnSidebarLink2("Orders");
        ordersPage = PageGenerator.getOrdersPage(driver);

        ordersPage.clickOnSidebarLink2("Customer info");
        customerInfoPage = PageGenerator.getCustomerInfoPage(driver);
    }

    @Test
    public void User_04_Switch_Site_Url() {
        customerInfoPage.openUrl(driver, GlobalConstants.ADMIN_URL);
        loginAdminPage = PageGenerator.getLoginAdminPage(driver);

        dashboardAdminPage = loginAdminPage.loginToSystem(GlobalConstants.ADMIN_EMAIL, GlobalConstants.ADMIN_PASSWORD);
        Assert.assertTrue(dashboardAdminPage.isLogoutLinkDisplayed());

        dashboardAdminPage.openUrl(driver, GlobalConstants.USER_URL);
        homePage = PageGenerator.getHomePage(driver);

        homePage.openUrl(driver, GlobalConstants.ADMIN_URL);
        dashboardAdminPage = PageGenerator.getDashboardAdminPage(driver);
        dashboardAdminPage.clickOnSidebarLink("Sales");
        dashboardAdminPage.clickOnSidebarLink("Promotions");
        dashboardAdminPage.clickOnSidebarLink("Customers");

        dashboardAdminPage.openUrl(driver, GlobalConstants.USER_URL);
        homePage = PageGenerator.getHomePage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
