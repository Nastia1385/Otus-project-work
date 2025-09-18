package main;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.WishListPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс с основными тестами
 */
public class OtusTests extends AbsBaseTest {

    private final static String login = System.getProperty("login");
    private final static String password = System.getProperty("password");
    LoginPage lp;

    @BeforeEach
    public void startDriver() {
        driver.get("https://wishlist.otus.kartushin.su/wishlists");
        lp = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    /**
     * Позитивный тест на авторизацию
     */
    @Test
    public void authorizationWithCorrectData() {
        lp.auth(login, password);
        String resultMessage = driver.findElement(By.xpath("//h2[contains(text(),'Мои списки желаний')]")).getText();
        String expectedMassage = "Мои списки желаний";
        assertEquals(expectedMassage, resultMessage);
    }

    /**
     * Негативный тест на авторизацию с использованием генерации случайного пароля
     */
    @Test
    public void authorizationWithAnIncorrectPassword() {
        String password = RandomStringUtils.random(5, true, true);
        lp.auth(login, password);
        String resultMessage = lp.authErrorText();
        String expectedMassage = "Неверное имя пользователя или пароль";
        assertEquals(expectedMassage, resultMessage);
    }

    /**
     * Тест проверят корректность отображения пользователя
     */
    @Test
    public void userSearch() {
        lp.auth(login, password);
        driver.findElement(By.cssSelector(".nav-link[href='/users']")).click();
        WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Sofia')]"));
        assertTrue(element.isDisplayed());
    }

    /**
     * Тест проверяет корректность создания списка желаний
     */
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

    /**
     * Тест проверяет корректность отображения карточки списка желаний
     */
    @Test
    public void viewingWish() throws InterruptedException {
        lp.auth(login, password);
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.clickBtnWishList();
        String name = "Сдать экзамен";
        wishListPage.inputWishName(name);
        wishListPage.clickBtnCreate();
        wishListPage.clickBtnViewing();
        Thread.sleep(500);
        String resultMessage = wishListPage.nameWishCard();
        assertEquals(name, resultMessage);
    }

    /**
     * Тест проверяет корректность удаления списка желаний
     */
    @Test
    public void deleteWish() throws InterruptedException {
        lp.auth(login, password);
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.clickBtnWishList();
        String name = "Сдать экзамен";
        wishListPage.inputWishName(name);
        wishListPage.clickBtnCreate();
        WebElement divCard = driver.findElement(By.xpath("//div[@class='card-body']"));
        driver.findElement(By.xpath("//button[contains(text(),'Удалить')]")).click();
        Thread.sleep(500);
        try {
            assertFalse(divCard.isDisplayed(), "Элемент не удалён!");
        } catch (StaleElementReferenceException e) {
            System.out.println("Элемент удален");
        }
    }

    /**
     * Тест проверят корректность выхода из системы
     */
    @Test
    public void exitUser() {
        lp.auth(login, password);
        driver.findElement(By.cssSelector(".nav-link[tabindex='0']")).click();
        String resultMessage = driver.findElement(By.xpath("//h2[contains(text(),'Вход в систему')]")).getText();
        String expectedMassage = "Вход в систему";
        assertEquals(expectedMassage, resultMessage);
    }
}


