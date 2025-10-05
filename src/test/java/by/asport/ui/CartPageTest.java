package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.cartpage.CartPage;
import by.asport.ui.pages.homepage.HomePage;
import by.asport.ui.pages.searchpage.SearchPage;
import by.asport.webdriver.WebDriver;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPageTest extends BaseLogger {
    HomePage homePage;

    @BeforeEach
    public void openSite() {
        homePage = new HomePage();
        homePage.openSite();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }

    @Test
    @DisplayName("The address in the address bar matches https://asport.by/shcart/.")
    public void test1() {
            homePage.addFirstProductToCart();
            homePage.clickCartButton();

            CartPage cartPage = new CartPage();
            Assertions.assertEquals("https://asport.by/shcart/", cartPage.getCurrentURL());
            logger.info("Running UI CartTest, test1: The address in the address bar matches https://asport.by/shcart/");
    }

    @Test
    @DisplayName("The title of the product table in the cart matches 'СПИСОК ТОВАРОВ'")
    public void test2() {
        homePage.addFirstProductToCart();
        homePage.clickCartButton();

        CartPage cartPage = new CartPage();
        Assertions.assertEquals("СПИСОК ТОВАРОВ", cartPage.getCartProductTableTitle());
        logger.info("Running UI CartTest, test2: The title of the product table in the cart matches 'СПИСОК ТОВАРОВ'");
    }

    @Test
    @DisplayName("Find a specific product 'Палатка туристическая 3-х местная Relmax MERAN 3', add it to cart and check it's name in the cart")
    public void test3() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysToSearch("Палатка туристическая 3-х местная Relmax MERAN 3");
        searchPage.startSearch();
        searchPage.addFirstProductToCart();
        searchPage.clickCartButton();
        CartPage cartPage = new CartPage();

        Assertions.assertEquals("Палатка туристическая 3-х местная Relmax MERAN 3 (1000 mm)", cartPage.getFirstProductTitle());
        logger.info("Find a specific product 'Палатка туристическая 3-х местная Relmax MERAN 3', add it to cart and check it's name in the cart");
    }

    @Test
    @DisplayName("Find a specific product 'Снегокат НИКА Snowpatrol СНД2/Г голубой каркас', add it to cart, increase it's number and check quantity and sum")
    public void test4() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysToSearch("Снегокат НИКА Snowpatrol СНД2/Г голубой каркас");
        searchPage.startSearch();
        searchPage.addFirstProductToCart();
        searchPage.clickCartButton();
        CartPage cartPage = new CartPage();
        cartPage.clickIncreaseProductButton();

        assertAll(
                () -> assertEquals("2", cartPage.getNumberOfProducts()),
                () -> assertEquals("386,00", cartPage.getTotalSum())
        );

        logger.info("Find a specific product 'Снегокат НИКА Snowpatrol СНД2/Г голубой каркас', add it to cart, increase it's number and check quantity and sum");
    }
}
