package by.asport.ui.pages.cartpage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private static final String CART_PRODUCT_TABLE_TITLE = "//div[@class='ok-table-el -size-big']//span[@class='ok-table__title']";
    private static final String BUTTON_INCREASE_PRODUCT_NUMBER = "//div[@class='ok-input-number__more']";
    private static final String NUMBER_OF_PRODUCTS = "//input[@class='form-control']";
    private static final String TOTAL_SUM = "//span[@class='ok-order__sum-val' and @data-cart-sum-2]";
    private static final String FIRST_PRODUCT_TITLE = "//div[@class='ok-order__text']//a";

    public CartPage() {
    }

    public String getCurrentURL() {
        return WebDriver.getDriver().getCurrentUrl();
    }

    public String getCartProductTableTitle() {
        return WebDriver.getTextFromElement(CART_PRODUCT_TABLE_TITLE);
    }

    public String getFirstProductTitle() {
        return WebDriver.getTextFromElement(FIRST_PRODUCT_TITLE);
    }

    public void clickIncreaseProductButton() {
        WebDriver.clickElement(BUTTON_INCREASE_PRODUCT_NUMBER);
    }

    public void clickIncreaseProductButton(int productOrder) {
        List<WebElement> plusButtons = WebDriver.getDriver().findElements(By.xpath(BUTTON_INCREASE_PRODUCT_NUMBER));
        plusButtons.get(productOrder).click();
    }

    public String getNumberOfProducts() {
            return WebDriver.findElementByPath(NUMBER_OF_PRODUCTS).getDomProperty("value"); // или getAttribute("value")
    }

//    public String getNumberOfProducts(int productOrder) {
//        List<WebElement> numberOfProducts = WebDriver.getDriver().findElements(By.xpath(NUMBER_OF_PRODUCTS));
//        return WebDriver.getTextFromElement(numberOfProducts.get(productOrder).getText());
//    }

    public String getTotalSum() {
        return WebDriver.getTextFromElement(TOTAL_SUM);
    }
}
