package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickOnElement(driver, HomePageUI.REGISTER_LINK);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

    public void clickOnMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickOnElement(driver, HomePageUI.MY_ACCOUNT_LINK);
    }
}
