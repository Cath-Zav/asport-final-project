package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchServiceTest extends BaseLogger {

    @Test
    @DisplayName("Valid search - results contains \"рюкзак\"")
    void test1() {
        String search = "рюкзак";
        SearchService searchService = new SearchService();
        searchService.search(search);

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
    @DisplayName("Valid search - results contains \"123\"")
    void test2() {
        String searchService = "123";
        SearchService service = new SearchService();
        service.search(searchService);

        List<String> titles = service.getProductTitles();
        logger.info("Статус код: {}. Всего результатов: {}", service.getResponseStatusCode(), titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertAll(
                () -> assertEquals(200, service.getResponseStatusCode()),
                () -> assertTrue(
                        titles.stream().allMatch(title -> title
                                .contains(searchService)))
        );
    }

    @DisplayName("Status code is 200 when searching invalid product items")
    @ParameterizedTest
    @ValueSource(strings = {"123@#$%^&*", ""})
    void test3(String search) {
        SearchService searchService = new SearchService();
        searchService.search(search);

        List<String> titles = searchService.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertEquals(200, searchService.getResponseStatusCode());
    }

    @DisplayName("Status code is 200 when sending null")
    @ParameterizedTest
    @EmptySource
    void test4(String search) {
        SearchService searchService = new SearchService();
        searchService.search(search);

        List<String> titles = searchService.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertEquals(200, searchService.getResponseStatusCode());
    }
}
