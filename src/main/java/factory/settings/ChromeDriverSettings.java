package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeDriverSettings implements ISettings{

    @Override
    public AbstractDriverOptions settings(String... userArgs) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(userArgs);

        return chromeOptions;
    }
}

