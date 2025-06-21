package EjadaStepDefinition;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
import static EjadaUIUtils.WebUIUtils.*;
import static EjadaValidationAndScreenshots.EjadaScreenshots.*;
import static EjadaValidationAndScreenshots.EjadaValidation.*;

public class ParallelExecution {

    @Parameters("browser")
    @BeforeTest
    public static void ejadaBeforeScenario(String browser) {
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

    @Test
    @Given("User insert username and password")
    public void userInsertUserNameAndPassword () throws IOException {
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

    @Test
    @And("User click on login button")
    public void userClickLogin () {
        test = extent.createTest("Click Login","User click login");
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();

        findElementClickable(loginButton).click();
    }

    @Test
    @Then("User login successfully")
    public void loggedInSuccessfully () throws IOException {
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

    @AfterTest
    public static void ejadaAfterScenario() {
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}
