package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

@Feature("User")
public class Level_18_Pattern_Object extends BaseTest {

    private CustomerInfoPageObject customerInfoPage;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;

    private String firstName = "Automation";
    private String lastName = "Testing";
    private String companyName = "Online 29";
    private String emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";
    private String password = "Abcd@1234";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = openBrowserWithUrl(browserName, GlobalConstants.NOPCOMMERCE_LOCAL);
        homePage = PageGenerator.getHomePage(driver);
    }

    @Description("User_01_Register")
    @Test
    public void User_01_Register() {
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
    }

    @Description("User_02_Login")
    @Test
    public void User_02_Login() {
        homePage.clickOnHeaderLinkByText("Log in");
        loginPage = PageGenerator.getLoginPage(driver);

        loginPage.sendKeysToTextboxByID("Email", emailAddress);
        loginPage.sendKeysToTextboxByID("Password", password);
        loginPage.clickOnButtonByText("Log in");
        homePage = PageGenerator.getHomePage(driver);

        verifyTrue(homePage.isHeaderLinkByTextDisplayed("My account"));
    }

    @Description("User_03_MyAccount")
    @Test
    public void User_03_MyAccount() {
        homePage.clickOnHeaderLinkByText("My account");
        customerInfoPage = PageGenerator.getCustomerInfoPage(driver);

        verifyTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("gender-male"));
        verifyEquals(customerInfoPage.getValueInTextboxByID("FirstName"), firstName);
        verifyEquals(customerInfoPage.getValueInTextboxByID("LastName"), lastName);
        verifyEquals(customerInfoPage.getValueInTextboxByID("Email"), emailAddress);
        verifyEquals(customerInfoPage.getValueInTextboxByID("Company"), companyName);
        verifyTrue(customerInfoPage.isCheckboxOrRadioByIDSelected("Newsletter"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
