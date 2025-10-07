package by.asport.ui.pages;

import by.asport.webdriver.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private static final String URL = "https://asport.by/";
    private static final String LINK_PERSONAL_CABINET = "//div[@class='ok-auth']//span[@ class='ok-auth__info']";
    private static final String BUTTON_CART = "//button[@class='ok-shcart__btn ok-shcart__ico']";
    private static final String CART_BUTTON_IS_DISABLED = "//div[@class='ok-shcart-box -state-disabled']";
    private static final String CART_BUTTON_IS_ENABLED = "//div[@class='ok-shcart-box']";
    private static final String PRODUCT_CARD = "//div[@class='ok-product ok-product--grid  ']";
    private static final String BUTTON_ADD_TO_CARD = "//div[@data-gtm-id='add-to-cart-listing']";
    private static final String CART_URL = "https://asport.by/shcart/";

    private final Header header = new Header();

    public Header header() { return header; }

    public HomePage() {
    }

    @Step("Home page opened")
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
}
