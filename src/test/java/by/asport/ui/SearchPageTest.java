package by.asport.ui;

import by.asport.logger.BaseLogger;
import by.asport.ui.pages.homepage.HomePage;
import by.asport.ui.pages.searchpage.SearchPage;
import by.asport.webdriver.WebDriver;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPageTest extends BaseLogger {
    @BeforeEach
    public void openHomePage() {
        HomePage homePage = new HomePage();
        homePage.openSite();
    }

    @AfterEach
    public void tearDown() {
        WebDriver.quit();
    }

    @Test
    @DisplayName("Checking if the list of the results contains the specific product \"Палатка туристическая 3-х местная Tramp Lite Tourist 3 Sand (V2) (4000 mm)\"")
    public void test1() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysToSearch("Палатка туристическая 3-х местная Tramp Lite Tourist 3 Sand (V2) (4000 mm)");
        searchPage.startSearch();
        WebDriver.pauseSeconds(5);

        Assertions.assertEquals("Палатка туристическая 3-х местная Tramp Lite Tourist 3 Sand (V2) (4000 mm)", searchPage.getSearchResultFirstItemTitleText());
    }

    @ParameterizedTest
    @DisplayName("Checking if the list of the results contains \"палатка\"")
    @ValueSource(strings = {"палатка", "палатка", "палатк", "палат", "пала",})
    public void test2() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysToSearch("палатка");
        searchPage.startSearch();
        WebDriver.pauseSeconds(5);

        List<String> searchResults = searchPage.getSearchResultItemsTitleText();
        System.out.println(searchResults.size());

        assertThat(searchResults)
                .as("The list of searches should not be empty")
                .isNotEmpty();

        SoftAssertions softly = new SoftAssertions();
        for (int i = 0; i < searchResults.size(); i++) {
            String title = searchResults.get(i);
            System.out.println(i + ": " + title);
            softly.assertThat(title)
                    .as("Result #%s should contain 'палатка'", i + 1)
                    .containsIgnoringCase("палатк");
        }
        softly.assertAll();
    }

    @ParameterizedTest
    @DisplayName("Checking if the list of the results never empty")
    @ValueSource(strings = {"", "па", "12", "@#$%"})
    public void test3() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysToSearch("палатка");
        searchPage.startSearch();
        WebDriver.pauseSeconds(5);

        List<String> searchResults = searchPage.getSearchResultItemsTitleText();
        System.out.println(searchResults.size());

        assertThat(searchResults)
                .as("The list of searches should not be empty")
                .isNotEmpty();
    }
}
