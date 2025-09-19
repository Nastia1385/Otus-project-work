package main;

import factory.BrowserType;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public abstract class AbsBaseTest {

    private final String browser = System.getProperty("browser").toUpperCase(Locale.ROOT).trim();
    public WebDriver driver;

    @BeforeEach
    public void init () {
        BrowserType browserType = BrowserType.valueOf(browser);
        this.driver = new WebDriverFactory(browserType).create("--start-fullscreen");
    }

    @AfterEach
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }
}
