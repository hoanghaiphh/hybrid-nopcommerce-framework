package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickOnRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickOnElement(driver, HomePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

    public CustomerInfoPageObject clickOnMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickOnElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getCustomerInfoPage(driver);
    }
}
