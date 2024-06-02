package pageObjects;

import commons.BasePage;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageUIs.SidebarPageUI;

public class SidebarPageObject extends BasePage {
    private WebDriver driver;

    public SidebarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddressesPageObject clickOnAddressesLink() {
        waitForElementClickable(driver, SidebarPageUI.ADDRESSES_LINK);
        clickOnElement(driver, SidebarPageUI.ADDRESSES_LINK);
        return PageGenerator.getAddressesPage(driver);
    }

    public ChangePasswordPageObject clickOnChangePasswordLink() {
        waitForElementClickable(driver, SidebarPageUI.CHANGE_PASSWORD_LINK);
        clickOnElement(driver, SidebarPageUI.CHANGE_PASSWORD_LINK);
        return PageGenerator.getChangePasswordPage(driver);
    }

    public CustomerInfoPageObject clickOnCustomerInfoLink() {
        waitForElementClickable(driver, SidebarPageUI.CUSTOMER_INFO_LINK);
        clickOnElement(driver, SidebarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getCustomerInfoPage(driver);
    }

    public OrdersPageObject clickOnOrdersLink() {
        waitForElementClickable(driver, SidebarPageUI.ORDERS_LINK);
        clickOnElement(driver, SidebarPageUI.ORDERS_LINK);
        return PageGenerator.getOrdersPage(driver);
    }
}
