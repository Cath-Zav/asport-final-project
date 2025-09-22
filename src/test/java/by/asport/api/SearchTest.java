package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseLogger {

    @Test
    @DisplayName("All search results contains \"рюкзак\"")
    void test1() {
        String search = "рюкзак";
        SearchService service = new SearchService();
        service.doRequest(search);

        List<String> names = service.extractProductNames();
        assertTrue(
                names.stream().allMatch(n -> n.replaceAll("\\s+"," ").trim().toLowerCase(Locale.ROOT)
                        .contains(search.toLowerCase(Locale.ROOT))),
                () -> "Some results do not contain '" + search + "': " +
                        names.stream()
                                .filter(n -> !n.replaceAll("\\s+"," ").trim().toLowerCase(Locale.ROOT)
                                        .contains(search.toLowerCase(Locale.ROOT)))
                                .collect(Collectors.toList())
        );
    }
}
