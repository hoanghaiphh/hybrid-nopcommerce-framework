package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Level_13_AssertVsVerify_02_Custom_Hard_Assert extends BaseTest {
    private WebDriver driver;

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInfoPageObject customerInfoPage;

    private String firstName = "Automation";
    private String lastName = "Testing";
    private String dayOfBirth = "15";
    private String monthOfBirth = "October";
    private String yearOfBirth = "1989";
    private String companyName = "Online 29";
    private String emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";
    private String password = "Abcd@1234";

    @BeforeClass
    public void beforeClass() {
        driver = openBrowserWithUrl("firefox", GlobalConstants.USER_URL);

        // Register (Pre-conditions)
        homePage = PageGenerator.getHomePage(driver);

        registerPage = (RegisterPageObject) homePage.clickOnHeaderLink("Register");
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
        assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = (HomePageObject) registerPage.clickOnHeaderLink("Log out");
    }

    @Test
    public void User_01_Login() {
        loginPage = (LoginPageObject) homePage.clickOnHeaderLink("Log in");

        homePage = loginPage.loginToSystem(emailAddress, password);
        assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_02_MyAccount() {
        customerInfoPage = (CustomerInfoPageObject) homePage.clickOnHeaderLink("My account");

        verifyFalse(customerInfoPage.isGenderMaleSelected());
        verifyEquals(customerInfoPage.getValueInFirstnameTextbox(), "firstName");
        verifyEquals(customerInfoPage.getValueInLastnameTextbox(), lastName);
        verifyEquals(customerInfoPage.getValueInDayDropdown(), "dayOfBirth");
        verifyEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);
        verifyEquals(customerInfoPage.getValueInYearDropdown(), "yearOfBirth");
        verifyEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
