package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import commons.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {

        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.EXTENT_REPORTS_FOLDER_PATH + "extentReport.html");
        reporter.config().setReportName("NopCommerce HTML Report");
        reporter.config().setDocumentTitle("NopCommerce HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Automation FC");
        extentReports.setSystemInfo("Project", "NopCommerce");
        extentReports.setSystemInfo("Team", "Automation Team");
        extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
        extentReports.setSystemInfo("OS", GlobalConstants.OS_NAME);

        return extentReports;
    }

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = createExtentReports().createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

}