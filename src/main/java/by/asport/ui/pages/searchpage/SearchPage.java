package by.asport.ui.pages.searchpage;

import by.asport.webdriver.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private final String INPUT_SEARCH = "//input[@class='ok-search-input -state-empty']";
    private final String BUTTON_START_SEARCH = "//button[@class='ok-search__btn']";
   // private final String TITLE_SEARCH_PAGE = "//ol[@class='breadcrumb ok-breadcrumb']//span[@itemprop='name']";
    private final String TITLE_SEARCH_RESULT = "//div[@class='product-name']//span[@itemprop='name']";

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

    public List<String> getSearchResultItemsTitleText() {
        List<WebElement> listOfSearchResultElements = WebDriver.findElements(TITLE_SEARCH_RESULT);
        List<String> listOfSearchResultTitles = new ArrayList<>();

        for(WebElement element : listOfSearchResultElements) {
            listOfSearchResultTitles.add(element.getText().toLowerCase());
        }
        return listOfSearchResultTitles;
    }
}
