package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EmptySource;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseLogger {

    @Test
    @DisplayName("Valid search - results contains \"рюкзак\"")
    void test1() {
        String search = "рюкзак";
        SearchService service = new SearchService();
        service.search(search);

        List<String> titles = service.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertTrue(
                titles.stream().allMatch(title -> title
                        .contains(search))
        );
    }

    @Test
    @DisplayName("Valid search - results contains \"123\"")
    void test2() {
        String search = "123";
        SearchService service = new SearchService();
        service.search(search);

        List<String> titles = service.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertTrue(
                titles.stream().allMatch(title -> title
                        .contains(search))
        );
    }

    @Test
    @DisplayName("Search empty")
    void test3() {
        String search = "";
        SearchService searchPage = new SearchService();
        searchPage.search(search);

        List<String> titles = searchPage.getProductTitles();
        logger.info("Всего результатов: {}", titles.size());
        titles.forEach(names -> logger.info("✔ {}", names));

        assertTrue(
                titles.stream().allMatch(title -> title
                        .contains(search))
        );
    }
}
