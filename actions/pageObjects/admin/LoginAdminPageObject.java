package pageObjects.admin;

import commons.BasePage;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.LoginAdminPageUI;

public class LoginAdminPageObject extends BasePage {
    private WebDriver driver;

    public LoginAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginAdminPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginAdminPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginAdminPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginAdminPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickOnLoginButton() {
        waitForElementClickable(driver, LoginAdminPageUI.LOGIN_BUTTON);
        clickOnElement(driver, LoginAdminPageUI.LOGIN_BUTTON);
    }

    public DashboardAdminPageObject loginToSystem(String emailAddress, String password) {
        sendKeyToEmailTextbox(emailAddress);
        sendKeyToPasswordTextbox(password);
        clickOnLoginButton();
        return PageGenerator.getDashboardAdminPage(driver);
    }

}
