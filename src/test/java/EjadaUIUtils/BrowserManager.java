package EjadaUIUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

    OptionsManager optionsManager = new OptionsManager();
    public static WebDriver driver;

    public void startBrowser(){
        String browserName = "chrome";
        if (browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver(optionsManager.getChromeOptions());

        } else if (browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver(optionsManager.getfireFoxOptions());
        }
        System.out.println(browserName + "Browser Started");
    }

    public void quiteBrowser(){
        driver.quit();
    }

}
