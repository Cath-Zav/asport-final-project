package by.asport.ui.pages.accountpage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    public static final String ACCOUNT_BUTTON= "//div[@class='ok-auth -state-login']";
    public static final String ACCOUNT_MENU_ORDERS= "//a[@href='/orders.xhtml']/span";

    public AccountPage() {
    }

    public String getAccountDropdownTitle(){
        WebDriver.clickElement(ACCOUNT_BUTTON);
        return WebDriver.getTextFromElement(ACCOUNT_MENU_ORDERS);
    }
}
