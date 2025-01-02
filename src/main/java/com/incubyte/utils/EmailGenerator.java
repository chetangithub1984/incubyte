package com.incubyte.utils;

import java.util.Random;

public class EmailGenerator {

    // Predefined list of domains
    private static final String[] DOMAINS = {
            "example.com",
            "mail.com",
            "test.com",
            "demo.com",
            "sample.org",
            "email.net",
            "mydomain.com",
            "webmail.com"
            // Add more domains as needed
    };

    private static final Random RANDOM = new Random();

    /**
     * Generates an email ID based on the provided first name and last name.
     * The format is: firstname.lastname<timestamp>@randomdomain.com
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @return A unique email ID.
     * @throws IllegalArgumentException if firstName or lastName is null or empty.
     */
    public static String generateEmailId(String firstName, String lastName) {
        // Validate input
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name must not be null or empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be null or empty.");
        }

        // Sanitize inputs by trimming and converting to lowercase
        String sanitizedFirstName = firstName.trim().toLowerCase();
        String sanitizedLastName = lastName.trim().toLowerCase();

        // Get current timestamp in milliseconds
        long timestamp = System.currentTimeMillis();

        // Select a random domain from the DOMAINS array
        String domain = DOMAINS[RANDOM.nextInt(DOMAINS.length)];

        // Construct the email prefix
        String emailPrefix = String.format("%s.%s%d", sanitizedFirstName, sanitizedLastName, timestamp);

        // Combine prefix and domain to form the email
        String email = String.format("%s@%s", emailPrefix, domain);

        return email;
    }

    // Example usage
    public static void main(String[] args) {
        // Example inputs
        String firstName1 = "John";
        String lastName1 = "Doe";

        String firstName2 = "Jane";
        String lastName2 = "Smith";

        // Generate email IDs
        String email1 = generateEmailId(firstName1, lastName1);
        String email2 = generateEmailId(firstName2, lastName2);

        // Display the generated emails
        System.out.println("Generated Email 1: " + email1);
        System.out.println("Generated Email 2: " + email2);
    }
}
