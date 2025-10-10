package by.asport.api;

import by.asport.logger.BaseLogger;
import by.asport.utils.LoginUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Tag("ci-skip")
public class LoginServiceTest extends BaseLogger {
    @Test
    @DisplayName("Incorrect email and password")
    public void test1() {
        LoginService service = new LoginService();
        service.doRequest(LoginUtils.getRandomEmail(), LoginUtils.getRandomPassword());
        assertAll(
                () -> assertEquals(422, service.getStatusCode()),
                () -> assertEquals("Выбранное значение для E-Mail адрес некорректно.", service.getInvalidBodyMessage())
        );
        logger.info("Checking API: login with Incorrect email and password");
    }

    @Test
    @DisplayName("Valid email and password")
    public void test2() {
        LoginService service = new LoginService();
        service.doRequest(service.getValidEmailL(), service.getValidPassword());
        assertAll(
                () -> assertEquals(200, service.getStatusCode()),
                () -> assertEquals(61205, service.getValidBodyMessage())
        );
        logger.info("Checking API: login with Valid email and password");
    }

    @ParameterizedTest
    @DisplayName("Empty email and filled password")
    @EmptySource
    public void test3(String email) {
        LoginService service = new LoginService();
        service.doRequest(email, LoginUtils.getRandomPassword());
        assertAll(
                () -> assertEquals(422, service.getStatusCode()),
                () -> assertEquals("Поле E-Mail адрес обязательно для заполнения.", service.getInvalidBodyMessage())
        );
        logger.info("Checking API: login with Empty email and filled password");
    }

    @ParameterizedTest
    @DisplayName("Filled incorrect email and empty password")
    @EmptySource
    public void test4(String emptyPassword) {
        LoginService service = new LoginService();
        service.doRequest(LoginUtils.getRandomEmail(), emptyPassword);
        assertAll(
                () -> assertEquals(422, service.getStatusCode()),
                () -> assertEquals("Выбранное значение для E-Mail адрес некорректно.", service.getInvalidBodyErrorsEmail()),
                () -> assertEquals("Поле Пароль обязательно для заполнения.", service.getInvalidBodyErrorsPassword())
        );
        logger.info("Checking API: login with Filled incorrect email and empty password");
    }

    @Test
    @DisplayName("Invalid email")
    public void test5() {
        LoginService service = new LoginService();
        service.doRequest(LoginUtils.getRandomPassword(), LoginUtils.getRandomPassword());
        assertAll(
                () -> assertEquals(422, service.getStatusCode()),
                () -> assertEquals("Поле E-Mail адрес должно быть действительным электронным адресом.", service.getInvalidBodyMessage())
        );
        logger.info("Checking API: login with Invalid email");
    }

    @ParameterizedTest
    @DisplayName("Correct email and empty password")
    @EmptySource
    public void test6(String emptyPassword) {
        LoginService service = new LoginService();
        service.doRequest(service.getValidEmailL(), emptyPassword);
        assertAll(
                () -> assertEquals(422, service.getStatusCode()),
                () -> assertEquals("Поле Пароль обязательно для заполнения.", service.getInvalidBodyMessage())
        );
        logger.info("Checking API: login with Correct email and empty password");
    }
}

