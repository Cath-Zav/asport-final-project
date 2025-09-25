package by.asport.api;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SearchService {
    private static final String BASE_URI = "https://asport.by";
    private static final String FIND_PATH = "/find";
    private static final String EXTENDED_PATH = "/template/find/extended";
    private static final String CSS_PRODUCT_TITLE = "div.ok-product__main [itemprop=name], span[itemprop=name]";
    private String xsrfToken;

    private Response resp;

    public void getToken(String search) {
        Response responseWithToken =
                given()
                        .headers("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                        .when()
                        .get(BASE_URI + FIND_PATH + "?findtext=" + URLEncoder.encode(search, StandardCharsets.UTF_8))
                        .then()
                        .extract()
                        .response();

        xsrfToken = URLDecoder.decode(responseWithToken.getCookie("XSRF-TOKEN"), StandardCharsets.UTF_8);
    }

    public Map<String, String> createHeadersForExtendedRequest(String search) {
        if (xsrfToken == null) {
            getToken(search);
        }
        Map<String, String> headersExtended = new HashMap<>();
        headersExtended.put("accept", "application/json, text/plain, */*");
        headersExtended.put("X-XSRF-TOKEN", xsrfToken);

        return headersExtended;
    }

//    public List<MultiPartSpecification> createBody(String searchKey) {
//        List<MultiPartSpecification> body = new ArrayList<>();
//        body.add(new MultiPartSpecBuilder("").controlName("sort").charset("UTF-8").build());
//        body.add(new MultiPartSpecBuilder(String.valueOf(1)).controlName("page_num").charset("UTF-8").build());
//        body.add(new MultiPartSpecBuilder(searchKey).controlName("findtext").mimeType("text/plain").charset("UTF-8").build());
//        body.add(new MultiPartSpecBuilder("utf-8").controlName("charset").charset("UTF-8").build());
//        body.add(new MultiPartSpecBuilder("1").controlName("item_status").charset("UTF-8").build());
//        return body;
//    }


    public void searchRequest(String searchKey) {

        resp = given()
                .headers(createHeadersForExtendedRequest(searchKey))
                .multiPart("findtext", searchKey)
                .multiPart("page_num", "1")
                .multiPart("sort", "")
                .multiPart("charset", "utf-8")
                .multiPart("item_status", "1")
                .when()
                .post(BASE_URI + EXTENDED_PATH)
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
        Elements els = doc.select(CSS_PRODUCT_TITLE);
        return els.stream()
                .map(e -> e.text().trim().toLowerCase())
                .toList();
    }

    public String getNotFoundMessage() {
        String html = resp.jsonPath().getString("content");
        Document doc = Jsoup.parse(html);
        Element notFoundText = doc.selectFirst("p.h3");
        if (notFoundText == null) {
            throw new NoSuchElementException("notFoundText is missing");
        } else {
            return notFoundText.ownText().trim();
        }
    }
}
