package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.DashboardAdminPageObject;
import pageObjects.admin.LoginAdminPageObject;
import pageObjects.user.*;
import pageObjects.user.myAccount.AddressesPageObject;
import pageObjects.user.myAccount.ChangePasswordPageObject;
import pageObjects.user.myAccount.CustomerInfoPageObject;
import pageObjects.user.myAccount.OrdersPageObject;

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

    public static CustomerInfoPageObject getCustomerInfoPage(WebDriver driver) {
        return new CustomerInfoPageObject(driver);
    }

    public static AddressesPageObject getAddressesPage(WebDriver driver) {
        return new AddressesPageObject(driver);
    }

    public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
        return new ChangePasswordPageObject(driver);
    }

    public static OrdersPageObject getOrdersPage(WebDriver driver) {
        return new OrdersPageObject(driver);
    }

    public static DashboardAdminPageObject getDashboardAdminPage(WebDriver driver) {
        return new DashboardAdminPageObject(driver);
    }

    public static LoginAdminPageObject getLoginAdminPage(WebDriver driver) {
        return new LoginAdminPageObject(driver);
    }
}
