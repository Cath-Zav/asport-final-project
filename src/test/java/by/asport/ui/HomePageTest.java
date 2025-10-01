package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.homepage.HomePage;
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
    homePage.addFirstProductToCart();
    Assertions.assertTrue(homePage.isCartClickable());
    }
}
