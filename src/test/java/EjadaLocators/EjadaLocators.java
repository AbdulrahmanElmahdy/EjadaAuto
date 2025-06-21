package EjadaLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static EjadaUIUtils.BrowserManager.*;

public class EjadaLocators {

    public static By userName = By.id("user-name");
    public static By userPassword =By.id("password");
    public static By loginButton = By.id("login-button");
    public static By subMenu = By.id("react-burger-menu-btn");
    public static By loggedOut = By.id("logout_sidebar_link");
    public static By invalidError = By.xpath("//*[@data-test=\"error\"]");
    public static By productsPage = By.xpath("//*[text()=\"Products\"]");
    public static By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    public static By cartIcon = By.xpath("//*[@data-test=\"shopping-cart-badge\"]");
    public static By product = By.xpath("//*[text()=\"Sauce Labs Backpack\"]");
    public static By selectedProduct = By.xpath("//*[@data-test=\"inventory-item-name\"]");
    public static By checkoutOrder = By.id("checkout");
    public static By checkoutFirstName = By.id("first-name");
    public static By checkoutLastName = By.id("last-name");
    public static By checkoutZipCode = By.id("postal-code");
    public static By continueButton = By.xpath("//*[@class=\"checkout_buttons\"]/input");
    public static By checkoutFinish = By.id("finish");
    public static By orderHeadSuccessMessage = By.xpath("//*[@data-test=\"complete-header\"]");
    // Thank you for your order!
    public static By orderSuccessTextMessage = By.xpath("//*[@data-test=\"complete-text\"]");
    // Your order has been dispatched, and will arrive just as fast as the pony can get there!
    public static By checkoutCompleteTitle = By.xpath("//*[text()=\"Checkout: Complete!\"]");
    //Checkout: Complete!

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
