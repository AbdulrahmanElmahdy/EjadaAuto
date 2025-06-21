package EjadaUIUtils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OptionsManager {

    public ChromeOptions getChromeOptions(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origin=*");
        options.addArguments("--disable-notifications");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY,options);
        options.merge(cp);

        return options;
    }

    public FirefoxOptions getfireFoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origin=*");
        options.addArguments("--disable-notifications");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(FirefoxOptions.FIREFOX_OPTIONS,options);
        options.merge(cp);

        return options;
    }
}
