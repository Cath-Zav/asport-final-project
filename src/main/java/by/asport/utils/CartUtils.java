package by.asport.utils;

import java.util.List;
import java.util.Random;

public class CartUtils {
    private static final Random random = new Random();

    public static <T> T getRandomProductId(List<T> ids) {
        int index = random.nextInt(ids.size());
        return ids.get(index);
    }

    public static String getRandomProductQuant() {
        return String.valueOf((int) (Math.random() * 10000));
    }
}
