package by.asport.ui.pages.homepage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private static final String URL = "https://asport.by/";
    private static final String LINK_PERSONAL_CABINET = "//div[@class='ok-auth']//span[@ class='ok-auth__info']";
    private static final String BUTTON_CART = "//button[@class='ok-shcart__btn ok-shcart__ico']";
    private static final String CART_BUTTON_IS_DISABLED = "//div[@class='ok-shcart-box -state-disabled']";
    private static final String CART_BUTTON_IS_ENABLED = "//div[@class='ok-shcart-box']";
    private static final String PRODUCT_CARD = "//div[@class='ok-product ok-product--grid  ']";
    private static final String BUTTON_ADD_TO_CARD = "//div[@data-gtm-id='add-to-cart-listing']";
    private static final String CART_URL = "https://asport.by/shcart/";

    public HomePage() {
    }

    public HomePage openSite() {
        WebDriver.getDriver().get(URL);
        return this;
    }

    public void clickLogin() {
        WebDriver.clickElement(LINK_PERSONAL_CABINET);
    }

    public boolean isCartNotClickable() {
        return WebDriver.findElementByPath(CART_BUTTON_IS_DISABLED).isDisplayed();
    }

    public void addFirstProductToCart() {
        WebElement cardToHover = WebDriver.findElementByPath(PRODUCT_CARD);
        Actions action = new Actions(WebDriver.getDriver());
        action.moveToElement(cardToHover).perform();
        WebDriver.findElementByPath(BUTTON_ADD_TO_CARD).click();
    }

    public boolean isCartClickable() {
        return WebDriver.findElementByPath(CART_BUTTON_IS_ENABLED).isDisplayed();
    }

    public void clickCartButton() {
        var d = WebDriver.getDriver();
        var wait = new WebDriverWait(d, Duration.ofSeconds(10));

        // 1) дождаться, что бокс корзины не disabled
        By CART_BOX = By.cssSelector(".ok-shcart-box");
        wait.until(driver -> {
            WebElement box = driver.findElement(CART_BOX);
            String cls = box.getAttribute("class");
            return cls != null && !cls.contains("-state-disabled");
        });

        // 2) цель клика: и <a>, и <button> (берём то, что есть на странице)
        By CART_TARGET = By.xpath(
                "//a[@href='/shcart/'] | //button[@data-url='/shcart/'] | //button[contains(@class,'ok-shcart__btn')]"
        );

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CART_TARGET));

        // 3) скролл/ховер, чтобы убрать перехваты из-за позиционирования
        ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", btn);
        new org.openqa.selenium.interactions.Actions(d)
                .moveToElement(btn)
                .pause(Duration.ofMillis(150))
                .perform();

        // 4) клик + fallback, если что-то перехватило
        try {
            btn.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // нередко мешает cookie-баннер/модалка — пробуем ESC и JS-клик
            new org.openqa.selenium.interactions.Actions(d).sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
            ((org.openqa.selenium.JavascriptExecutor) d).executeScript("arguments[0].click();", btn);
        }

        // 5) подтверждение перехода
        wait.until(ExpectedConditions.urlContains("/shcart"));
//        WebElement buttonCard = WebDriver.findElementByPath(BUTTON_CART);
//        Actions action = new Actions(WebDriver.getDriver());
//        action.moveToElement(buttonCard).perform();
//        WebDriver.clickElement(BUTTON_CART);
    }

    public void waitUntilOnCartPage() {
        WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(CART_URL));
    }
}
