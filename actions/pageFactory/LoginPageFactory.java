package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends BasePage {
    private WebDriver driver;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailTextbox;

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox, emailAddress);
    }

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordTextbox;

    public void sendKeyToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    @FindBy(xpath = "//button[@class='button-1 login-button']")
    private WebElement loginButton;

    public void clickOnLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickOnElement(loginButton);
    }
}
