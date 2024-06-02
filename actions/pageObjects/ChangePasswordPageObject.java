package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObject extends SidebarPageObject {
    private WebDriver driver;

    public ChangePasswordPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
