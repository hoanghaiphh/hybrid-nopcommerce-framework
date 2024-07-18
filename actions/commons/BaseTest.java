package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();
    private EdgeOptions edgeOptions = new EdgeOptions();

    protected final Logger log; // protected final Log log; --> log4j ver 1.x

    protected BaseTest() {
        log = LogManager.getLogger(getClass()); // log = LogFactory.getLog(getClass()); --> log4j ver 1.x
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
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case CHROME_HEADLESS:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE_HEADLESS:
                edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                break;
            case CHROME_PROFILE:
                chromeOptions.addArguments("--user-data-dir=C:\\Users\\HAIPH\\AppData\\Local\\Google\\Chrome\\User Data\\").addArguments("--profile-directory=Default");
                driver = new ChromeDriver(chromeOptions);
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

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info(e);
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
            log.info(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info(e);
        }
        return status;
    }

}
