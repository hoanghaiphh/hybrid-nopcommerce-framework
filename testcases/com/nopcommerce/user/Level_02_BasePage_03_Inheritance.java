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

public class Level_02_BasePage_03_Inheritance extends BasePage {
    WebDriver driver;
    String firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, emailAddress, companyName, password;

    public long getRandomNumber() {
        return new Random().nextInt(999999);
    }

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

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
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickOnElement(driver, "//a[@class='ico-register']");

        waitForElementClickable(driver, "//input[@id='gender-male']");
        clickOnElement(driver, "//input[@id='gender-male']");

        sendKeysToElement(driver, "//input[@id='FirstName']", firstName);
        sendKeysToElement(driver, "//input[@id='LastName']", lastName);

        selectOptionInDropdown(driver, "//select[@name='DateOfBirthDay']", dayOfBirth);
        selectOptionInDropdown(driver, "//select[@name='DateOfBirthMonth']", monthOfBirth);
        selectOptionInDropdown(driver, "//select[@name='DateOfBirthYear']", yearOfBirth);

        sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeysToElement(driver, "//input[@id='Company']", companyName);
        sendKeysToElement(driver, "//input[@id='Password']", password);
        sendKeysToElement(driver, "//input[@id='ConfirmPassword']", password);

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickOnElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickOnElement(driver, "//a[@class='ico-login']");

        sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeysToElement(driver, "//input[@id='Password']", password);

        waitForElementClickable(driver, "//button[@class='button-1 login-button']");
        clickOnElement(driver, "//button[@class='button-1 login-button']");

        Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void User_03_MyAccount() {
        waitForElementClickable(driver, "//a[@class='ico-account' and text()='My account']");
        clickOnElement(driver, "//a[@class='ico-account' and text()='My account']");

        Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-male']"));

        Assert.assertEquals(getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);

        Assert.assertEquals(getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthDay']"), dayOfBirth);
        Assert.assertEquals(getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthMonth']"), monthOfBirth);
        Assert.assertEquals(getSelectedOptionInDropdown(driver, "//select[@name='DateOfBirthYear']"), yearOfBirth);

        Assert.assertEquals(getAttributeValue(driver, "//input[@id='Company']", "value"), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
