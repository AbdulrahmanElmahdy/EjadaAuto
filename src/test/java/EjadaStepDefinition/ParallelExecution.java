package EjadaStepDefinition;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static EjadaHooks.EjadaHook.*;
import static EjadaLocators.EjadaLocators.*;
import static EjadaStepDefinition.UserLoginScenarios.errorMessage;
import static EjadaUIUtils.BrowserManager.*;
import static EjadaUIUtils.WebUIUtils.*;
import static EjadaValidationAndScreenshots.EjadaScreenshots.*;
import static EjadaValidationAndScreenshots.EjadaValidation.*;

public class ParallelExecution {

    @Parameters("browser")
    @BeforeTest
    public static void ejadaBeforeScenarioParallel(String browser) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--incognito");
        if (browser.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver(firefoxOptions);
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver(chromeOptions);
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
        }
    }

    @Test (priority = 1)
    public void userInsertUserNameAndPasswordParallel () throws IOException, InterruptedException {
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
    }

    @Test(priority = 2)
    public void userClickLoginValidParallel () throws InterruptedException {
        test = extent.createTest("Click Login","User click login");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElementClickable(loginButton).click();
    }

    @Test(priority = 3)
    public void loggedInSuccessfullyParallel () throws IOException, InterruptedException {
        test = extent.createTest("Click Login","User click login");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        if(isElementPresent(subMenu)){
            findElement(subMenu).click();
        }

        if (isElementPresent(subMenu))
        {
            findElement(loggedOut).isDisplayed();
            test.log(Status.PASS,"User is logged in successfully");
            ejadaPassedScreenshots();
        } else
        {
            test.log(Status.FAIL,"User is logged in failed");
            ejadaFailedScreenshots();
        }
    }

    @Test (priority = 4)
    public void userInsertInvalidUserNameAndPassword () throws IOException, InterruptedException {
        test = extent.createTest("Insert Username and Password","User insert username and password");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElement(subMenu).click();
        findElement(loggedOut).click();

        findElement(userName).sendKeys("standard_user");
        findElement(userPassword).sendKeys("secret_sau");

        if (validationNotEmpty(userName,"value") && validationNotEmpty(userPassword,"value"))
        {
            test.log(Status.PASS,"Username inserted as: " + userName.toString() + " And password insert successfully");
            ejadaPassedScreenshots();
        } else
        {
            test.log(Status.FAIL,"Username and password not inserted");
            ejadaFailedScreenshots();
        }
    }

    @Test(priority = 5)
    public void userClickLoginInvalidParallel () throws InterruptedException {
        test = extent.createTest("Click Login","User click login");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElementClickable(loginButton).click();
    }

    @Test (priority = 6)
    public void loggedInFailed () throws IOException, InterruptedException {
        test = extent.createTest("Logged In Failure","User logged in failed");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        if(isElementPresent(invalidError)){
            errorMessage = findElement(invalidError).getText();
        }

        System.out.println(errorMessage);

        if (validationContain(invalidError,invalidErrorMessage))
        {
            test.log(Status.PASS,"User is logged in failed with invalid password" + "Error: ");
            ejadaPassedScreenshots();
        } else
        {
            test.log(Status.FAIL,"User is logged in successfully with invalid data");
            ejadaFailedScreenshots();
        }
    }

    @AfterTest
    public static void ejadaAfterScenarioParallel() {
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}
