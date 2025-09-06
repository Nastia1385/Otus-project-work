package exceptions;

public class BrowserNotSupportedExceptions extends RuntimeException{
    public BrowserNotSupportedExceptions(String browserName) {
        super(String.format("Browser %s not supported", browserName));
    }

}
