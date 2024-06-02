package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPageFactory extends BasePage {
    private WebDriver driver;

    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='gender-male']")
    @CacheLookup
    private WebElement genderMaleRadio;

    public void clickOnGenderRadio() {
        waitForElementClickable(driver, genderMaleRadio);
        checkCheckboxOrRadio(genderMaleRadio);
    }

    @FindBy(xpath = "//input[@id='FirstName']")
    @CacheLookup
    private WebElement firstNameTextbox;

    public void sendKeyToFirstnameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    @FindBy(xpath = "//input[@id='LastName']")
    @CacheLookup
    private WebElement lastNameTextbox;

    public void sendKeyToLastnameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox, lastName);
    }

    @FindBy(xpath = "//select[@name='DateOfBirthDay']")
    @CacheLookup
    private WebElement dayDropdown;

    public void selectItemInDayDropdown(String dayOfBirth) {
        waitForElementClickable(driver, dayDropdown);
        selectItemInDropdown(dayDropdown, dayOfBirth);
    }

    @FindBy(xpath = "//select[@name='DateOfBirthMonth']")
    @CacheLookup
    private WebElement monthDropdown;

    public void selectItemInMonthDropdown(String monthOfBirth) {
        waitForElementClickable(driver, monthDropdown);
        selectItemInDropdown(monthDropdown, monthOfBirth);
    }

    @FindBy(xpath = "//select[@name='DateOfBirthYear']")
    @CacheLookup
    private WebElement yearDropdown;

    public void selectItemInYearDropdown(String yearOfBirth) {
        waitForElementClickable(driver, yearDropdown);
        selectItemInDropdown(yearDropdown, yearOfBirth);
    }

    @FindBy(xpath = "//input[@id='Email']")
    @CacheLookup
    private WebElement emailTextbox;

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox, emailAddress);
    }

    @FindBy(xpath = "//input[@id='Company']")
    @CacheLookup
    private WebElement companyTextbox;

    public void sendKeyToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, companyTextbox);
        sendKeyToElement(companyTextbox, companyName);
    }

    @FindBy(xpath = "//input[@id='Password']")
    @CacheLookup
    private WebElement passwordTextbox;

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    @CacheLookup
    private WebElement confirmPasswordTextbox;

    public void sendKeyToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(confirmPasswordTextbox, password);
    }

    @FindBy(xpath = "//button[@id='register-button']")
    @CacheLookup
    private WebElement registerButton;

    public void clickOnRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickOnElement(registerButton);
    }

    @FindBy(xpath = "//div[@class='result']")
    @CacheLookup
    private WebElement registerSuccessMsg;

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMsg);
        return getElementText(registerSuccessMsg);
    }

    @FindBy(xpath = "//a[@class='ico-logout']")
    @CacheLookup
    private List<WebElement> logoutLink;

    public void clickOnLogoutLink() {
        if (isElementDisplayed(driver, logoutLink)) {
            waitForElementClickable(driver, getFirstElementOfList(logoutLink));
            clickOnElement(getFirstElementOfList(logoutLink));
        }
    }

    @FindBy(xpath = "//a[@class='ico-login']")
    @CacheLookup
    private WebElement loginLink;

    public void clickOnLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickOnElement(loginLink);
    }
}
