package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

public class Level_14_Log4J extends BaseTest {
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

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = openBrowserWithUrl(browserName, GlobalConstants.USER_URL);
        homePage = PageGenerator.getHomePage(driver);

        log.info("Pre-condition: Register - Step 01: Click on Register link in header");
        registerPage = (RegisterPageObject) homePage.clickOnHeaderLink("Register");

        log.info("Pre-condition: Register - Step 02: Select Gender Male radio button");
        registerPage.clickOnGenderMaleRadio();

        log.info("Pre-condition: Register - Step 03: Input value into Firstname textbox: " + firstName);
        registerPage.sendKeyToFirstnameTextbox(firstName);

        log.info("Pre-condition: Register - Step 04: Input value into Lastname textbox: " + lastName);
        registerPage.sendKeyToLastnameTextbox(lastName);

        log.info("Pre-condition: Register - Step 05: Select value for Day dropdown: " + dayOfBirth);
        registerPage.selectItemInDayDropdown(dayOfBirth);

        log.info("Pre-condition: Register - Step 06: Select value for Month dropdown: " + monthOfBirth);
        registerPage.selectItemInMonthDropdown(monthOfBirth);

        log.info("Pre-condition: Register - Step 07: Select value for Year dropdown: " + yearOfBirth);
        registerPage.selectItemInYearDropdown(yearOfBirth);

        log.info("Pre-condition: Register - Step 08: Input value into Email textbox: " + emailAddress);
        registerPage.sendKeyToEmailTextbox(emailAddress);

        log.info("Pre-condition: Register - Step 09: Input value into Company textbox: " + companyName);
        registerPage.sendKeyToCompanyTextbox(companyName);

        log.info("Pre-condition: Register - Step 10: Input value into Password textbox: " + password);
        registerPage.sendKeyToPasswordTextbox(password);

        log.info("Pre-condition: Register - Step 11: Input value into Confirm Password textbox: " + password);
        registerPage.sendKeyToConfirmPasswordTextbox(password);

        log.info("Pre-condition: Register - Step 12: Click on Register button");
        registerPage.clickOnRegisterButton();

        log.info("Pre-condition: Register - Step 13: Verify Success Message displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Pre-condition: Register - Step 14: Click on Log Out link");
        homePage = (HomePageObject) registerPage.clickOnHeaderLink("Log out");

        log.info("-------------------------------------------------------------------");
    }

    @Test
    public void User_01_Login() {
        log.info("User_01_Login - Step 01: Click on Log In link");
        loginPage = (LoginPageObject) homePage.clickOnHeaderLink("Log in");

        log.info("User_01_Login - Step 02: Login to system with Email: " + emailAddress + " and Password: " + password);
        homePage = loginPage.loginToSystem(emailAddress, password);

        log.info("User_01_Login - Step 03: Verify My Account link displayed in header");
        verifyTrue(homePage.isMyAccountLinkDisplayed());

        log.info("-------------------------------------------------------------------");
    }

    @Test
    public void User_02_MyAccount() {
        log.info("User_02_MyAccount - Step 01: Click on My Account link in header");
        customerInfoPage = (CustomerInfoPageObject) homePage.clickOnHeaderLink("My account");

        log.info("User_02_MyAccount - Step 02: Verify Gender Male radio button selected");
        verifyFalse(customerInfoPage.isGenderMaleSelected()); // failed

        log.info("User_02_MyAccount - Step 03: Verify value in Firstname textbox: " + firstName);
        verifyEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);

        log.info("User_02_MyAccount - Step 04: Verify value in Lastname textbox: " + lastName);
        verifyEquals(customerInfoPage.getValueInLastnameTextbox(), "lastName"); // failed

        log.info("User_02_MyAccount - Step 05: Verify value in Day dropdown: " + dayOfBirth);
        verifyEquals(customerInfoPage.getValueInDayDropdown(), dayOfBirth);

        log.info("User_02_MyAccount - Step 06: Verify value in Month dropdown: " + monthOfBirth);
        verifyEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);

        log.info("User_02_MyAccount - Step 07: Verify value in Year dropdown: " + yearOfBirth);
        verifyEquals(customerInfoPage.getValueInYearDropdown(), yearOfBirth);

        log.info("User_02_MyAccount - Step 08: Verify value in Company textbox: " + companyName);
        verifyEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);

        log.info("-------------------------------------------------------------------");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
