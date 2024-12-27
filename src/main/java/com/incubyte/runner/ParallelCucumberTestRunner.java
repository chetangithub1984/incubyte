package com.incubyte.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;


/**
 * A Cucumber runner class that supports parallel execution with TestNG.
 */
@CucumberOptions(
        features = "src/test/java/com/incubyte/features",        // Path to your feature files
        glue = "src/main/java/com/incubyte/stepdefination",            // Step definitions package
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class ParallelCucumberTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Override the scenarios() method and enable parallel execution.
     * TestNG will handle splitting the scenarios into parallel threads.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
