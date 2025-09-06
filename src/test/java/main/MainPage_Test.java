package main;

import org.junit.jupiter.api.Test;
import pages.MainPage;

public class MainPage_Test extends AbsBaseTestSuite {

    @Test
    public void cheKHeaderByMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }
}
