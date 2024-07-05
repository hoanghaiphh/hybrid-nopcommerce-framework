package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_02_StaticMethod {
    WebDriver driver;
    String firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, emailAddress, companyName, password;
    BasePage basePage;

    public long getRandomNumber() {
        return new Random().nextInt(999999);
    }

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        basePage = BasePage.getBasePage();

        firstName = "Automation";
        lastName = "Testing";
        dayOfBirth = "15";
        monthOfBirth = "October";
        yearOfBirth = "1989";
        emailAddress = firstName + lastName + getRandomNumber() + "@gmail.com";
        companyName = "Online 29";
        password = "Abcd@1234";

        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void User_01_Register() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickOnElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//input[@id='gender-male']");
        basePage.clickOnElement(driver, "//input[@id='gender-male']");

        basePage.sendKeysToElement(driver, "//input[@id='FirstName']", firstName);
        basePage.sendKeysToElement(driver, "//input[@id='LastName']", lastName);

        basePage.selectOptionInDropdown(driver, "//select[@name='DateOfBirthDay']", dayOfBirth);
        basePage.selectOptionInDropdown(driver, "//select[@name='DateOfBirthMonth']", monthOfBirth);
        basePage.selectOptionInDropdown(driver, "//select[@name='DateOfBirthYear']", yearOfBirth);

        basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendKeysToElement(driver, "//input[@id='Company']", companyName);
        basePage.sendKeysToElement(driver, "//input[@id='Password']", password);
        basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", password);

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickOnElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-login']");
        basePage.clickOnElement(driver, "//a[@class='ico-login']");

        basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendKeysToElement(driver, "//input[@id='Password']", password);

        basePage.waitForElementClickable(driver, "//button[@class='button-1 login-button']");
        basePage.clickOnElement(driver, "//button[@class='button-1 login-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void User_03_MyAccount() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-account' and text()='My account']");
        basePage.clickOnElement(driver, "//a[@class='ico-account' and text()='My account']");

        Assert.assertTrue(basePage.isElementSelected(driver, "//input[@id='gender-male']"));

        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);

        Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthDay']"), dayOfBirth);
        Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthMonth']"), monthOfBirth);
        Assert.assertEquals(basePage.getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthYear']"), yearOfBirth);

        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='Company']", "value"), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
