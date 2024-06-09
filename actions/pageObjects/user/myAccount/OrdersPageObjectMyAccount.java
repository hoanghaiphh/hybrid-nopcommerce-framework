package pageObjects.user.myAccount;

import org.openqa.selenium.WebDriver;
import pageObjects.user.MyAccountPageObject;

public class OrdersPageObjectMyAccount extends MyAccountPageObject {
    private WebDriver driver;

    public OrdersPageObjectMyAccount(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
