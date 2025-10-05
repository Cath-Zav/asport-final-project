package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchServiceTest extends BaseLogger {

    @Test
    @Tag("ci-skip")
    @DisplayName("Valid search - all results contains \"рюкзак\"")
    void test1() {
        String search = "рюкзак";
        SearchService searchService = new SearchService();
        searchService.searchRequest(search);

        List<String> titles = searchService.getProductTitles();
        logger.info("Статус код: {}. Всего результатов: {}", searchService.getResponseStatusCode(), titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertAll(
                () -> assertEquals(200, searchService.getResponseStatusCode()),
                () -> assertTrue(
                        titles.stream().allMatch(title -> title
                                .contains(search)))
        );
    }

    @Test
    @Tag("ci-skip")
    @DisplayName("Valid search - all results contains more detailed item description \"Велоперчатки Favorit 8510BK-S (обхват руки: 18-20 см)\"")
    void test2() {
        String searchService = "Велоперчатки Favorit 8510BK-S (обхват руки: 18-20 см)";
        SearchService service = new SearchService();
        service.searchRequest(searchService);

        logger.info("Статус код: {}. Результат: {}", service.getResponseStatusCode(), service.getProductTitle());

        assertAll(
                () -> assertEquals(200, service.getResponseStatusCode()),
                () -> assertEquals("Велоперчатки Favorit 8510BK-S (обхват руки: 18-20 см)", service.getProductTitle())
        );
    }

    @Tag("ci-skip")
    @DisplayName("Status code is 200 when searching invalid product items")
    @ParameterizedTest
    @ValueSource(strings = {"123@#$%^&*", ""})
    void test3(String search) {
        SearchService searchService = new SearchService();
        searchService.searchRequest(search);

        List<String> titles = searchService.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertEquals(200, searchService.getResponseStatusCode());
    }

    @DisplayName("Status code is 200 when sending null")
    @ParameterizedTest
    @Tag("ci-skip")
    @ValueSource(strings = {"rdfghjj", "@"})
    void test4(String searchKey) {
        SearchService searchService = new SearchService();
        searchService.searchRequest(searchKey);

        List<String> titles = searchService.getProductTitles();
        logger.info("Checking invalid search request");

        assertAll(
                () -> assertEquals(200, searchService.getResponseStatusCode()),
                () ->assertEquals("Не найдено ни одного товара по запросу", searchService.getNotFoundMessage()));
    }
}
