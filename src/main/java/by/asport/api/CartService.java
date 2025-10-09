package by.asport.api;

import by.asport.utils.CartUtils;
import io.restassured.response.Response;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartService extends BaseService {
    private static final String ADD_TO_CART_ENDPOINT = "/shcart/add/";
    private static final String GO_TO_CART_ENDPOINT = "/shcart/get/";
    private String xsrfToken;
    private Map<String, String> cookies;

    List<String> ids = List.of("8980", "999709", "106342683", "106340519", "106342684", "3474");
    List<String> listOfProductsInCart;

    private Response addToCartResponse;
    private Response cartResponse;

    public String getValidDataId() {
        return CartUtils.getRandomProductId(ids);
    }

    public String getValidQuant() {
        return CartUtils.getRandomProductQuant();
    }

    private Map<String, String> getParamsForAddingToCart(String data_id, String quant) {
        Map<String, String> params = new HashMap<>();
        params.put("data_id", data_id);
        params.put("quant",quant);
        return params;
    }

    public void AddToCart(String data_id, String quant) {
        addToCartResponse = given()
                .headers("Accept", "application/json, text/javascript, */*; q=0.01")
                .params(getParamsForAddingToCart(data_id, quant))
                .when().get(BASE_URI + ADD_TO_CART_ENDPOINT)
                .then()
                .extract()
                .response();

        cookies = addToCartResponse.getCookies();
        xsrfToken = URLDecoder.decode(addToCartResponse.getCookie("XSRF-TOKEN"), StandardCharsets.UTF_8);
    }

    public int getAddToCartResponseStatusCode() {
        return addToCartResponse.getStatusCode();
    }

    public String getAddToCartValidResponseBody() {
        return addToCartResponse.getBody().prettyPrint();
    }

    public String getAddToCartInvalidResponseBody() {
        return addToCartResponse.getBody().path("message");
    }

    public void goToCart() {
        cartResponse =
                given()
                        .accept("application/json, text/javascript, */*; q=0.01")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .header("X-XSRF-TOKEN", xsrfToken)
                        .cookies(cookies)
                .when().get(BASE_URI + GO_TO_CART_ENDPOINT)
                        .then().extract().response();
    }

    public String getProductTitleInCart(int index) {
        listOfProductsInCart =  cartResponse.jsonPath().getList("items.title", String.class);
        return listOfProductsInCart.get(index);
    }
}
