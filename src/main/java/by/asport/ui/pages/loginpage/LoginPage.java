package by.asport.ui.pages.loginpage;

import by.asport.webdriver.WebDriver;

public class LoginPage {
    private String LOGIN_FORM_TITLE = "//div[contains(@class,'ok-auth')]//span[@data-auth-info and contains(@class,'ok-auth__info')]";
    private String EMAIL_FIELD = "//input[@class='form-control form-group  require_fields required data-input-check -state-empty']";
    private String PASSWORD_FIELD = "//input[@class='form-control  require_fields required data-input-check -state-empty']";
    private final String VALID_EMAIL = "cathzavizion@gmail.com";
    private final String VALID_PASSWORD = "itAcademy2025&";
    private String BUTTON_LOGIN = "//button[@class='ok-btn -btn-theme-action -width-full']";
    private String EMAIL_ERROR = "//div[@class='data-input-check has-error']";
    private String PASSWORD_ERROR = "//span[@class='input-group has-error']";
    private String BUTTON_GO_TO_RESTORE_PASSWORD = "//a[@class='ok-enter__restore-href']";
    private String BUTTON_RESTORE_PASSWORD = "//form[@onsubmit='restore(this); return false']//button[@class='ok-btn -btn-theme-action -width-full']";

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

    public void fillLoginForm(String email, String password) {
        sendKeyLogin(email);
        sendKeyPassword(password);
        WebDriver.clickElement(BUTTON_LOGIN);
        WebDriver.pauseSeconds(5);
    }

    public boolean isInvalidField(String xpath) {
        return WebDriver.findElementByPath(xpath).isDisplayed();
    }

    public String getRestoreButtonText() {
        WebDriver.clickElement(BUTTON_GO_TO_RESTORE_PASSWORD);
        WebDriver.pauseSeconds(5);
        return WebDriver.getTextFromElement(BUTTON_RESTORE_PASSWORD);
    }
}
