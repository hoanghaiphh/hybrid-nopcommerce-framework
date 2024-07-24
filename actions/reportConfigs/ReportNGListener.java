package reportConfigs;

import commons.BaseTest;
import commons.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener extends BaseTest implements ITestListener {

    private String getScreenshotPath(WebDriver driver, String screenshotName) {
        try {
            String screenshotTime = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
            String screenshotPath = GlobalConstants.SCREENSHOTS_FOLDER_PATH + screenshotName + "_" + screenshotTime + ".png";
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);
            return "file:///" + screenshotPath;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    private String getScreenshotPath(WebDriver driver) {
        try {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            return "data:image/png;base64," + screenshot;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " FAILED !!!");

        System.setProperty("org.uncommons.reportng.escape-output", "false");

        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String screenshotPath = getScreenshotPath(driver, result.getName()); // OutputType.FILE
//        String screenshotPath = getScreenshotPath(driver); // OutputType.BASE64

        Reporter.getCurrentTestResult();
        Reporter.log("<br><a target=\"_blank\" href=\"" + screenshotPath + "\"> <img src=\"" + screenshotPath + "\" height='200' width='300'/> </a></br>");
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

}
