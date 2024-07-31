package pageObjects.nopcommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import pageObjects.nopcommerce.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageObjects.nopcommerce.user.myAccount.CustomerInfoPageObject;
import pageUIs.nopcommerce.user.BasePageUI;

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

    @Step("My Account link displayed in header...")
    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, BasePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, BasePageUI.MY_ACCOUNT_LINK);
    }

    public CustomerInfoPageObject clickOnMyAccountLink() {
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

    @Step("Click on {0} link in header")
    public BasePageObject clickOnHeaderLink(String pageName) {
        waitForElementClickable(driver, BasePageUI.HEADER_DYNAMIC_LINK, pageName);
        clickOnElement(driver, BasePageUI.HEADER_DYNAMIC_LINK, pageName);
        return switch (pageName) {
            case ("Register") -> PageGenerator.getRegisterPage(driver);
            case ("My account") -> PageGenerator.getCustomerInfoPage(driver);
            case ("Log in") -> PageGenerator.getLoginPage(driver);
            case ("Log out") -> PageGenerator.getHomePage(driver);
            default -> throw new RuntimeException("Page name is not valid !!!");
        };
    }
}
