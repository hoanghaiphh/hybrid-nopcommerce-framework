package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import jiraConfig.JiraCreateIssue;
import org.testng.annotations.*;
import pageObjects.nopcommerce.PageGenerator;
import pageObjects.nopcommerce.user.HomePageObject;
import pageObjects.nopcommerce.user.LoginPageObject;
import pageObjects.nopcommerce.user.RegisterPageObject;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;

//@Listeners(jiraConfig.JiraListener.class) -->
public class Level_17_Jira extends BaseTest {

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

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_01_Register() {
        registerPage = (RegisterPageObject) homePage.clickOnHeaderLink("Register");
        
        registerPage.clickOnGenderMaleRadio();
        registerPage.sendKeyToFirstnameTextbox(firstName);
        registerPage.sendKeyToLastnameTextbox(lastName);
        registerPage.sendKeyToEmailTextbox(emailAddress);
        registerPage.sendKeyToCompanyTextbox(companyName);
        registerPage.sendKeyToPasswordTextbox(password);
        registerPage.sendKeyToConfirmPasswordTextbox(password);
        registerPage.clickOnRegisterButton();
        
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..."); // failed
        
        homePage = (HomePageObject) registerPage.clickOnHeaderLink("Log out");
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_02_Login() {
        loginPage = (LoginPageObject) homePage.clickOnHeaderLink("Log in");

        homePage = loginPage.loginToSystem(emailAddress, password);

        verifyFalse(homePage.isMyAccountLinkDisplayed()); // failed
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_03_MyAccount() {
        customerInfoPage = (CustomerInfoPageObject) homePage.clickOnHeaderLink("My account");

        verifyFalse(customerInfoPage.isGenderMaleSelected()); // failed
        verifyEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);
        verifyEquals(customerInfoPage.getValueInLastnameTextbox(), "Testing..."); // failed
        verifyEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
