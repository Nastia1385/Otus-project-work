package main;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log = LogManager.getLogger(OtusTests.class);

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
        log.info("Старт теста на успешную авторизацию");
        lp.auth(login, password);
        String resultMessage = driver.findElement(By.xpath("//h2[contains(text(),'Мои списки желаний')]")).getText();
        String expectedMassage = "Мои списки желаний";
        assertEquals(expectedMassage, resultMessage);
        log.info("Тест на успешную авторизацию завершен");
    }

    /**
     * Негативный тест на авторизацию с использованием генерации случайного пароля
     */
    @Test
    public void authorizationWithAnIncorrectPassword() {
        log.info("Старт теста на не успешную авторизацию");
        String password = RandomStringUtils.random(5, true, true);
        lp.auth(login, password);
        String resultMessage = lp.authErrorText();
        String expectedMassage = "Неверное имя пользователя или пароль";
        assertEquals(expectedMassage, resultMessage);
        log.info("Тест на не успешную авторизацию завершен");
    }

    /**
     * Тест проверят корректность отображения пользователя
     */
    @Test
    public void userSearch() {
        log.info("Старт теста на поиск пользователя");
        lp.auth(login, password);
        driver.findElement(By.cssSelector(".nav-link[href='/users']")).click();
        WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Sofia')]"));
        assertTrue(element.isDisplayed());
        log.info("Тест на поиск пользователя завершен");
    }

    /**
     * Тест проверяет корректность создания списка желаний
     */
    @Test
    public void createWishList() {
        log.info("Стар теста на создание нового списка желаний");
        lp.auth(login, password);
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.clickBtnWishList();
        String name = "Сдать экзамен";
        wishListPage.inputWishName(name);
        wishListPage.clickBtnCreate();
        String resultMessage = wishListPage.nameWish();
        assertEquals(name, resultMessage);
        log.info("Тест на создание нового списка желаний завершен");
    }

    /**
     * Тест проверяет корректность отображения карточки списка желаний
     */
    @Test
    public void viewingWish() throws InterruptedException {
        log.info("Старт теста на просмотр списка желаний");
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
        log.info("Тест на просмотр списка желаний завершен");
    }

    /**
     * Тест проверяет корректность удаления списка желаний
     */
    @Test
    public void deleteWish() throws InterruptedException {
        log.info("Стар теста удаления карточки");
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
            log.error("Карточка не удалена");
            assertFalse(divCard.isDisplayed(), "Элемент не удалён!");
        } catch (StaleElementReferenceException e) {
            log.info("Карточка успешно удалена");
        } finally {
            log.info("Тест удаления карточки завершен");
        }
    }

    /**
     * Тест проверят корректность выхода из системы
     */
    @Test
    public void exitUser() {
        log.info("Старт теста на выход из системы");
        lp.auth(login, password);
        driver.findElement(By.cssSelector(".nav-link[tabindex='0']")).click();
        String resultMessage = driver.findElement(By.xpath("//h2[contains(text(),'Вход в систему')]")).getText();
        String expectedMassage = "Вход в систему";
        assertEquals(expectedMassage, resultMessage);
        log.info("Тест на выход из системы завершен");
    }
}


