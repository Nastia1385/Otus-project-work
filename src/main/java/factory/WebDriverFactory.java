package factory;

import exceptions.BrowserNotSupportedExceptions;
import factory.settings.ChromeDriverSettings;
import factory.settings.FirefoxDriverSettings;
import factory.settings.ISettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private final String browser = System.getProperty("browser").toLowerCase().trim();

    public WebDriver create () {
        switch (browser) {
            case "chrome": {
                ISettings settings = new ChromeDriverSettings();
                return new ChromeDriver((ChromeOptions) settings.settings(null));
            }
            case "firefox": {
                ISettings settings = new FirefoxDriverSettings();
                return new FirefoxDriver((FirefoxOptions) settings.settings(null));
            }
        }
        throw new BrowserNotSupportedExceptions(browser);
    }




}
