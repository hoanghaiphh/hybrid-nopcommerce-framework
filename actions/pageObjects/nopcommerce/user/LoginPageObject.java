package pageObjects.nopcommerce.user;

import pageObjects.nopcommerce.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.user.LoginPageUI;

public class LoginPageObject extends BasePageObject {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

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
