package com.incubyte.utils;
import java.util.Random;

public class RandomEmailGenerator {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // Alphabet for email username
    private static final String DOMAIN = "example.com"; // Fixed domain
    private static final int USERNAME_LENGTH = 10; // Length of the random username
    private static final Random RAND = new Random();

    /**
     * Generates a random alphabetic string for the email username.
     */
    private static String generateRandomUsername(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = RAND.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Generates a random email ID.
     */
    public static String generateRandomEmail() {
        String username = generateRandomUsername(USERNAME_LENGTH); // Generate random username
        return username + "@" + DOMAIN; // Combine username with the domain
    }
}

