package com.nopcommerce.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class User_01_Register_Login {
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
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText(dayOfBirth);
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(monthOfBirth);
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(yearOfBirth);
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).isDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='gender-male']")).isSelected());
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), dayOfBirth);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), monthOfBirth);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), yearOfBirth);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
