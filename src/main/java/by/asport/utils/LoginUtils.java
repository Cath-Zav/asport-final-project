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
//    private static final String aToZ="abcdefghigklmnopqrstuvwxyz1234567890";
//
//    public static String createRandomWord() {
//        int wordLength = ThreadLocalRandom.current().nextInt(1, 26);
//        StringBuilder randomWord = new StringBuilder();
//        Random rand = new Random();
//
//        for(int i = 0; i < wordLength; i++) {
//            int randIndex = rand.nextInt(aToZ.length());
//            randomWord.append(aToZ.charAt(randIndex));
//        }
//        return randomWord.toString();
//    }

    public static String getRandomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }
}
