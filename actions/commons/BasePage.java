package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
    }

    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//  Browser ------------------------------------------------------------------------------------------------------------

    protected String getCurrentBrowserName(WebDriver driver) {
        return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPreviousPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToNextPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void dismissAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitForAlertPresence(driver).sendKeys(keyToSend);
    }

    private String getWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    private Set<String> getAllWindowIDs(WebDriver driver) {
        return driver.getWindowHandles();
    }

    private void switchToWindow(WebDriver driver, String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    protected void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                switchToWindow(driver, runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            switchToWindow(driver, runWindow);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    protected void closeAllWindowsExceptParent(WebDriver driver, String parentID) {
        Set<String> allWindows = getAllWindowIDs(driver);
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                switchToWindow(driver, runWindow);
                driver.close();
            }
        }
        switchToWindow(driver, parentID);
    }

//  Element ------------------------------------------------------------------------------------------------------------

    private By getByLocator(String locator) {
        By by = null;
        if (locator.toUpperCase().startsWith("CSS")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.toUpperCase().startsWith("XPATH")) {
            by = By.xpath(locator.substring(6));
        } else if (locator.toUpperCase().startsWith("ID")) {
            by = By.id(locator.substring(3));
        } else if (locator.toUpperCase().startsWith("CLASS")) {
            by = By.className(locator.substring(6));
        } else if (locator.toUpperCase().startsWith("NAME")) {
            by = By.name(locator.substring(5));
        } else {
            throw new RuntimeException("Locator type is not supported !!!");
        }
        return by;
    }

    private String castParameter(String dynamicLocator, String... restParameter) {
        return String.format(dynamicLocator, (Object[]) restParameter);
    }

    private By getDynamicLocator(String dynamicLocator, String... restParameter) {
        return getByLocator(castParameter(dynamicLocator, restParameter));
    }

    protected void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementPresence(WebDriver driver, String dynamicLocator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getDynamicLocator(dynamicLocator, restParameter)));
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementVisible(WebDriver driver, String dynamicLocator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getDynamicLocator(dynamicLocator, restParameter)));
    }

    protected void waitForAllElementsVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String dynamicLocator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getDynamicLocator(dynamicLocator, restParameter)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String dynamicLocator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getDynamicLocator(dynamicLocator, restParameter)));
    }

    protected void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    protected void waitForElementSelected(WebDriver driver, String dynamicLocator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getDynamicLocator(dynamicLocator, restParameter)));
    }

    private WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    private WebElement getElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        return driver.findElement(getDynamicLocator(dynamicLocator, restParameter));
    }

    protected List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getElements(WebDriver driver, String dynamicLocator, String... restParameter) {
        return driver.findElements(getDynamicLocator(dynamicLocator, restParameter));
    }

    public void clickOnElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickOnElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        getElement(driver, dynamicLocator, restParameter).click();
    }

    protected void clearElementText(WebDriver driver, String locator) {
        getElement(driver, locator).clear();
    }

    protected void clearElementText(WebDriver driver, String dynamicLocator, String... restParameter) {
        getElement(driver, dynamicLocator, restParameter).clear();
    }

    protected void clearAllElementsText(WebDriver driver, String locator) {
        List<WebElement> allElements = getElements(driver, locator);
        for (WebElement element : allElements) {
            element.clear();
        }
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver, String dynamicLocator, String keyToSend, String... restParameter) {
        getElement(driver, dynamicLocator, restParameter).sendKeys(keyToSend);
    }

    private Select getDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator));
    }

    private Select getDropdown(WebDriver driver, String dynamicLocator, String... restParameter) {
        return new Select(getElement(driver, dynamicLocator, restParameter));
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String item) {
        getDropdown(driver, locator).selectByVisibleText(item);
    }

    public void selectItemInDropdown(WebDriver driver, String dynamicLocator, String item, String... restParameter) {
        getDropdown(driver, dynamicLocator, restParameter).selectByVisibleText(item);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return getDropdown(driver, locator).getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDropdown(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getDropdown(driver, dynamicLocator, restParameter).getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        return getDropdown(driver, locator).isMultiple();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getDropdown(driver, dynamicLocator, restParameter).isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
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

    public String getElementText(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getElement(driver, dynamicLocator, restParameter).getText();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String dynamicLocator, String attributeName, String... restParameter) {
        return getElement(driver, dynamicLocator, restParameter).getAttribute(attributeName);
    }

    protected String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    protected String getCssValue(WebDriver driver, String dynamicLocator, String propertyName, String... restParameter) {
        return getElement(driver, dynamicLocator, restParameter).getCssValue(propertyName);
    }

    protected String getHexFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementsSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    protected int getElementsSize(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getElements(driver, dynamicLocator, restParameter).size();
    }

    private void setImplicitlyWait(WebDriver driver, long timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        setImplicitlyWait(driver, GlobalConstants.SHORT_TIMEOUT);
        if (getElementsSize(driver, locator) != 0) {
            status = getElement(driver, locator).isDisplayed();
        }
        setImplicitlyWait(driver, GlobalConstants.LONG_TIMEOUT);
        return status;
    }

    public boolean isElementDisplayed(WebDriver driver, String dynamicLocator, String... restParameter) {
        boolean status = false;
        setImplicitlyWait(driver, GlobalConstants.SHORT_TIMEOUT);
        if (getElementsSize(driver, dynamicLocator, restParameter) != 0) {
            status = getElement(driver, dynamicLocator, restParameter).isDisplayed();
        }
        setImplicitlyWait(driver, GlobalConstants.LONG_TIMEOUT);
        return status;
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getElement(driver, dynamicLocator, restParameter).isSelected();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected boolean isElementEnabled(WebDriver driver, String dynamicLocator, String... restParameter) {
        return getElement(driver, dynamicLocator, restParameter).isEnabled();
    }

    protected void checkCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            clickOnElement(driver, locator);
        }
    }

    protected void checkCheckboxOrRadio(WebDriver driver, String dynamicLocator, String... restParameter) {
        if (!isElementSelected(driver, dynamicLocator, restParameter)) {
            clickOnElement(driver, dynamicLocator, restParameter);
        }
    }

    protected void uncheckCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            clickOnElement(driver, locator);
        }
    }

    protected void uncheckCheckbox(WebDriver driver, String dynamicLocator, String... restParameter) {
        if (isElementSelected(driver, dynamicLocator, restParameter)) {
            clickOnElement(driver, dynamicLocator, restParameter);
        }
    }

    protected void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    protected void switchToFrame(WebDriver driver, String dynamicLocator, String... restParameter) {
        driver.switchTo().frame(getElement(driver, dynamicLocator, restParameter));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

//  Actions ------------------------------------------------------------------------------------------------------------

    protected void hoverOnElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    protected void hoverOnElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        new Actions(driver).moveToElement(getElement(driver, dynamicLocator, restParameter)).perform();
    }

    protected void clickAndHoldOnElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    protected void clickAndHoldOnElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        new Actions(driver).clickAndHold(getElement(driver, dynamicLocator, restParameter)).perform();
    }

    protected void releaseElement(WebDriver driver) {
        new Actions(driver).release().perform();
    }

    protected void doubleClickOnElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    protected void doubleClickOnElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        new Actions(driver).doubleClick(getElement(driver, dynamicLocator, restParameter)).perform();
    }

    protected void rightClickOnElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    protected void rightClickOnElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        new Actions(driver).contextClick(getElement(driver, dynamicLocator, restParameter)).perform();
    }

    protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    protected void sendKeyToElementByActions(WebDriver driver, String locator, Keys keyToSend) {
        new Actions(driver).sendKeys(getElement(driver, locator), keyToSend).perform();
    }

    protected void sendKeyToElementByActions(WebDriver driver, String dynamicLocator, Keys keyToSend, String... restParameter) {
        new Actions(driver).sendKeys(getElement(driver, dynamicLocator, restParameter), keyToSend).perform();
    }

    protected void scrollToElementByActions(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    protected void scrollToElementByActions(WebDriver driver, String dynamicLocator, String... restParameter) {
        new Actions(driver).scrollToElement(getElement(driver, dynamicLocator, restParameter)).perform();
    }

//  JavascriptExecutor -------------------------------------------------------------------------------------------------

    protected void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    protected void hightlightElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    protected void clickOnElementByJS(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleepInSecond(1);
    }

    protected void clickOnElementByJS(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleepInSecond(1);
    }

    protected void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElementOnTopByJS(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElementOnBottomByJS(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    protected void scrollToElementOnBottomByJS(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    protected void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void sendKeyToElementByJS(WebDriver driver, String locator, String keyToSend) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + keyToSend + "')", element);
    }

    protected void sendKeyToElementByJS(WebDriver driver, String dynamicLocator, String keyToSend, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + keyToSend + "')", element);
    }

    protected String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        WebElement element = getElement(driver, locator);
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].getAttribute('" + attributeName + "');", element);
    }

    protected String getAttributeInDOMByJS(WebDriver driver, String dynamicLocator, String attributeName, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].getAttribute('" + attributeName + "');", element);
    }

    protected void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", element);
    }

    protected void setAttributeInDOM(WebDriver driver, String dynamicLocator, String attributeName, String attributeValue, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        WebElement element = getElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String dynamicLocator, String attributeName, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
    }

    protected String getValidationMsg(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    protected String getValidationMsg(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
                + "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }

    protected boolean isImageLoaded(WebDriver driver, String dynamicLocator, String... restParameter) {
        WebElement element = getElement(driver, dynamicLocator, restParameter);
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
                + "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }

}
