package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.homepage.HomePage;
import by.asport.ui.pages.loginpage.LoginPage;
import by.asport.utils.LoginUtils;
import by.asport.webdriver.WebDriver;
import org.junit.jupiter.api.*;

public class LoginPageTest extends BaseLogger {
    @BeforeEach
    public void openLoginForm() {
        HomePage homePage = new HomePage();
        homePage.openSite()
                .clickLogin();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }

    @Test
    @DisplayName("Checking opening LoginDropdown")
    public void test1() {
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals("Вход", loginPage.getLoginFormTitle());
        logger.info("Checking UI: opening LoginDropdown");
    }

    @Test
    @DisplayName("Checking UI: login with valid email and password")
    public void test2() {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(loginPage.getValidEmail(), loginPage.getValidPassword());
        WebDriver.pauseSeconds(5);
        Assertions.assertEquals(loginPage.getValidEmail(), loginPage.getLoginFormTitle());
        logger.info("Checking UI: login with valid email and password");
    }

    @Test
    @DisplayName("Checking login with incorrect email")
    public void test3() {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(LoginUtils.getRandomEmail(), LoginUtils.getRandomPassword());
        Assertions.assertTrue(loginPage.isInvalidField(loginPage.getEmailError()));
        logger.info("Checking UI: login with incorrect email");
    }

    @Test
    @DisplayName("Checking login with Correct email and Incorrect password")
    public void test4() {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(loginPage.getValidEmail(), LoginUtils.getRandomPassword());
        Assertions.assertTrue(loginPage.isInvalidField(loginPage.getPasswordError()));
        logger.info("Checking UI: login with Correct email and Incorrect password");
    }

    @Test
    @DisplayName("Checking Restore button")
    public void test5() {
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals("ВОCСТАНОВИТЬ", loginPage.getRestoreButtonText());
        logger.info("Checking UI: login - Restore button");
    }
}
