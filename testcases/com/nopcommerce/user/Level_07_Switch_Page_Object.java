package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;
import pageObjects.user.myAccount.AddressesPageObject;
import pageObjects.user.myAccount.ChangePasswordPageObject;
import pageObjects.user.myAccount.CustomerInfoPageObject;
import pageObjects.user.myAccount.OrdersPageObject;

public class Level_07_Switch_Page_Object extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInfoPageObject customerInfoPage;
    private AddressesPageObject addressesPage;
    private ChangePasswordPageObject changePasswordPage;
    private OrdersPageObject ordersPage;
    private String firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, emailAddress, companyName, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        firstName = "Automation";
        lastName = "Testing";
        dayOfBirth = "15";
        monthOfBirth = "October";
        yearOfBirth = "1989";
        emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";
        companyName = "Online 29";
        password = "Abcd@1234";

        driver = getBrowserDriver(browserName);
        driver.get("https://demo.nopcommerce.com/");

        homePage = PageGenerator.getHomePage(driver);
    }

    @Test
    public void User_01_Register() {
        registerPage = homePage.clickOnRegisterLink();

        registerPage.clickOnGenderRadio();
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
    }

    @Test
    public void User_02_Login() {
        registerPage.clickOnLogoutLink();

        loginPage = registerPage.clickOnLoginLink();

        homePage = loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.clickOnMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);
        Assert.assertEquals(customerInfoPage.getValueInLastnameTextbox(), lastName);
        Assert.assertEquals(customerInfoPage.getValueInDayDropdown(), dayOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInYearDropdown(), yearOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @Test
    public void User_04_Switch_Page() {
        addressesPage = customerInfoPage.clickOnAddressesLink();
        customerInfoPage = addressesPage.clickOnCustomerInfoLink();
        changePasswordPage = customerInfoPage.clickOnChangePasswordLink();
        ordersPage = changePasswordPage.clickOnOrdersLink();
        customerInfoPage = ordersPage.clickOnCustomerInfoLink();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
