package main;

import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseTestSuite {

    protected WebDriver driver;

    @BeforeEach
    public void init () {
        this.driver = new WebDriverFactory().create();

    }

    @AfterEach
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }

}
