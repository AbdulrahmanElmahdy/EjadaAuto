package EjadaLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static EjadaHooks.EjadaHook.driver;

public class EjadaLocators {

    public static By userName = By.id("user-name");
    public static By userPassword =By.id("password");
    public static By loginButton = By.id("login-button");
    public static By subMenu = By.id("react-burger-menu-btn");
    public static By loggedOut = By.id("logout_sidebar_link");

    public static WebElement findElement (By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public static WebElement findElementClickable (By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }
}
