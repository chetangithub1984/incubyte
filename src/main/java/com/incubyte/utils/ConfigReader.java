package com.incubyte.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    // Update this path to match the location of your config.properties file
    private static final String PROPERTY_FILE_PATH = "src/test/resources/config.properties";

    static {
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load configuration from " + PROPERTY_FILE_PATH);
        }
    }

    /**
     * Returns the value associated with the specified property key.
     *
     * @param key property key
     * @return property value as a String, or null if key doesn't exist
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Example helper method for a commonly used property: 'browser'.
     */
    public static String getBrowser() {
        return getProperty("browser");
    }

    /**
     * Example helper method for base URL of the application.
     */
    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    // Add more helper methods as needed for additional config properties...
}
