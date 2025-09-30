package by.asport.ui.pages.homepage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private static final String URL = "https://asport.by/";
    private static final String LINK_PERSONAL_CABINET = "//div[@class='ok-auth']//span[@ class='ok-auth__info']";
    private static final String BUTTON_CART = "//button[@class='ok-shcart__btn ok-shcart__ico']";
    private static final String CART_BUTTON_IS_DISABLED = "//div[@class='ok-shcart-box -state-disabled']";
    private static final String CART_BUTTON_IS_ENABLED = "//div[@class='ok-shcart-box']";
    private static final String PRODUCT_CARD = "//div[@class='ok-product__main ']";
    private static final String BUTTON_ADD_TO_CARD = "//div[@data-gtm-id='add-to-cart-listing']";

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

    public void addProductToCart() {
        WebElement cardToHover = WebDriver.findElementByPath(PRODUCT_CARD);
        Actions action = new Actions(WebDriver.getDriver());
        action.moveToElement(cardToHover).perform();
        WebDriver.findElementByPath(BUTTON_ADD_TO_CARD).click();
    }

    public boolean isCartClickable() {
        return WebDriver.findElementByPath(CART_BUTTON_IS_ENABLED).isDisplayed();
    }
}
