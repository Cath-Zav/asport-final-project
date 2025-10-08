package by.asport.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginService {
    private static final String BASE_URI = "https://asport.by";
    private static final String AUTH_PATH = "/user/auth";
    private static final String VALID_EMAIL = "cathzavizion%40gmail.com";
    private static final String VALID_PASSWORD = "itAcademy2025%26";
    private final String BODY_TEMPLATE= "login=login&type=email_password&email=%s&password=%s";

    private Response response;

    public String getValidEmailL() {
        return VALID_EMAIL;
    }

    public String getValidPassword() {
        return VALID_PASSWORD;
    }

    public String createBody(String email, String password) {
        return String.format(BODY_TEMPLATE, email, password);
    }

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");
        return headers;
    }

    public void doRequest(String email, String password) {
        response = given()
                .headers(createHeaders())
                .body(createBody(email, password))
                .when().post(BASE_URI + AUTH_PATH);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getInvalidBodyMessage() {
        return response.getBody().path("message");
    }

    public String getInvalidBodyErrorsPassword() {
        return response.getBody().path("errors.password[0]");
    }

    public String getInvalidBodyErrorsEmail() {
        return response.getBody().path("errors.email[0]");
    }

    public int getValidBodyMessage() {
        return response.getBody().path("id");
    }
}
