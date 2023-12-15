package utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomUtils {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";

    public static int getRandomInt(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(getRandomInt(CHARACTERS.length()));
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    public static String generateRandomNumber(int length) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = DIGITS.charAt(getRandomInt(DIGITS.length()));
            randomString.append(randomChar);
        }
        return randomString.toString();
    }
}
