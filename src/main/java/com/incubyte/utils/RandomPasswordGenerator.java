package com.incubyte.utils;
import java.util.Random;

public class RandomPasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:',.<>?/~`";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    private static final Random RAND = new Random();

    /**
     * Generates a random password with the specified criteria.
     *
     * @return A randomly generated password.
     */
    public static String generateRandomPassword() {
        int passwordLength = RAND.nextInt(9) + 8; // Random length between 8 and 16

        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each required category
        password.append(getRandomCharacter(UPPERCASE));
        password.append(getRandomCharacter(LOWERCASE));
        password.append(getRandomCharacter(DIGITS));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS));

        // Fill the remaining characters randomly from all categories
        for (int i = 4; i < passwordLength; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }

        // Shuffle the characters to ensure randomness
        return shuffleString(password.toString());
    }

    /**
     * Gets a random character from the given string.
     *
     * @param characters The string to pick a random character from.
     * @return A random character.
     */
    private static char getRandomCharacter(String characters) {
        int index = RAND.nextInt(characters.length());
        return characters.charAt(index);
    }

    /**
     * Shuffles the characters in the given string.
     *
     * @param input The string to shuffle.
     * @return A shuffled version of the string.
     */
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = RAND.nextInt(i + 1);
            // Swap characters[i] with characters[j]
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}
