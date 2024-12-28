package com.incubyte.utils;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueNameGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int NAME_LENGTH = 8; // Length of the generated name
    private static final Random RAND = new Random();
    private static final Set<String> USED_NAMES = new HashSet<>(); // To ensure uniqueness during the session

    /**
     * Generates a random alphabetic string of a fixed length.
     */
    private static String generateRandomAlphabeticString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = RAND.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Generates a unique first or last name, ensuring no duplication.
     */
    public static String generateUniqueName() {
        String name;
        do {
            name = generateRandomAlphabeticString(NAME_LENGTH);
        } while (USED_NAMES.contains(name)); // Ensure uniqueness by checking against the set
        USED_NAMES.add(name); // Mark this name as used
        return name;
    }
}
