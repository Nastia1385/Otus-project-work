import main.AbsBaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OtusTests extends AbsBaseTest {

    private final static String login = "Sofia";
    private final static String password = "Sofa12";

    @BeforeEach
    public void startDriver() {
        driver.get("https://wishlist.otus.kartushin.su/wishlists");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    @Test
    public void authorizationWithCorrectData() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement h2 = driver.findElement(By.xpath("//h2[contains(text(),'Мои списки желаний')]"));
        String resultMessage = h2.getText();
        String expectedMassage = "Мои списки желаний";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void authorizationWithAnIncorrectPassword() {
        WebElement inputRequiredType = driver.findElement(By.cssSelector("input[type='text'"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        String password = RandomStringUtils.random(5, true, true);
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement div = driver.findElement(By.cssSelector("div[role='alert']"));
        String resultMessage = div.getText();
        String expectedMassage = "Неверное имя пользователя или пароль";
        assertEquals(expectedMassage, resultMessage);
    }

    //TODO доделать поиск
    @Test
    public void userSearch() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();

    }

    @Test
    public void createWishList() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement buttonType = driver.findElement(By.xpath("//button[contains(text(),'Создать новый список')]"));
        buttonType.click();
        WebElement input = driver.findElement(By.xpath("//input[@class='form-control']"));
        input.sendKeys("Сдать экзамен");
        WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
        btn.click();
        WebElement div = driver.findElement(By.xpath("//div[@class='card-title h5']"));
        String resultMessage = div.getText();
        String expectedMassage = "Сдать экзамен";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void ViewingWish() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement buttonType = driver.findElement(By.xpath("//button[contains(text(),'Создать новый список')]"));
        buttonType.click();
        WebElement input = driver.findElement(By.xpath("//input[@class='form-control']"));
        input.sendKeys("Сдать экзамен");
        WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
        btn.click();
        WebElement btn1 = driver.findElement(By.xpath("//button[contains(text(),'Просмотр')]"));
        btn1.click();
        WebElement div = driver.findElement(By.xpath("//h2[contains(text(),'Сдать экзамен')]"));
        String resultMessage = div.getText();
        String expectedMassage = "Сдать экзамен";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void deleteWish() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement buttonType = driver.findElement(By.xpath("//button[contains(text(),'Создать новый список')]"));
        buttonType.click();
        WebElement input = driver.findElement(By.xpath("//input[@class='form-control']"));
        input.sendKeys("Купить диван");
        WebElement btnCreate = driver.findElement(By.xpath("//button[@type='submit']"));
        btnCreate.click();
        WebElement divCard = driver.findElement(By.xpath("//div[@class='card-body']"));
        WebElement btnDelete = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btnDelete.click();
        assertFalse(divCard.isDisplayed(), "Элемент не удалён!");
    }

    @Test
    public void exitUser() {
        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys(login);
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys(password);
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement btnRole = driver.findElement(By.cssSelector(".nav-link[tabindex='0']"));
        btnRole.click();
        WebElement h2 = driver.findElement(By.xpath("//h2[contains(text(),'Вход в систему')]"));
        String resultMessage = h2.getText();
        String expectedMassage = "Вход в систему";
        assertEquals(expectedMassage, resultMessage);
    }
}


