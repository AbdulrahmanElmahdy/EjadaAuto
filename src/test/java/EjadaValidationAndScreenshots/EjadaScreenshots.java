package EjadaValidationAndScreenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static EjadaHooks.EjadaHook.*;

public class EjadaScreenshots {

    public static String filePath;

    public static void ejadaPassedScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        strDate = sdf.format(cal.getTime());
        String name = ("Passed" + " - " + methodName ).concat(strDate).concat(".png");
        File destination = new File("D:\\Ejada\\EjadaAutomation\\test-output\\EjadaScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }

    public static void ejadaFailedScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        strDate = sdf.format(cal.getTime());
        String name = ("Failed" + " - " + methodName ).concat(strDate).concat(".png");
        File destination = new File("D:\\Ejada\\EjadaAutomation\\test-output\\EjadaScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }
}
