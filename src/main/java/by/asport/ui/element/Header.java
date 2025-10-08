package by.asport.ui.element;

import by.asport.ui.pages.CartPage;
import by.asport.webdriver.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private static final String BUTTON_CART = "//button[contains(@class,'ok-shcart__btn')]";
    private static final String CART_URL = "https://asport.by/shcart/";

    public CartPage clickCartButton() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(10));

        WebElement buttonCartElement = wait.until(ExpectedConditions.elementToBeClickable(WebDriver.findElementByPath(BUTTON_CART)));

        ((org.openqa.selenium.JavascriptExecutor) WebDriver.getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", buttonCartElement);
        new org.openqa.selenium.interactions.Actions(WebDriver.getDriver())
                .moveToElement(buttonCartElement)
                .pause(Duration.ofMillis(150))
                .perform();

        try {
            buttonCartElement.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            new org.openqa.selenium.interactions.Actions(WebDriver.getDriver()).sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
            ((org.openqa.selenium.JavascriptExecutor) WebDriver.getDriver()).executeScript("arguments[0].click();", buttonCartElement);
        }
        wait.until(ExpectedConditions.urlToBe(CART_URL));
        return new CartPage();
    }
}
