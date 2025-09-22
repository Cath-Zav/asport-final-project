package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseLogger {

    @Test
    void testAllNamesContainWord() {
        String search = "рюкзак";
        SearchService service = new SearchService();
        service.doRequest(search);

        List<String> names = service.extractProductNames();
        assertTrue(
                names.stream().allMatch(n -> n.trim().toLowerCase().contains(search)),
                () -> "Some results does not contain '" + search + "': " +
                        names.stream().filter(n -> !n.trim().toLowerCase().contains(search)).toList()
        );
    }
}
