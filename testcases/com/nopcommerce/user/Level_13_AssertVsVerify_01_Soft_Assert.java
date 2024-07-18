package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

public class Level_13_AssertVsVerify_01_Soft_Assert extends BaseTest {
    private WebDriver driver;
    private SoftAssert soft;

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
        soft = new SoftAssert();

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

        soft.assertFalse(customerInfoPage.isGenderMaleSelected(), "Gender Male is selected\t\t");
        soft.assertEquals(customerInfoPage.getValueInFirstnameTextbox(), "firstName", "Firstname is not matched\t");
        soft.assertEquals(customerInfoPage.getValueInLastnameTextbox(), lastName);
        soft.assertEquals(customerInfoPage.getValueInDayDropdown(), "dayOfBirth", "DayOfBirth is not matched\t");
        soft.assertEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);
        soft.assertEquals(customerInfoPage.getValueInYearDropdown(), "yearOfBirth", "YearOfBirth is not matched\t");
        soft.assertEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);

        soft.assertAll();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
