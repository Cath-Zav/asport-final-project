package by.asport.api;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class SearchService {
    private static final String BASE_URI = "https://asport.by";
    private static final String FIND_PATH = "/find";
    private static final String EXTENDED_PATH = "/template/find/extended";
    private static final String CSS_PRODUCT_TITLE = "div.ok-product__main [itemprop=name], span[itemprop=name]";

    private final CookieFilter cookieFilter = new CookieFilter();
    private Response resp;

    String xsrf;

    private Map<String, String> createHeadersForExtendedRequest() {
        Map<String, String> headersExtended = new HashMap<>();
        headersExtended.put("Accept", "application/json, text/plain, */*");
        headersExtended.put("Origin", BASE_URI);
        headersExtended.put("Referer", BASE_URI + FIND_PATH + "/");
        headersExtended.put("X-XSRF-TOKEN", xsrf);

        return headersExtended;
    }

    public void search(String query) {
        Response bootstrap = given()
                .baseUri(BASE_URI)
                .filter(cookieFilter)
                .when()
                .get(FIND_PATH + "?findtext=" + URLEncoder.encode(query, StandardCharsets.UTF_8))
                .then()
                .extract()
                .response();

        xsrf = Optional.ofNullable(bootstrap.getCookie("XSRF-TOKEN"))
                .map(c -> URLDecoder.decode(c, StandardCharsets.UTF_8))
                .orElse("");

        EncoderConfig enc = EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(true)
                .defaultContentCharset(StandardCharsets.UTF_8.name())
                .encodeContentTypeAs("multipart/form-data", ContentType.MULTIPART);

        resp = given()
                .config(RestAssured.config().encoderConfig(enc))
                .baseUri(BASE_URI)
                .basePath(EXTENDED_PATH)
                .filter(cookieFilter)
                .contentType("multipart/form-data; charset=UTF-8")
                .headers(createHeadersForExtendedRequest())
                .multiPart(new MultiPartSpecBuilder("").controlName("sort").charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder("1").controlName("page_num").charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(query).controlName("findtext").mimeType("text/plain").charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder("utf-8").controlName("charset").charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder("1").controlName("item_status").charset("UTF-8").build())
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public int getResponseStatusCode() {
        return resp.getStatusCode();
    }

    public List<String> getProductTitles() {
        String html = resp.jsonPath().getString("content");
        if (html == null) return List.of();
        Document doc = Jsoup.parse(html);
        // селектор шире на случай разных версток
        Elements els = doc.select(CSS_PRODUCT_TITLE);
        return els.stream()
                .map(e -> e.text().trim().toLowerCase())
                .toList();
    }
}
