package factory;

import exceptions.BrowserNotSupportedExceptions;
import factory.settings.ChromeDriverSettings;
import factory.settings.FirefoxDriverSettings;
import factory.settings.ISettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

   // private final String browser = System.getProperty("browser").toLowerCase().trim();
    private final BrowserType browser;

    public WebDriverFactory(BrowserType browser) {
        this.browser = browser;
    }

    public WebDriver create (String... params) {
        return switch (browser) {
            case CHROME -> {
                ISettings settings = new ChromeDriverSettings();
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver((ChromeOptions) settings.settings(params));
            }
            case FIREFOX -> {
                ISettings settings = new FirefoxDriverSettings();
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver((FirefoxOptions) settings.settings(params));
            }
            default -> throw new BrowserNotSupportedExceptions("Неизвестный тип браузера " + browser);
        };
    }
}
