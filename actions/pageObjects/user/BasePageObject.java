package pageObjects.user;

import commons.BasePage;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageObjects.user.myAccount.CustomerInfoPageObjectMyAccount;
import pageUIs.user.BasePageUI;

public class BasePageObject extends BasePage {
    private WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickOnRegisterLink() {
        waitForElementClickable(driver, BasePageUI.REGISTER_LINK);
        clickOnElement(driver, BasePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, BasePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, BasePageUI.MY_ACCOUNT_LINK);
    }

    public CustomerInfoPageObjectMyAccount clickOnMyAccountLink() {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
        clickOnElement(driver, BasePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getCustomerInfoPage(driver);
    }

    public LoginPageObject clickOnLoginLink() {
        waitForElementClickable(driver, BasePageUI.LOGIN_LINK);
        clickOnElement(driver, BasePageUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }

    public HomePageObject clickOnLogoutLink() {
        if (isElementDisplayed(driver, BasePageUI.LOGOUT_LINK)) {
            waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
            clickOnElement(driver, BasePageUI.LOGOUT_LINK);
        }
        return PageGenerator.getHomePage(driver);
    }
}
