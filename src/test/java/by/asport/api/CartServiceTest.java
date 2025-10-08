//package by.asport.api;
//
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.anyOf;
//import static org.hamcrest.Matchers.equalTo;
//
//public class CartServiceTest {
//    @Test
//    public void addToCart() {
//        given()
//                .headers("Accept", "application/json, text/javascript, */*; q=0.01")
//                .params("data_id", "8981")
//                .params("quant", "1")
//        .when().get("https://asport.by/shcart/add/")
//        .then().log().all()
//                .statusCode(200)
//                .body(anyOf(equalTo("success"), equalTo("\"success\"")));
//    }
//
//    @Test
//    public void goToCart() {
//
//        String cookie =
//    }
//}
