package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class AddressesPageObject extends SidebarPageObject {
    private WebDriver driver;

    public AddressesPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
