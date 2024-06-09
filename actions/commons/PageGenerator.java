package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.DashboardAdminPageObject;
import pageObjects.admin.LoginAdminPageObject;
import pageObjects.user.*;
import pageObjects.user.myAccount.AddressesPageObjectMyAccount;
import pageObjects.user.myAccount.ChangePasswordPageObjectMyAccount;
import pageObjects.user.myAccount.CustomerInfoPageObjectMyAccount;
import pageObjects.user.myAccount.OrdersPageObjectMyAccount;

public class PageGenerator {
    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static CustomerInfoPageObjectMyAccount getCustomerInfoPage(WebDriver driver) {
        return new CustomerInfoPageObjectMyAccount(driver);
    }

    public static AddressesPageObjectMyAccount getAddressesPage(WebDriver driver) {
        return new AddressesPageObjectMyAccount(driver);
    }

    public static ChangePasswordPageObjectMyAccount getChangePasswordPage(WebDriver driver) {
        return new ChangePasswordPageObjectMyAccount(driver);
    }

    public static OrdersPageObjectMyAccount getOrdersPage(WebDriver driver) {
        return new OrdersPageObjectMyAccount(driver);
    }

    public static DashboardAdminPageObject getDashboardAdminPage(WebDriver driver) {
        return new DashboardAdminPageObject(driver);
    }

    public static LoginAdminPageObject getLoginAdminPage(WebDriver driver) {
        return new LoginAdminPageObject(driver);
    }
}
