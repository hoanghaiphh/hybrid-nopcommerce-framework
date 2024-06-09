package pageObjects.user.myAccount;

import org.openqa.selenium.WebDriver;
import pageObjects.user.MyAccountPageObject;

public class ChangePasswordPageObjectMyAccount extends MyAccountPageObject {
    private WebDriver driver;

    public ChangePasswordPageObjectMyAccount(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
