package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.CustomerInfoPageFactory;
import pageFactory.HomePageFactory;
import pageFactory.LoginPageFactory;
import pageFactory.RegisterPageFactory;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_05_Page_Factory extends BaseTest {
    private WebDriver driver;
    private HomePageFactory homePage;
    private RegisterPageFactory registerPage;
    private LoginPageFactory loginPage;
    private CustomerInfoPageFactory customerInfoPage;
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
        homePage = new HomePageFactory(driver);
    }

    @Test
    public void User_01_Register() {
        homePage.clickOnRegisterLink();

        registerPage = new RegisterPageFactory(driver);

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
        registerPage.clickOnLoginLink();

        loginPage = new LoginPageFactory(driver);

        loginPage.sendKeyToEmailTextbox(emailAddress);
        loginPage.sendKeyToPasswordTextbox(password);
        loginPage.clickOnLoginButton();

        homePage = new HomePageFactory(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        homePage.clickOnMyAccountLink();

        customerInfoPage = new CustomerInfoPageFactory(driver);

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getValueInFirstnameTextbox(), firstName);
        Assert.assertEquals(customerInfoPage.getValueInLastnameTextbox(), lastName);
        Assert.assertEquals(customerInfoPage.getValueInDayDropdown(), dayOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInMonthDropdown(), monthOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInYearDropdown(), yearOfBirth);
        Assert.assertEquals(customerInfoPage.getValueInCompanyTextbox(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
