package pageObjects.nopcommerce.user;

import io.qameta.allure.Step;
import pageObjects.nopcommerce.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.LoginPageUI;

public class LoginPageObject extends BasePageObject {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Input value into Email textbox: {0}")
    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Input value into Password textbox: {0}")
    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click on Login button")
    public void clickOnLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickOnElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public HomePageObject loginToSystem(String emailAddress, String password) {
        sendKeyToEmailTextbox(emailAddress);
        sendKeyToPasswordTextbox(password);
        clickOnLoginButton();
        return PageGenerator.getHomePage(driver);
    }
}
