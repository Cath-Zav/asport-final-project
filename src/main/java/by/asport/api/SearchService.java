package by.asport.api;

import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SearchService {
    private static final String BASE_URI = "https://asport.by";
    private static final String PATH = "/find";

    private Response response;

    public void doRequest(String search) {
        response = given()
                .baseUri(BASE_URI)
                .basePath(PATH)
                .urlEncodingEnabled(true)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .queryParam("findtext", search)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response getResponse() {
        return response;
    }

    public List<String> extractProductNames() {
        String html = new String(response.asByteArray(), StandardCharsets.UTF_8);
        Document doc = Jsoup.parse(html, BASE_URI);

        Elements els = doc.select("div.product-name");
        return els.stream()
                .map(e -> e.text().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private static String normalize(String s) {
        return s == null ? "" : s.replaceAll("\\s+", " ").trim().toLowerCase(Locale.ROOT);
    }
}
