package com.jQuery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.PageGenerator;
import pageObjects.jQuery.UploadFilesPO;

public class Level_12_Upload_Files extends BaseTest {
    private WebDriver driver;
    private UploadFilesPO uploadFiles;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = openBrowserWithUrl(browserName, url);
        uploadFiles = PageGenerator.getUploadFilesPage(driver);
    }

    @Test
    public void TC_01() {
        // add files to upload
        uploadFiles.addFiles("avatar.jpg", "jQuery.txt", "large.jpg", "santa.ico", "snow.png", "topic13.png");

        // verify all files added
        Assert.assertTrue(uploadFiles.isFileAdded("avatar.jpg", "jQuery.txt", "large.jpg", "santa.ico", "snow.png", "topic13.png"));

        // verify file type allowed/ not-allowed
        Assert.assertTrue(uploadFiles.isFileAllowedToUpload("avatar.jpg", "snow.png", "topic13.png"));
        Assert.assertFalse(uploadFiles.isFileAllowedToUpload("large.jpg", "jQuery.txt", "santa.ico"));

        // cancel not-allowed files
        uploadFiles.actionForAddedFiles("cancel", "large.jpg", "jQuery.txt", "santa.ico");

        // verify not-allowed files canceled
        Assert.assertFalse(uploadFiles.isFileAdded("large.jpg", "jQuery.txt", "santa.ico"));

        // upload allowed files
        uploadFiles.actionForAddedFiles("start", "avatar.jpg", "snow.png", "topic13.png");

        // verify allowed files uploaded
        Assert.assertTrue(uploadFiles.isFileUploaded("avatar.jpg", "snow.png", "topic13.png"));

        // delete uploaded files
        uploadFiles.deleteUploadedFiles("avatar.jpg", "snow.png", "topic13.png");
        uploadFiles.sleepInSeconds(1);

        // verify uploaded files deleted
        Assert.assertFalse(uploadFiles.isFileUploaded("avatar.jpg", "snow.png", "topic13.png"));
    }

    @Test
    public void TC_02() {
        uploadFiles.addFiles("avatar.jpg", "jQuery.txt", "large.jpg", "santa.ico", "snow.png", "topic13.png");
        Assert.assertTrue(uploadFiles.isFileAdded("avatar.jpg", "jQuery.txt", "large.jpg", "santa.ico", "snow.png", "topic13.png"));
        Assert.assertTrue(uploadFiles.isFileAllowedToUpload("avatar.jpg", "snow.png", "topic13.png"));
        Assert.assertFalse(uploadFiles.isFileAllowedToUpload("large.jpg", "jQuery.txt", "santa.ico"));

        uploadFiles.actionForAllFiles("start");
        Assert.assertTrue(uploadFiles.isFileUploaded("avatar.jpg", "snow.png", "topic13.png"));
        uploadFiles.actionForAllFiles("cancel");
        Assert.assertFalse(uploadFiles.isFileAdded("large.jpg", "jQuery.txt", "santa.ico"));

        uploadFiles.deleteAllUploadedFiles();
        uploadFiles.sleepInSeconds(1);
        Assert.assertFalse(uploadFiles.isFileUploaded("avatar.jpg", "snow.png", "topic13.png"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
