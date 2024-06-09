package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();
    private EdgeOptions edgeOptions = new EdgeOptions();

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case CHROME:
                // chromeOptions.addArguments("--headless=new");
                // chromeOptions.addArguments("--user-data-dir=C:\\Users\\HAIPH\\AppData\\Local\\Google\\Chrome\\User Data\\").addArguments("--profile-directory=Default");
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE:
                edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser is not valid");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    protected static long getRandomNumber() {
        return new Random().nextInt(999999);
    }
}
