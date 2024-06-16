package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.DashboardAdminPageUI;

public class DashboardAdminPageObject extends BasePage {
    private WebDriver driver;

    public DashboardAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoutLinkDisplayed() {
        waitForElementVisible(driver, DashboardAdminPageUI.LOGOUT_LINK);
        return isElementDisplayed(driver, DashboardAdminPageUI.LOGOUT_LINK);
    }

    public void clickOnCustomersLink() {
        waitForElementClickable(driver, DashboardAdminPageUI.CUSTOMERS_LINK);
        clickOnElement(driver, DashboardAdminPageUI.CUSTOMERS_LINK);
        sleepInSecond(1);
    }

    public void clickOnPromotionsLink() {
        waitForElementClickable(driver, DashboardAdminPageUI.PROMOTIONS_LINK);
        clickOnElement(driver, DashboardAdminPageUI.PROMOTIONS_LINK);
        sleepInSecond(1);
    }

    public void clickOnSalesLink() {
        waitForElementClickable(driver, DashboardAdminPageUI.SALES_LINK);
        clickOnElement(driver, DashboardAdminPageUI.SALES_LINK);
        sleepInSecond(1);
    }

    public void clickOnSidebarLink(String linkName) {
        waitForElementClickable(driver, DashboardAdminPageUI.SIDEBAR_DYNAMIC_LINK, linkName);
        clickOnElement(driver, DashboardAdminPageUI.SIDEBAR_DYNAMIC_LINK, linkName);
        sleepInSecond(1);
    }
}
