package EjadaStepDefinition;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Set;

import static EjadaHooks.EjadaHook.*;
import static EjadaLocators.EjadaLocators.*;
import static EjadaUIUtils.BrowserManager.*;
import static EjadaUIUtils.WebUIUtils.*;
import static EjadaValidationAndScreenshots.EjadaScreenshots.ejadaFailedScreenshots;
import static EjadaValidationAndScreenshots.EjadaScreenshots.ejadaPassedScreenshots;
import static EjadaValidationAndScreenshots.EjadaValidation.*;

public class ProductOrder {

    public static String chosenProduct;
    public static String parentWindowHandle = driver.getWindowHandle();

    @Given("User insert username and password and click login button")
    public void userInsertUserNameAndPassword () throws IOException, InterruptedException {
        test = extent.createTest("Insert Username and Password","User insert username and password");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElement(userName).sendKeys("standard_user");
        findElement(userPassword).sendKeys("secret_sauce");

        if (validationNotEmpty(userName,"value") && validationNotEmpty(userPassword,"value"))
        {
            test.log(Status.PASS,"Username inserted as: " + userName.toString() + " And password insert successfully");
            ejadaPassedScreenshots();
        } else
        {
            test.log(Status.FAIL,"Username and password not inserted");
            ejadaFailedScreenshots();
        }
        findElementClickable(loginButton).click();
        Thread.sleep(2000);
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Then("Product page is displayed and user clicking on add to cart")
    public void productPageAddToCart () throws IOException {
        test = extent.createTest("Add To Cart","User add product to cart");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        if (validationContain(productsPage,"Product"))
        {
            test.log(Status.PASS,"Logged in successfully");
            ejadaPassedScreenshots();
        } else
        {
            test.log(Status.FAIL,"Logged in failed");
            ejadaFailedScreenshots();
        }

        chosenProduct = findElement(product).getText();
        findElementClickable(addToCart).click();
    }

    @When("Product add to cart and displayed in cart page")
    public void productInCart () throws IOException {
        test = extent.createTest("Product in cart","User saw the product in cart");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        if (isElementPresent(cartIcon) && validationContain(cartIcon,"1"))
        {
            test.log(Status.PASS,"Product inserted to cart");
            findElement(cartIcon).click();
            ejadaPassedScreenshots();
            if (findElement(selectedProduct).getText().equalsIgnoreCase(chosenProduct))
            {
                test.log(Status.PASS,"Product in cart as selected");
                ejadaPassedScreenshots();
            }else {
                test.log(Status.WARNING,"Product in cart not same as selected product");
                ejadaFailedScreenshots();
            }
        } else
        {
            test.log(Status.FAIL,"Logged in failed");
            ejadaFailedScreenshots();
        }
    }

    @And("User proceed to checkout and insert personal details after that will complete the order")
    public void userProceedToCheckoutAndInsertPersonalDetailsAfterThatWillCompleteTheOrder() throws IOException {
        test = extent.createTest("Product in cart","User saw the product in cart");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElement(checkoutOrder).click();
        findElement(checkoutFirstName).sendKeys("Abdulrahman");
        findElement(checkoutLastName).sendKeys("Mahdy");
        findElement(checkoutZipCode).sendKeys("WA14 2PU");
        ejadaPassedScreenshots();

        Actions build = new Actions(driver);
        build.moveToElement(findElement(continueButton)).build().perform();
        WebElement m2m= driver.findElement(continueButton);
        m2m.click();

    }
    @Then("User should see the order confirmation")
    public void userShouldSeeTheOrderConfirmation() throws IOException {
        test = extent.createTest("Product in cart","User saw the product in cart");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        scrollBy(0,1000);

        Actions build = new Actions(driver);
        build.moveToElement(findElement(checkoutFinish)).build().perform();
        WebElement m2m= driver.findElement(checkoutFinish);
        m2m.click();

        if (validation(orderHeadSuccessMessage,"Thank you for your order!") && validationContain(checkoutCompleteTitle,"Checkout: Complete!")){
            test.log(Status.PASS,"Order successfully done");
            ejadaPassedScreenshots();
        } else {
            test.log(Status.FAIL,"Order failed");
            ejadaFailedScreenshots();
        }
    }

    @And("User should see the success message")
    public void userShouldSeeTheMessage() throws IOException {
        test = extent.createTest("Product in cart","User saw the product in cart");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        if (validation(orderSuccessTextMessage,"Your order has been dispatched, and will arrive just as fast as the pony can get there!")){
            test.log(Status.PASS,"Order successfully done");
            ejadaPassedScreenshots();
        } else {
            test.log(Status.FAIL,"Order failed");
            ejadaFailedScreenshots();
        }
    }
}
