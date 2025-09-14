package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "form-control")
    private WebElement loginField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement authButton;

    private final By errorMessage = By.cssSelector("div[role='alert']");

    public String authErrorText() {
        return driver.findElement(errorMessage).getText();
    }

    public void auth(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        authButton.click();
    }
}
