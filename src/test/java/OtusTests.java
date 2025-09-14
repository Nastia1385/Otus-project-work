import main.AbsBaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.WishListPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OtusTests extends AbsBaseTest {

    private final static String login = "Sofia";
    private final static String password = "Sofa12";
    LoginPage lp;

    @BeforeEach
    public void startDriver() {
        driver.get("https://wishlist.otus.kartushin.su/wishlists");
        lp = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    @Test
    public void authorizationWithCorrectData() {
        lp.auth(login, password);
        WebElement h2 = driver.findElement(By.xpath("//h2[contains(text(),'Мои списки желаний')]"));
        String resultMessage = h2.getText();
        String expectedMassage = "Мои списки желаний";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void authorizationWithAnIncorrectPassword() {
        String password = RandomStringUtils.random(5, true, true);
        lp.auth(login, password);
        String resultMessage = lp.authErrorText();
        String expectedMassage = "Неверное имя пользователя или пароль";
        assertEquals(expectedMassage, resultMessage);
    }

    //TODO доделать поиск
    @Test
    public void userSearch() {
        lp.auth(login, password);
        WebElement btnUsers = driver.findElement(By.cssSelector(".nav-link[href='/users']"));
        btnUsers.click();
        WebElement listUsers = driver.findElement(By.cssSelector(".g-4"));
    }

    @Test
    public void createWishList() {
        lp.auth(login, password);
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.clickBtnWishList();
        String name = "Сдать экзамен";
        wishListPage.inputWishName(name);
        wishListPage.clickBtnCreate();
        String resultMessage = wishListPage.nameWish();
        assertEquals(name, resultMessage);
    }

    @Test
    public void ViewingWish() throws InterruptedException {
        lp.auth(login, password);
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.clickBtnWishList();
        String name = "Сдать экзамен";
        wishListPage.inputWishName(name);
        wishListPage.clickBtnCreate();
        driver.findElement(By.xpath("//h5[contains(text(),'Сдать экзамен')]"));
        wishListPage.clickBtnViewing();
        Thread.sleep(500);
        String resultMessage = wishListPage.nameWishCard();
        assertEquals(name, resultMessage);
    }

    @Test
    public void deleteWish() throws InterruptedException {
        lp.auth(login, password);
        WebElement buttonType = driver.findElement(By.xpath("//button[contains(text(),'Создать новый список')]"));
        buttonType.click();
        WebElement input = driver.findElement(By.xpath("//input[@class='form-control']"));
        input.sendKeys("Купить диван");
        WebElement btnCreate = driver.findElement(By.xpath("//button[@type='submit']"));
        btnCreate.click();
        WebElement divCard = driver.findElement(By.xpath("//div[@class='card-body']"));
        WebElement btnDelete = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btnDelete.click();
        Thread.sleep(500);
        try {
            assertFalse(divCard.isDisplayed(), "Элемент не удалён!");
        } catch (StaleElementReferenceException e) {
            System.out.println("Элемент удален");
        }
    }

    @Test
    public void exitUser() {
        lp.auth(login, password);
        WebElement btnRole = driver.findElement(By.cssSelector(".nav-link[tabindex='0']"));
        btnRole.click();
        WebElement h2 = driver.findElement(By.xpath("//h2[contains(text(),'Вход в систему')]"));
        String resultMessage = h2.getText();
        String expectedMassage = "Вход в систему";
        assertEquals(expectedMassage, resultMessage);
    }
}


