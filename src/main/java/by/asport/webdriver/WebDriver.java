package by.asport.webdriver;

import by.asport.logger.BaseLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class    WebDriver extends BaseLogger {
    private static org.openqa.selenium.WebDriver driver;

    public static org.openqa.selenium.WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    public static WebElement findElementByPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static void clickElement(String xpath) {
        findElementByPath(xpath).click();
    }

    public static String getTextFromElement(String xpath) {
        return findElementByPath(xpath).getText();
    }

    public static void sendKeys(String xpath, String value) {
        findElementByPath(xpath).sendKeys(value);
    }

    public static List<WebElement> findElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public static void pauseSeconds(long seconds) {
        try {
            Thread.sleep(java.time.Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("Pause was interrupted");
        }
    }
}
