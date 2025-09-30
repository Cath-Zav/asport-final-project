package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.homepage.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartPageTest extends BaseLogger {
    @BeforeEach
    public void openSite() {
        HomePage homePage = new HomePage();
        homePage.openSite();
    }

    @Test
    @DisplayName("Сart icon is displayed")
    public void test1() {

    }
}
