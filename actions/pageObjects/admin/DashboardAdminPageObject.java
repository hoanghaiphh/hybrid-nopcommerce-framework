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
}
