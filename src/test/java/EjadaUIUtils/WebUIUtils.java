package EjadaUIUtils;

import EjadaException.FrameworkException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static EjadaHooks.EjadaHook.*;
import static EjadaLocators.EjadaLocators.*;

public class WebUIUtils {

    public static void scrollBy(int xOffset, int yOffset) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", xOffset, yOffset);
        }
    }

    public void scrollToElement (WebElement element)
    {
        if (driver instanceof JavascriptExecutor)
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public static void staticWait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread.sleep interrupted: " + e.getMessage());
        }
    }

    public static List<WebElement> findElements (By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Increased wait time
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            List<WebElement> elements = (driver).findElements(locator);
            if (elements.isEmpty()) {
                System.err.println("No elements found (presence of all): " + locator);
                // Decide if this should throw an exception or return an empty list based on expected usage.
                // For now, returning empty list and logging, as findElements usually doesn't throw if nothing is found.
                // However, the wait.until would throw TimeoutException if NO elements ever become present.
            }
            return elements;
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for all elements: " + locator.toString() + " due to " + e.getMessage());
            throw new FrameworkException("Timeout waiting for presence of all elements: " + locator, e);
        }
    }

    public static Select select (By locator) {
        WebDriverWait wait = new WebDriverWait( driver,Duration.ofSeconds(10)); // Increased wait time
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator)); // Ensure select tag itself is present
            return new Select(( driver).findElement(locator));
        } catch (TimeoutException e) {
            System.err.println("Select element not found: " + locator.toString() + " due to " + e.getMessage());
            throw new FrameworkException("Select element not found after waiting: " + locator, e);
        }
    }

    public static boolean isElementPresent(By locatorKey) {
        try {
            findElement(locatorKey);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
