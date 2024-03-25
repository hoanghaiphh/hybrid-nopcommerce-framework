package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPreviousPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToNextPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void dismissAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitForAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public Set<String> getAllWindowIDs(WebDriver driver) {
        return driver.getWindowHandles();
    }

    public void switchToWindow(WebDriver driver, String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                switchToWindow(driver, runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            switchToWindow(driver, runWindow);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsExceptParent(WebDriver driver, String parentID) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                switchToWindow(driver, runWindow);
                driver.close();
            }
        }
        switchToWindow(driver, parentID);
    }

    public By getXpathLocator(String locator) {
        return By.xpath(locator);
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(getXpathLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(getXpathLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(getXpathLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(getXpathLocator(locator)));
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getXpathLocator(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getXpathLocator(locator));
    }

    public void clickOnElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public Select getDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator));
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String item) {
        getDropdown(driver, locator).selectByVisibleText(item);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return getDropdown(driver, locator).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return getDropdown(driver, locator).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpathLocator(childItemLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementsSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        if (getElementsSize(driver, locator) != 0) {
            status = getElement(driver, locator).isDisplayed();
        }
        return status;
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public void checkCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            clickOnElement(driver, locator);
        }
    }

    public void uncheckCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            clickOnElement(driver, locator);
        }
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverOnElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void clickAndHoldOnElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseElement(WebDriver driver) {
        new Actions(driver).release().perform();
    }

    public void doubleClickOnElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void rightClickOnElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void sendKeyToElementByActions(WebDriver driver, String locator, Keys keyToSend) {
        new Actions(driver).sendKeys(getElement(driver, locator), keyToSend).perform();
    }

    public void scrollToElementByActions(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickOnElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(1);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnBottomByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String keyToSend) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + keyToSend + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getValidationMsg(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }
}
