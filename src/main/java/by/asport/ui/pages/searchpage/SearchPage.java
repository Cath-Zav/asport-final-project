package by.asport.ui.pages.searchpage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private static final String INPUT_SEARCH = "//input[@class='ok-search-input -state-empty']";
    private static final String BUTTON_START_SEARCH = "//button[@class='ok-search__btn']";
    private static final String TITLE_SEARCH_RESULT = "//div[@class='product-name']//span[@itemprop='name']";
    private static final String TITLE_NOT_FOUND = "//div[@class='col-md-12 -ml-default -mr-default']/p[@class='h3']";

    public SearchPage() {
    }

    public void sendKeysToSearch(String search) {
        WebDriver.sendKeys(INPUT_SEARCH, search);
    }

    public void startSearch() {
        WebDriver.clickElement(BUTTON_START_SEARCH);
    }

    public String getSearchResultFirstItemTitleText() {
        return WebDriver.getTextFromElement(TITLE_SEARCH_RESULT);
    }

    public String getNotFoundTitle() {
        return WebDriver.getTextFromElement(TITLE_NOT_FOUND);
    }

    public List<String> getSearchResultItemsTitleText() {
        List<WebElement> listOfSearchResultElements = WebDriver.findElements(TITLE_SEARCH_RESULT);
        List<String> listOfSearchResultTitles = new ArrayList<>();

        for(WebElement element : listOfSearchResultElements) {
            listOfSearchResultTitles.add(element.getText().toLowerCase());
        }
        return listOfSearchResultTitles;
    }
}
