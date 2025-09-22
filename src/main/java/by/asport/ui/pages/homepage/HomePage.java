package by.asport.ui.pages.homepage;

import by.asport.webdriver.WebDriver;

public class HomePage {
    private final String URL = "https://asport.by/";
    private final String LINK_PERSONAL_CABINET = "//div[@class='ok-auth']//span[@ class='ok-auth__info']";

    public HomePage() {
    }

    public HomePage openSite() {
        WebDriver.getDriver().get(URL);
        return this;
    }

    public void clickLogin() {
        WebDriver.clickElement(LINK_PERSONAL_CABINET);
    }
}
