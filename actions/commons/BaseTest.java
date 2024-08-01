package commons;

import com.aventstack.extentreports.Status;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import reportConfig.ExtentManager;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Random;

public class BaseTest {

    protected WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    protected final Logger log;
    protected BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case FIREFOX_HEADLESS:
                driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                break;
            case CHROME_HEADLESS:
                driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                break;
            case EDGE_HEADLESS:
                driver = new EdgeDriver(new EdgeOptions().addArguments("--headless=new"));
                break;
            case CHROME_PROFILE:
                driver = new ChromeDriver(new ChromeOptions()
                        .addArguments("--user-data-dir=C:\\Users\\HAIPH\\AppData\\Local\\Google\\Chrome\\User Data\\")
                        .addArguments("--profile-directory=Default"));
                break;
            default:
                throw new RuntimeException("Browser is not valid");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver openBrowserWithUrl(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        driver.get(url);
        return driver;
    }

    protected static long getRandomNumber() {
        return new Random().nextInt(999999);
    }

    protected void deleteAllFilesInFolder(String folder) {
        try {
            File[] listOfFiles = new File(folder).listFiles();
            for (File file : listOfFiles) {
                if (file.isFile() && !file.getName().equals("environment.properties")) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @BeforeSuite
    protected void clearReport() {
        deleteAllFilesInFolder(GlobalConstants.SCREENSHOTS_FOLDER_PATH);
        deleteAllFilesInFolder(GlobalConstants.ALLURE_RESULTS_FOLDER_PATH);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
//            extentAttachScreenshot(e);
            verificationFailed(e.getMessage());
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
//            extentAttachScreenshot(e);
            verificationFailed(e.getMessage());
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            verificationPassed(expected);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
//            extentAttachScreenshot(e);
            verificationFailed(e.getMessage());
        }
        return status;
    }

    @Step(">> verify passed: value = {0}")
    private void verificationPassed(Object expected) {
    }

    @Step(">> verify FAILED: {0}")
    private void verificationFailed(String msg) {
        allureAttachScreenshot();
    }

    @Attachment(value = "Verification failure Screenshot", type = "image/png")
    private byte[] allureAttachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

//    @BeforeMethod
    protected void extentStartTest(Method method) {
        ExtentManager.startTest(method.getName() + " - Run on " + BasePage.getCurrentBrowserName(driver), method.getName());
    }

    protected void extentLog(String msg) {
        ExtentManager.getTest().log(Status.INFO, msg);
    }

    protected void extentAttachScreenshot(Throwable e) {
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtentManager.getTest().log(Status.FAIL, "java.lang.AssertionError: " + e.getMessage(),
                ExtentManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

}
