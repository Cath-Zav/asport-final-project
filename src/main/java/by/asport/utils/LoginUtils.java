package by.asport.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LoginUtils {
    private static final String aToZ="abcdefghigklmnopqrstuvwxyz1234567890";

    public static String createRandomWord() {
        int wordLength = ThreadLocalRandom.current().nextInt(1, 26);
        StringBuilder randomWord = new StringBuilder();
        Random rand = new Random();

        for(int i = 0; i < wordLength; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            randomWord.append(aToZ.charAt(randIndex));
        }
        return randomWord.toString();
    }

    public static String getRandomEmail() {
        return createRandomWord() + "%40mail.com";
    }

    public static String getRandomPassword() {
        return createRandomWord();
    }
}
