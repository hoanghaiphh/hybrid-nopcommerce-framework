package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnGenderRadio() {
        waitForElementClickable(driver, RegisterPageUI.GENDER_MALE_RADIO);
        checkCheckboxOrRadio(driver, RegisterPageUI.GENDER_MALE_RADIO);
    }

    public void sendKeyToFirstnameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void sendKeyToLastnameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void selectItemInDayDropdown(String dayOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.DAY_DROPDOWN);
        selectItemInDropdown(driver, RegisterPageUI.DAY_DROPDOWN, dayOfBirth);
    }

    public void selectItemInMonthDropdown(String monthOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.MONTH_DROPDOWN);
        selectItemInDropdown(driver, RegisterPageUI.MONTH_DROPDOWN, monthOfBirth);
    }

    public void selectItemInYearDropdown(String yearOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.YEAR_DROPDOWN);
        selectItemInDropdown(driver, RegisterPageUI.YEAR_DROPDOWN, yearOfBirth);
    }

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, companyName);
    }

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void sendKeyToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickOnRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickOnElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public void clickOnLogoutLink() {
        if (isElementDisplayed(driver, RegisterPageUI.LOGOUT_LINK)) {
            waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
            clickOnElement(driver, RegisterPageUI.LOGOUT_LINK);
        }
    }

    public void clickOnLoginLink() {
        waitForElementClickable(driver, RegisterPageUI.LOGIN_LINK);
        clickOnElement(driver, RegisterPageUI.LOGIN_LINK);
    }
}
