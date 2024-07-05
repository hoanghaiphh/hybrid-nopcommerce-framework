package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageObject {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickOnGenderRadio() {
        waitForElementClickable(driver, RegisterPageUI.GENDER_MALE_RADIO);
        selectCheckboxOrRadio(driver, RegisterPageUI.GENDER_MALE_RADIO);
    }

    public void sendKeyToFirstnameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void sendKeyToLastnameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void selectItemInDayDropdown(String dayOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.DAY_DROPDOWN);
        selectOptionInDropdown(driver, RegisterPageUI.DAY_DROPDOWN, dayOfBirth);
    }

    public void selectItemInMonthDropdown(String monthOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.MONTH_DROPDOWN);
        selectOptionInDropdown(driver, RegisterPageUI.MONTH_DROPDOWN, monthOfBirth);
    }

    public void selectItemInYearDropdown(String yearOfBirth) {
        waitForElementClickable(driver, RegisterPageUI.YEAR_DROPDOWN);
        selectOptionInDropdown(driver, RegisterPageUI.YEAR_DROPDOWN, yearOfBirth);
    }

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, companyName);
    }

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void sendKeyToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickOnRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickOnElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }
}
