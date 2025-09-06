package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverSettings implements ISettings{

    @Override
    public AbstractDriverOptions settings(DesiredCapabilities desiredCapabilities, String... userArgs) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-fullscreen");
        firefoxOptions.addArguments(userArgs);
        firefoxOptions.merge(desiredCapabilities);

        return firefoxOptions;
    }
}