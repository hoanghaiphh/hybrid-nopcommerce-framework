package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePageFactory extends BasePage {
    private WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registerLink;

    public void clickOnRegisterLink() {
        waitForElementClickable(driver, registerLink);
        clickOnElement(registerLink);
    }

    @FindBy(xpath = "//a[@class='ico-account' and text()='My account']")
    private List<WebElement> myAccountLinks;

    public boolean isMyAccountLinkDisplayed() {
        return isElementDisplayed(driver, myAccountLinks);
    }

    @FindBy(xpath = "//a[@class='ico-account' and text()='My account']")
    private WebElement myAccountLink;

    public void clickOnMyAccountLink() {
        waitForElementClickable(driver, myAccountLink);
        clickOnElement(myAccountLink);
    }
}
