package main;

import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPageTest extends AbsBaseTest {

    @Test
    public void cheKHeaderByMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }
}
