package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class FirefoxDriverSettings implements ISettings{

    @Override
    public AbstractDriverOptions settings(String... userArgs) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(userArgs);

        return firefoxOptions;
    }
}