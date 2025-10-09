package by.asport.api;

import by.asport.logger.BaseLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartServiceTest extends BaseLogger {
    @Test
    @DisplayName("Add valid product to Cart with valid quantity")
    public void test1() {
        CartService service = new CartService();
        service.AddToCart(service.getValidDataId(), service.getValidQuant());
        assertAll(
                () -> assertEquals(200, service.getAddToCartResponseStatusCode()),
                () -> assertEquals("\"success\"", service.getAddToCartValidResponseBody())
        );
        logger.info("Checking API: add valid product to Cart with valid quantity");
    }

    @Test
    @DisplayName("Add invalid product to Cart with valid quantity")
    public void test2() {
        CartService service = new CartService();
        service.AddToCart("0", service.getValidQuant());
        assertAll(
                () -> assertEquals(422, service.getAddToCartResponseStatusCode()),
                () -> assertEquals("Выбранное значение для data id некорректно.", service.getAddToCartInvalidResponseBody())
        );
        logger.info("Checking API: add invalid product to Cart with valid quantity");
    }

    @Test
    @DisplayName("Add valid product to Cart with invalid quantity")
    public void test3() {
        CartService service = new CartService();
        service.AddToCart(service.getValidDataId(), "10001");
        assertAll(
                () -> assertEquals(422, service.getAddToCartResponseStatusCode()),
                () -> assertEquals("Поле quant не может быть более 10000.", service.getAddToCartInvalidResponseBody())
        );
        logger.info("Checking API: add invalid product to Cart with valid quantity");
    }

    @Test
    @DisplayName("Add product to cart and go to cart to check it's title")
    public void test4() {
        CartService service = new CartService();
        service.AddToCart("8981", service.getValidQuant());
        service.goToCart();
        assertAll(
                () -> assertEquals(200, service.getAddToCartResponseStatusCode()),
                () -> assertEquals("Роликовые коньки раздвижные (30-33, 34-37) Favorit TE-285PR", service.getProductTitleInCart(0))
        );
        logger.info("Checking API: add valid product to Cart and check it's title");
    }
}
