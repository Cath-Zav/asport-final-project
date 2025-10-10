package by.asport.utils;

import by.asport.webdriver.WebDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LoginUtils {
    public static String getRandomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }
}
