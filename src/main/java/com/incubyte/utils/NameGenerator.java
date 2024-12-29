package com.incubyte.utils;
import java.util.Random;

public class NameGenerator {

    // Arrays of sample first and last names
    private static final String[] FIRST_NAMES = {
            "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael",
            "Linda", "William", "Elizabeth", "David", "Barbara", "Richard", "Susan",
            "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher",
            "Nancy", "Daniel", "Lisa", "Matthew", "Betty", "Anthony", "Margaret",
            "Mark", "Sandra", "Donald", "Ashley", "Steven", "Kimberly", "Paul",
            "Emily", "Andrew", "Donna", "Joshua", "Michelle", "Kenneth", "Dorothy",
            "Kevin", "Carol", "Brian", "Amanda", "George", "Melissa", "Edward",
            "Deborah"
            // ... Add more first names as needed
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
            "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez",
            "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark",
            "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King",
            "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green",
            "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
            "Carter", "Roberts"
            // ... Add more last names as needed
    };

    private static final Random RANDOM = new Random();

    /**
     * Generates a random first name.
     *
     * @return A randomly selected first name.
     */
    public static String getRandomFirstName() {
        int index = RANDOM.nextInt(FIRST_NAMES.length);
        return FIRST_NAMES[index];
    }

    /**
     * Generates a random last name.
     *
     * @return A randomly selected last name.
     */
    public static String getRandomLastName() {
        int index = RANDOM.nextInt(LAST_NAMES.length);
        return LAST_NAMES[index];
    }

    /**
     * Generates a random full name by combining a first name and a last name.
     *
     * @return A randomly generated full name.
     */
    public static String getRandomFullName() {
        return getRandomFirstName() + " " + getRandomLastName();
    }

    /**
     * Generates an array of random full names.
     *
     * @param count The number of random names to generate.
     * @return An array containing randomly generated full names.
     */
    public static String[] getRandomFullNames(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be a positive integer.");
        }

        String[] fullNames = new String[count];
        for (int i = 0; i < count; i++) {
            fullNames[i] = getRandomFullName();
        }
        return fullNames;
    }

    // Example usage
    public static void main(String[] args) {
        NameGenerator generator = new NameGenerator();

        // Generate a single random full name
        String randomName = generator.getRandomFullName();
        System.out.println("Random Full Name: " + randomName);

        // Generate multiple random full names
        int numberOfNames = 10;
        String[] randomNames = generator.getRandomFullNames(numberOfNames);
        System.out.println("\nList of Random Full Names:");
        for (String name : randomNames) {
            System.out.println(name);
        }
    }
}
