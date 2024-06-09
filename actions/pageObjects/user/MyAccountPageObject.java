package pageObjects.user;

import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageObjects.user.myAccount.AddressesPageObjectMyAccount;
import pageObjects.user.myAccount.ChangePasswordPageObjectMyAccount;
import pageObjects.user.myAccount.CustomerInfoPageObjectMyAccount;
import pageObjects.user.myAccount.OrdersPageObjectMyAccount;
import pageUIs.user.MyAccountPageUI;

public class MyAccountPageObject extends BasePageObject {
    private WebDriver driver;

    public MyAccountPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AddressesPageObjectMyAccount clickOnAddressesLink() {
        waitForElementClickable(driver, MyAccountPageUI.ADDRESSES_LINK);
        clickOnElement(driver, MyAccountPageUI.ADDRESSES_LINK);
        return PageGenerator.getAddressesPage(driver);
    }

    public ChangePasswordPageObjectMyAccount clickOnChangePasswordLink() {
        waitForElementClickable(driver, MyAccountPageUI.CHANGE_PASSWORD_LINK);
        clickOnElement(driver, MyAccountPageUI.CHANGE_PASSWORD_LINK);
        return PageGenerator.getChangePasswordPage(driver);
    }

    public CustomerInfoPageObjectMyAccount clickOnCustomerInfoLink() {
        waitForElementClickable(driver, MyAccountPageUI.CUSTOMER_INFO_LINK);
        clickOnElement(driver, MyAccountPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getCustomerInfoPage(driver);
    }

    public OrdersPageObjectMyAccount clickOnOrdersLink() {
        waitForElementClickable(driver, MyAccountPageUI.ORDERS_LINK);
        clickOnElement(driver, MyAccountPageUI.ORDERS_LINK);
        return PageGenerator.getOrdersPage(driver);
    }
}
