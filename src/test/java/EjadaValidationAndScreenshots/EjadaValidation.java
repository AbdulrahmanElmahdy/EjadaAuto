package EjadaValidationAndScreenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static EjadaHooks.EjadaHook.*;
import static EjadaUIUtils.BrowserManager.*;

public class EjadaValidation {

    public static String invalidErrorMessage = "Username and password do not match";

    public static boolean validation (By locator, String message){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return ( driver).findElement(locator).getText().equalsIgnoreCase(message);
    }

    public static boolean validationNotEmpty (By locator,  String att){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return !( driver).findElement(locator).getAttribute(att).isEmpty();
    }

    public static boolean validationContain (By locator,  String contain){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return ( driver).findElement(locator).getText().contains(contain);
    }

    public static boolean validationDisplayed (By locator){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return ( driver).findElement(locator).isDisplayed();
    }

    public static boolean validationSelected (By locator){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return ( driver).findElement(locator).isSelected();
    }

    public static boolean multipleValidation (By locator1, By locator2, String message){
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
        return (( driver).findElement(locator1).getText() + ( driver).findElement(locator2).getText()).equalsIgnoreCase(message);
    }

    public static void softAssert (By locator, String expected){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        softAssert.assertEquals(( driver).findElement(locator).getText(), expected);
    }
}
