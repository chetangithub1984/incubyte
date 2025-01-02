package com.incubyte.base;

import com.incubyte.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Collection;

public class TestBase {

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    // ThreadLocal ensures each thread (test) gets its own WebDriver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static String tagName;

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Initialize WebDriver based on browser name (e.g., chrome, firefox, edge).
     */
    public static void initializeDriver(String browser) {
        try {
            if (browser == null || browser.isEmpty()) {
                browser = ConfigReader.getBrowser();
            }

            WebDriver driver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                    logger.info("Chrome browser launched successfully");
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                    logger.info("Firefox browser launched successfully");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
                    logger.info("Edge browser launched successfully");
                    break;
                default:
                    logger.error("Unrecognized browser: {0}, defaulting to Chrome.", browser);
                    WebDriverManager.chromedriver().setup();
                    driver = new org.openqa.selenium.chrome.ChromeDriver();
                    break;
            }

            driver.manage().window().maximize();
            // Optional: Implicit Wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Set the driver in ThreadLocal
            driverThreadLocal.set(driver);

        } catch (Exception e) {
            logger.error("Error initializing the WebDriver: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Quit the driver to free resources.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                logger.error("Error quitting the WebDriver: ", e);
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    @Before
    public void init() {
        initializeDriver(ConfigReader.getBrowser());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        // Retrieve all tags associated with the current scenario
        Collection<String> tags = scenario.getSourceTagNames();

        // Iterate through the tags and perform desired operations
        for (String tag : tags) {
            System.out.println("Scenario Tag: " + tag);
            tagName = tag.replace("@", "");
        }
    }

    @After
    public void tear(){
        quitDriver();
    }
}
