package by.asport.ui.pages;

import by.asport.webdriver.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private static final String INPUT_SEARCH = "//input[@class='ok-search-input -state-empty']";
    private static final String BUTTON_START_SEARCH = "//button[@class='ok-search__btn']";
    private static final String TITLE_SEARCH_RESULT = "//div[@class='product-name']//span[@itemprop='name']";
    private static final String TITLE_NOT_FOUND = "//div[@class='col-md-12 -ml-default -mr-default']/p[@class='h3']";
    private static final String BUTTON_ADD_TO_CART = "//div[@data-gtm-id='add-to-cart-listing']";
    private static final String PRODUCT_CARD = "//div[@class='ok-product__main ']";

    private final Header header = new Header();

    public Header header() { return header; }

    public SearchPage() {
    }

    @Step("Send keys to search")
    public void sendKeysToSearch(String search) {
        WebDriver.sendKeys(INPUT_SEARCH, search);
    }

    @Step("Start search")
    public void startSearch() {
        WebDriver.clickElement(BUTTON_START_SEARCH);
    }

    @Step("Get product title on the search result page")
    public String getSearchResultFirstItemTitleText() {
        return WebDriver.getTextFromElement(TITLE_SEARCH_RESULT);
    }

    @Step("Get not found title")
    public String getNotFoundTitle() {
        return WebDriver.getTextFromElement(TITLE_NOT_FOUND);
    }

    @Step("Get product title on the first result on the search result page")
    public List<String> getSearchResultItemsTitleText() {
        List<WebElement> listOfSearchResultElements = WebDriver.findElements(TITLE_SEARCH_RESULT);
        List<String> listOfSearchResultTitles = new ArrayList<>();

        for(WebElement element : listOfSearchResultElements) {
            listOfSearchResultTitles.add(element.getText().toLowerCase());
        }
        return listOfSearchResultTitles;
    }

    @Step("Add first product to the cart")
    public void addFirstProductToCart() {
        WebElement cardToHover = WebDriver.findElementByPath(PRODUCT_CARD);
        Actions action = new Actions(WebDriver.getDriver());
        action.moveToElement(cardToHover).perform();
        WebDriver.clickElement(BUTTON_ADD_TO_CART);
        WebDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
