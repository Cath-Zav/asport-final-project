package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.HomePage;
import by.asport.webdriver.WebDriver;
import org.junit.jupiter.api.*;

public class HomePageTest extends BaseLogger {
    @BeforeEach
    public void openSite() {
        HomePage homePage = new HomePage();
        homePage.openSite();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }

    @Test
    @DisplayName("Cart is inactive before adding product in it")
    public void test1() {
        HomePage homePage = new HomePage();
        Assertions.assertTrue(homePage.isCartNotClickable());
    }

    @Test
    @DisplayName("Cart is clickable when not empty")
    public void test2() {
    HomePage homePage = new HomePage();
    homePage.addFirstFoundProductToCart();
    Assertions.assertTrue(homePage.isCartClickable());
    }

    @Test
    @DisplayName("Add product to cart, then delete, cart is not clickable")
    public void test3() {
        HomePage homePage = new HomePage();
        homePage.addFirstFoundProductToCart();
        homePage.header().removeProductFromCart();
        Assertions.assertTrue(homePage.isCartClickable());
    }
}
