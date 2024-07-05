package pageObjects.jQuery;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.jQuery.UploadFilesPageUI;

public class UploadFilesPO extends BasePage {
    private WebDriver driver;

    public UploadFilesPO(WebDriver driver) {
        this.driver = driver;
    }
    
    public void addFiles(String... fileNames) {
        StringBuilder filePath = new StringBuilder();
        for (String fileName : fileNames) {
            filePath.append(GlobalConstants.UPLOAD_FOLDER_PATH).append(fileName).append("\n");
        }
        sendKeysToElement(driver, UploadFilesPageUI.ADD_FILES_BUTTON, filePath.toString().trim());
    }
        /*String filePath = "";
        for (String fileName : fileNames) {
            filePath += GlobalConstants.UPLOAD_FOLDER_PATH + fileName + "\n";
        }
        sendKeyToElement(driver, UploadFilesPageUI.ADD_FILES_BUTTON, filePath.trim());*/

    public boolean isFileAdded(String... fileNames) {
        boolean status = false;
        for (String fileName: fileNames) {
            status = isElementDisplayed(driver, UploadFilesPageUI.DYNAMIC_ADDED_FILE, fileName);
            if (!status) {
                break;
            }
        }
        return status;
    }

    public boolean isFileAllowedToUpload(String... fileNames) {
        boolean isErrMsgDisplayed, isStartBtnEnabled;
        boolean status = false;
        for (String fileName: fileNames) {
            isErrMsgDisplayed = isElementDisplayed(driver, UploadFilesPageUI.DYNAMIC_ERROR_MESSAGE, fileName);
            isStartBtnEnabled = isElementEnabled(driver, UploadFilesPageUI.DYNAMIC_ADDED_FILE_ACTION_BUTTON, fileName, "start");
            status = !isErrMsgDisplayed && isStartBtnEnabled;
            if (!status) {
                break;
            }
        }
        return status;
    }

    public void actionForAddedFiles(String action, String... fileNames) {
        for (String fileName: fileNames) {
            waitForElementClickable(driver, UploadFilesPageUI.DYNAMIC_ADDED_FILE_ACTION_BUTTON, fileName, action);
            clickOnElement(driver, UploadFilesPageUI.DYNAMIC_ADDED_FILE_ACTION_BUTTON, fileName, action);
        }
    }

    public boolean isFileUploaded(String... fileNames) {
        boolean status = false;
        for (String fileName: fileNames) {
            status = isElementDisplayed(driver, UploadFilesPageUI.DYNAMIC_UPLOADED_FILE, fileName);
            if (!status) {
                break;
            }
        }
        return status;
    }

    public void deleteUploadedFiles(String... fileNames) {
        for (String fileName: fileNames) {
            waitForElementClickable(driver, UploadFilesPageUI.DYNAMIC_UPLOADED_FILE_DELETE_BUTTON, fileName);
            clickOnElement(driver, UploadFilesPageUI.DYNAMIC_UPLOADED_FILE_DELETE_BUTTON, fileName);
        }
    }

    public void actionForAllFiles(String action) {
        waitForElementClickable(driver, UploadFilesPageUI.DYNAMIC_ALL_FILES_ACTION_BUTTON, action);
        clickOnElement(driver, UploadFilesPageUI.DYNAMIC_ALL_FILES_ACTION_BUTTON, action);
    }

    public void selectAllFilesToDelete() {
        waitForElementClickable(driver, UploadFilesPageUI.SELECT_ALL_CHECKBOX);
        selectCheckboxOrRadio(driver, UploadFilesPageUI.SELECT_ALL_CHECKBOX);
    }

    public void deleteAllUploadedFiles() {
        selectAllFilesToDelete();
        actionForAllFiles("delete");
    }

}
