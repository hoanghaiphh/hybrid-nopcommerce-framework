package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

public class Level_15_Extent_Reports extends BaseTest {
//    private String browserName;

    private CustomerInfoPageObject customerInfoPage;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;

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
//        this.browserName = browserName;
        driver = openBrowserWithUrl(browserName, GlobalConstants.USER_URL);
        homePage = PageGenerator.getHomePage(driver);
    }

    @Test
    public void User_01_Register(Method method) {
//        ExtentManager.startTest(this.browserName.toUpperCase() + " - " + method.getName(), "User_01_Register");

        extentLog("User_01_Register - Step 01: Click on Register link in header");
        registerPage = (RegisterPageObject) homePage.clickOnHeaderLink("Register");

        extentLog("User_01_Register - Step 02: Select Gender Male radio button");
        registerPage.clickOnGenderMaleRadio();

        extentLog("User_01_Register - Step 03: Input value into Firstname textbox: " + firstName);
        registerPage.sendKeyToFirstnameTextbox(firstName);

        extentLog("User_01_Register - Step 04: Input value into Lastname textbox: " + lastName);
        registerPage.sendKeyToLastnameTextbox(lastName);

        extentLog("User_01_Register - Step 05: Select value for Day dropdown: " + dayOfBirth);
        registerPage.selectItemInDayDropdown(dayOfBirth);

        extentLog("User_01_Register - Step 06: Select value for Month dropdown: " + monthOfBirth);
        registerPage.selectItemInMonthDropdown(monthOfBirth);

        extentLog("User_01_Register - Step 07: Select value for Year dropdown: " + yearOfBirth);
        registerPage.selectItemInYearDropdown(yearOfBirth);

        extentLog("User_01_Register - Step 08: Input value into Email textbox: " + emailAddress);
        registerPage.sendKeyToEmailTextbox(emailAddress);

        extentLog("User_01_Register - Step 09: Input value into Company textbox: " + companyName);
        registerPage.sendKeyToCompanyTextbox(companyName);

        extentLog("User_01_Register - Step 10: Input value into Password textbox: " + password);
        registerPage.sendKeyToPasswordTextbox(password);

        extentLog("User_01_Register - Step 11: Input value into Confirm Password textbox: " + password);
        registerPage.sendKeyToConfirmPasswordTextbox(password);

        extentLog("User_01_Register - Step 12: Click on Register button");
        registerPage.clickOnRegisterButton();

        extentLog("User_01_Register - Step 13: Verify Success Message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        extentLog("User_01_Register - Step 14: Click on Log Out link");
        homePage = (HomePageObject) registerPage.clickOnHeaderLink("Log out");
    }

    @Test
    public void User_02_Login(Method method) {
//        ExtentManager.startTest(this.browserName.toUpperCase() + " - " + method.getName(), "User_02_Login");

        extentLog("User_02_Login - Step 01: Click on Log In link");
        loginPage = (LoginPageObject) homePage.clickOnHeaderLink("Log in");

        extentLog("User_02_Login - Step 02: Login to system with Email: " + emailAddress + " and Password: " + password);
        homePage = loginPage.loginToSystem(emailAddress, password);

        extentLog("User_02_Login - Step 03: Verify My Account link displayed in header");
        verifyFalse(homePage.isMyAccountLinkDisplayed()); // failed
    }

    @Test
    public void User_03_MyAccount(Method method) {
//        ExtentManager.startTest(this.browserName.toUpperCase() + " - " + method.getName(), "User_03_MyAccount");

        extentLog("User_03_MyAccount - Step 01: Click on My Account link in header");
        customerInfoPage = (CustomerInfoPageObject) homePage.clickOnHeaderLink("My account");

        extentLog("User_03_MyAccount - Step 02: Verify Gender Male radio button selected");
        verifyTrue(customerInfoPage.isGenderMaleSelected());

        extentLog("User_03_MyAccount - Step 03: Verify value in Firstname textbox: " + firstName);
        verifyEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);

        extentLog("User_03_MyAccount - Step 04: Verify value in Lastname textbox: " + lastName);
        verifyEquals(customerInfoPage.getValueInLastnameTextbox(), "lastName"); // failed

        extentLog("User_03_MyAccount - Step 05: Verify value in Day dropdown: " + dayOfBirth);
        verifyEquals(customerInfoPage.getValueInDayDropdown(), dayOfBirth);

        extentLog("User_03_MyAccount - Step 06: Verify value in Month dropdown: " + monthOfBirth);
        verifyEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);

        extentLog("User_03_MyAccount - Step 07: Verify value in Year dropdown: " + yearOfBirth);
        verifyEquals(customerInfoPage.getValueInYearDropdown(), "yearOfBirth"); // failed

        extentLog("User_03_MyAccount - Step 08: Verify value in Company textbox: " + companyName);
        verifyEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
