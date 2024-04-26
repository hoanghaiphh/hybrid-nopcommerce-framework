package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageFactory extends BasePage {
    private WebDriver driver;

    public CustomerInfoPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='gender-male']")
    private WebElement genderMaleRadio;

    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, genderMaleRadio);
        return isElementSelected(genderMaleRadio);
    }

    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameTextbox;

    public String getValueInFirstnameTextbox() {
        waitForElementVisible(driver, firstNameTextbox);
        return getAttributeValue(firstNameTextbox, "value");
    }

    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameTextbox;

    public String getValueInLastnameTextbox() {
        waitForElementVisible(driver, lastNameTextbox);
        return getAttributeValue(lastNameTextbox, "value");
    }

    @FindBy(xpath = "//select[@name='DateOfBirthDay']")
    private WebElement dayDropdown;

    public String getValueInDayDropdown() {
        waitForElementClickable(driver, dayDropdown);
        return getSelectedItemInDropdown(dayDropdown);
    }

    @FindBy(xpath = "//select[@name='DateOfBirthMonth']")
    private WebElement monthDropdown;

    public String getValueInMonthDropdown() {
        waitForElementClickable(driver, monthDropdown);
        return getSelectedItemInDropdown(monthDropdown);
    }

    @FindBy(xpath = "//select[@name='DateOfBirthYear']")
    private WebElement yearDropdown;

    public String getValueInYearDropdown() {
        waitForElementClickable(driver, yearDropdown);
        return getSelectedItemInDropdown(yearDropdown);
    }

    @FindBy(xpath = "//input[@id='Company']")
    private WebElement companyTextbox;

    public String getValueInCompanyTextbox() {
        waitForElementVisible(driver, companyTextbox);
        return getAttributeValue(companyTextbox, "value");
    }
}
