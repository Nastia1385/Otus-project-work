import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtusTests {
    WebDriver driver;
    String userName;
    String email;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        userName = RandomStringUtils.random(10, true, true);
        email = userName + "@gmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void authorizationWithCorrectData() {
        driver.manage().window().maximize();
        driver.get("https://wishlist.otus.kartushin.su/wishlists");

        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys("Sofia");
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys("Sofa12");
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement h2 = driver.findElement(By.xpath("//h2[contains(text(),'Мои списки желаний')]"));
        String resultMessage = h2.getText();
        String expectedMassage = "Мои списки желаний";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void authorizationWithAnIncorrectPassword() {
        driver.manage().window().maximize();
        driver.get("https://wishlist.otus.kartushin.su/wishlists");

        WebElement inputRequiredType = driver.findElement(By.cssSelector("input[type='text'"));
        inputRequiredType.sendKeys("Sofia");
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys("Sofa1");
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement div = driver.findElement(By.cssSelector("div[role='alert']"));
        String resultMessage = div.getText();
        String expectedMassage = "Неверное имя пользователя или пароль";
        assertEquals(expectedMassage, resultMessage);
    }

    @Test
    public void createWishList() {
        driver.manage().window().fullscreen();
        driver.get("https://wishlist.otus.kartushin.su/wishlists");

        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys("Sofia");
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys("Sofa12");
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
        driver.manage().window().maximize();
        driver.get("https://wishlist.otus.kartushin.su/wishlists");

        WebElement inputRequiredType = driver.findElement(By.className("form-control"));
        inputRequiredType.sendKeys("Sofia");
        WebElement inputPassword = driver.findElement(By.cssSelector("input[type='password'"));
        inputPassword.sendKeys("Sofa12");
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

    //@Test
    public void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(generatedString);
    }

    //@Test
    public void Test3() {
        driver.manage().window().fullscreen();
        driver.get("https://otus.home.kartushin.su/training.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement input = driver.findElement(By.id("name"));
        input.sendKeys("Анастасия");
        WebElement inputType = driver.findElement(By.id("email"));
        inputType.sendKeys("12031985@TTT.gmail.com");
        WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
        button.click();
        WebElement div = driver.findElement(By.id("messageBox"));
        String rgbaColor = div.getCssValue("background-color");
        String resultMessage = div.getText();
        String expectedMessage = "Форма отправлена с именем: Анастасия и email: 12031985@TTT.gmail.com";
        assertEquals("rgba(234, 248, 230, 1)", rgbaColor);
        assertEquals(expectedMessage, resultMessage);

        //driver.switchTo().alert().sendKeys("green");
        //String expected = "Форма отправлена с именем: Анастасия и email: 12031985@TTT.gmail.com";
        //String value = input.getAttribute("Форма отправлена с именем: Анастасия и email: 12031985@TTT.gmail.com");
        //assertEquals(expected, value);
    }
}


