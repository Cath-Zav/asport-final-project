package by.asport.ui.pages.cartpage;

import by.asport.webdriver.WebDriver;

public class CartPage {
    private static final String CART_PRODUCT_TABLE_TITLE = "//div[@class='ok-table-el -size-big']//span[@class='ok-table__title']";

    public CartPage() {
    }

    public String getCurrentURL() {
        return WebDriver.getDriver().getCurrentUrl();
    }

    public String getCartProductTableTitle() {
        return WebDriver.getTextFromElement(CART_PRODUCT_TABLE_TITLE);
    }

}
