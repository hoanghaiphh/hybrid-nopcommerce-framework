package pageObjects.jqueryscript;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jqueryscript.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGINATION_BUTTON, pageNumber);
        clickOnElement(driver, HomePageUI.DYNAMIC_PAGINATION_BUTTON, pageNumber);
        sleepInSecond(1);
    }

    public boolean isPageSelectedByNumber(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGINATION_BUTTON, pageNumber);
        return getAttributeValue(driver, HomePageUI.DYNAMIC_PAGINATION_BUTTON, "class", pageNumber).endsWith("active");
    }

    public void clearAllSearchTextboxes() {
        waitForAllElementsVisible(driver, HomePageUI.ALL_SEARCH_TEXTBOXES);
        clearAllElementsText(driver, HomePageUI.ALL_SEARCH_TEXTBOXES);
    }

    public void searchValueByHeader(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_SEARCH_TEXTBOX, headerName);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_SEARCH_TEXTBOX, value, headerName);
        sendKeyToElementByActions(driver, HomePageUI.DYNAMIC_SEARCH_TEXTBOX, Keys.ENTER, headerName);
        sleepInSecond(1);
    }

    public boolean isDataRowDisplayed(String female, String country, String male, String total) {
//        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
    }

    public void removeOrEditDataRowByValue(String action, String headerName, String value) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ACTION_BUTTON, headerName.toLowerCase(), value, action);
        clickOnElement(driver, HomePageUI.DYNAMIC_ACTION_BUTTON, headerName.toLowerCase(), value, action);
        sleepInSecond(1);
    }

    public void clickOnSubmitButton() {
        waitForElementClickable(driver, HomePageUI.SUBMIT_BUTTON);
        clickOnElement(driver, HomePageUI.SUBMIT_BUTTON);
    }

    public List<String> getAllValueOfColumn(String columnName) {
        List<String> allValue = new ArrayList<String>();
        List<WebElement> allCellsOfColumn = getElements(driver, HomePageUI.DYNAMIC_ALL_CELLS_OF_COLUMN, columnName.toLowerCase());
        for (WebElement cellOfColumn: allCellsOfColumn) {
            allValue.add(cellOfColumn.getText());
//            System.out.println(cellOfColumn.getText());
        }
        return allValue;
    }

    public List<String> getAllValueOfRow(String rowIndex) {
        List<String> allValue = new ArrayList<String>();
        List<WebElement> allCellsOfRow = getElements(driver, HomePageUI.DYNAMIC_ALL_CELLS_OF_ROW, rowIndex);
        for (WebElement cellOfColumn: allCellsOfRow) {
            allValue.add(cellOfColumn.getText());
//            System.out.println(cellOfColumn.getText());
        }
        return allValue;
    }

//  --------------------------------------------------------------------------------------------------------------------

    public void loadDataToTable() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickOnElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public String getIndexOfColumn(String columnName) {
        return String.valueOf(getElementsSize(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_OF_COLUMN, columnName) +1);
    }

    public void sendKeyToCellTextbox(String rowIndex, String columnName, String keyToSend) {
        String columnIndex = getIndexOfColumn(columnName);
        waitForElementVisible(driver, HomePageUI.DYNAMIC_CELL_TEXTBOX, rowIndex, columnIndex);
        clearElementText(driver, HomePageUI.DYNAMIC_CELL_TEXTBOX, rowIndex, columnIndex);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_CELL_TEXTBOX, keyToSend, rowIndex, columnIndex);
    }

    public void selectValueFromCellDropdown(String rowIndex, String valueToSelect) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_CELL_DROPDOWN, rowIndex);
        selectItemInDropdown(driver, HomePageUI.DYNAMIC_CELL_DROPDOWN, valueToSelect, rowIndex);
    }

    public void checkOrUncheckCellCheckbox(String rowIndex, boolean status) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_CELL_CHECKBOX, rowIndex);
        if (status) {
            checkCheckboxOrRadio(driver, HomePageUI.DYNAMIC_CELL_CHECKBOX, rowIndex);
        } else {
            uncheckCheckbox(driver, HomePageUI.DYNAMIC_CELL_CHECKBOX, rowIndex);
        }
    }

    public void enterDateToCellDatePicker(String rowIndex, String date) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, rowIndex);
        clearElementText(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, rowIndex);
        if (getCurrentBrowserName(driver).equalsIgnoreCase("firefox")) {
            removeAttributeInDOM(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, "type", rowIndex);
            String convertedDate = date.substring(6) + "-" + date.substring(0, 2) + "-" + date.substring(3,5);
            sendKeyToElement(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, convertedDate, rowIndex);
            setAttributeInDOM(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, "type", "date", rowIndex);
        } else {
            sendKeyToElement(driver, HomePageUI.DYNAMIC_CELL_DATE_PICKER, date, rowIndex);
        }
    }

    public void enterAllValueToRow(String rowIndex, String company, String contactPerson, String country, boolean NPOStatus, String orderPlaced, String memberSince) {
        sendKeyToCellTextbox(rowIndex, "Company", company);
        sendKeyToCellTextbox(rowIndex, "Contact Person", contactPerson);
        selectValueFromCellDropdown(rowIndex, country);
        checkOrUncheckCellCheckbox(rowIndex, NPOStatus);
        sendKeyToCellTextbox(rowIndex, "Order Placed", orderPlaced);
        enterDateToCellDatePicker(rowIndex, memberSince);
        sleepInSecond(1);
    }

    public void clickOnCellActionButton(String rowIndex, String action) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_CELL_ACTION_BUTTON, rowIndex, action);
        clickOnElement(driver, HomePageUI.DYNAMIC_CELL_ACTION_BUTTON, rowIndex, action);
        sleepInSecond(1);
    }

}
