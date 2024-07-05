package pageObjects.nopcommerce.user.myAccount;

import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.user.MyAccountPageObject;
import pageUIs.nopcommerce.user.myAccount.CustomerInfoPageUI;

public class CustomerInfoPageObject extends MyAccountPageObject {
    private WebDriver driver;

    public CustomerInfoPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
        return isElementSelected(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    public String getValueInFirstnameTextbox() {
        waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getValueInLastnameTextbox() {
        waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getValueInDayDropdown() {
        waitForElementClickable(driver, CustomerInfoPageUI.DAY_DROPDOWN);
        return getSelectedOptionInDropdown(driver, CustomerInfoPageUI.DAY_DROPDOWN);
    }

    public String getValueInMonthDropdown() {
        waitForElementClickable(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
        return getSelectedOptionInDropdown(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
    }

    public String getValueInYearDropdown() {
        waitForElementClickable(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
        return getSelectedOptionInDropdown(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
    }

    public String getValueInCompanyTextbox() {
        waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
        return getAttributeValue(driver, CustomerInfoPageUI.COMPANY_TEXTBOX, "value");
    }
}
