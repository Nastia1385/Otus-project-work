package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {
    WebDriver driver;

    public WishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final By btnWishList = By.xpath("//button[contains(text(),'Создать новый список')]");
    private final By nameField = By.xpath("//input[@class='form-control']");
    private final By btnCreate = By.xpath("//button[@type='submit']");
    private final By wishNameField = By.xpath("//div[@class='card-title h5']");
    private final By btnViewing = By.xpath("//button[contains(text(),'Просмотр')]");
    private final By wishCard = By.xpath("//h2[contains(text(),'Сдать экзамен')]");

    public void clickBtnWishList() {
        driver.findElement(btnWishList).click();
    }

    public void inputWishName(String wishName) {
        driver.findElement(nameField).sendKeys(wishName);
    }

    public void clickBtnCreate() {
        driver.findElement(btnCreate).click();
    }

    public String nameWish() {
        return driver.findElement(wishNameField).getText();
    }

    public void clickBtnViewing() {
        driver.findElement(btnViewing).click();
    }

    public String nameWishCard() {
        return driver.findElement(wishCard).getText();
    }
}
