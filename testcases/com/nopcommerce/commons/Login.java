package com.nopcommerce.commons;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Feature;
import org.openqa.selenium.Cookie;
import org.testng.annotations.*;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;

import java.util.Set;

@Feature("User")
public class Login extends BaseTest {
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;

    public static String firstName, lastName, companyName, password, emailAddress;
    public static Set<Cookie> nopcommerceCookies;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        driver = openBrowserWithUrl(browserName, GlobalConstants.NOPCOMMERCE_LOCAL);
        homePage = PageGenerator.getHomePage(driver);

        // Data test
        firstName = "Automation";
        lastName = "Testing";
        companyName = "Online 29";
        password = "Abcd@1234";
        emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";

        // Register
        homePage.clickOnHeaderLinkByText("Register");
        registerPage = PageGenerator.getRegisterPage(driver);

        registerPage.selectCheckboxOrRadioByID("gender-male");
        registerPage.sendKeysToTextboxByID("FirstName", firstName);
        registerPage.sendKeysToTextboxByID("LastName", lastName);
        registerPage.sendKeysToTextboxByID("Email", emailAddress);
        registerPage.sendKeysToTextboxByID("Company", companyName);
        registerPage.sendKeysToTextboxByID("Password", password);
        registerPage.sendKeysToTextboxByID("ConfirmPassword", password);
        registerPage.selectCheckboxOrRadioByID("Newsletter");
        registerPage.clickOnButtonByText("Register");

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickOnHeaderLinkByText("Log out");
        homePage = PageGenerator.getHomePage(driver);

        // Login
        homePage.clickOnHeaderLinkByText("Log in");
        loginPage = PageGenerator.getLoginPage(driver);

        loginPage.sendKeysToTextboxByID("Email", emailAddress);
        loginPage.sendKeysToTextboxByID("Password", password);
        loginPage.clickOnButtonByText("Log in");
        homePage = PageGenerator.getHomePage(driver);

        // Save Cookies
        nopcommerceCookies = homePage.getCookies(driver);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
