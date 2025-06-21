package EjadaHooks;

import EjadaUIUtils.BrowserManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static EjadaUIUtils.BrowserManager.*;

public class EjadaHook {

    BrowserManager browserManager = new BrowserManager();
//    public static WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static String methodName;
    public static SimpleDateFormat sdf;
    public static String strDate;
    public static Calendar cal;

    static {
        extent = new ExtentReports();
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        strDate = sdf.format(cal.getTime());
        String reportFileName = "Ejada_Report_" + strDate + ".html";
        File reportDir = new File("test-output/EjadaReports/");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        spark = new ExtentSparkReporter(new File(reportDir, reportFileName));
        extent.attachReporter(spark);

        // Add system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
    }

    @Before
    public void ejadaBeforeScenario() throws InterruptedException {
        /*ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();*/
        browserManager.startBrowser();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @After
    public void ejadaAfterScenario() {
        extent.flush();
        browserManager.quiteBrowser();
    }
}
