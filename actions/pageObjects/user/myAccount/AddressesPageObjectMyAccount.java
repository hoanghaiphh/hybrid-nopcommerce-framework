package pageObjects.user.myAccount;

import org.openqa.selenium.WebDriver;
import pageObjects.user.MyAccountPageObject;

public class AddressesPageObjectMyAccount extends MyAccountPageObject {
    private WebDriver driver;

    public AddressesPageObjectMyAccount(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
