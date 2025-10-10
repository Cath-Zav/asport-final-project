package by.asport.ui.pages;

import by.asport.webdriver.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final String LOGIN_FORM_TITLE = "//div[contains(@class,'ok-auth')]//span[@data-auth-info and contains(@class,'ok-auth__info')]";
    private static final String EMAIL_FIELD = "//input[@class='form-control form-group  require_fields required data-input-check -state-empty']";
    private static final String PASSWORD_FIELD = "//input[@class='form-control  require_fields required data-input-check -state-empty']";
    private static final String VALID_EMAIL = "cathzavizion@gmail.com";
    private static final String VALID_PASSWORD = "itAcademy2025&";
    private static final String BUTTON_LOGIN = "//button[@class='ok-btn -btn-theme-action -width-full']";
    private static final String EMAIL_ERROR = "//div[@class='data-input-check has-error']";
    private static final String PASSWORD_ERROR = "//span[@class='input-group has-error']";
    private static final String BUTTON_GO_TO_RESTORE_PASSWORD = "//a[@class='ok-enter__restore-href']";
    private static final String BUTTON_RESTORE_PASSWORD = "//form[@onsubmit='restore(this); return false']//button[@class='ok-btn -btn-theme-action -width-full']";

    private static final WebDriverWait wait = new WebDriverWait(WebDriver.getDriver(), Duration.ofSeconds(8));

    public LoginPage() {
    }

    public String getValidEmail() {
        return VALID_EMAIL;
    }

    public String getValidPassword() {
        return VALID_PASSWORD;
    }

    public String getEmailError() {
        return EMAIL_ERROR;
    }

    public String getPasswordError() {
        return PASSWORD_ERROR;
    }

    public String getLoginFormTitle() {
      return WebDriver.getTextFromElement(LOGIN_FORM_TITLE);
    }

    public void sendKeyLogin(String email) {
        WebDriver.findElementByPath(EMAIL_FIELD).sendKeys(email);
    }

    public void sendKeyPassword(String password) {
        WebDriver.findElementByPath(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Fill Login form")
    public void fillLoginForm(String email, String password) {
        sendKeyLogin(email);
        sendKeyPassword(password);
        WebDriver.clickElement(BUTTON_LOGIN);
    }

    public boolean isInvalidField(String xpath) {
        return WebDriver.findElementByPath(xpath).isDisplayed();
    }

    public String getRestoreButtonText() {
        WebDriver.clickElement(BUTTON_GO_TO_RESTORE_PASSWORD);
        wait.until(ExpectedConditions.visibilityOf(WebDriver.findElementByPath(BUTTON_RESTORE_PASSWORD)));
        return WebDriver.getTextFromElement(BUTTON_RESTORE_PASSWORD);
    }
}
