package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementSelected(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeSelected(element));
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void sendKeyToElement(WebElement element, String keyToSend) {
        element.sendKeys(keyToSend);
    }

    public Select getDropdown(WebElement element) {
        return new Select(element);
    }

    public void selectItemInDropdown(WebElement element, String item) {
        getDropdown(element).selectByVisibleText(item);
    }

    public String getSelectedItemInDropdown(WebElement element) {
        return getDropdown(element).getFirstSelectedOption().getText();
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public int getElementsSize(List<WebElement> elements) {
        return elements.size();
    }

    public WebElement getFirstElementOfList (List<WebElement> elements) {
        return elements.get(0);
    }

    public void setImplicitlyWait(WebDriver driver, int timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    public boolean isElementDisplayed(WebDriver driver, List<WebElement> elements) {
        boolean status = false;
        setImplicitlyWait(driver, 3);
        if (getElementsSize(elements) != 0) {
            status = getFirstElementOfList(elements).isDisplayed();
        }
        setImplicitlyWait(driver, 15);
        return status;
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public void checkCheckboxOrRadio(WebElement element) {
        if (!isElementSelected(element)) {
            clickOnElement(element);
        }
    }
}
