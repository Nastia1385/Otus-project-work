package main;

import factory.BrowserType;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseTest {

    public WebDriver driver;

    @BeforeEach
    public void init () {
        this.driver = new WebDriverFactory(BrowserType.CHROME).create("--start-fullscreen");
    }

    @AfterEach
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }
}
