package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class AbsCommon {

    protected WebDriver driver;
    protected Actions actions;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> $$(By locator) {
       return driver.findElements(locator);
    }
}
