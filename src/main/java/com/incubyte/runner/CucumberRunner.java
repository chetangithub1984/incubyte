package com.incubyte.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/incubyte/features",        // Path to your feature files
        glue = "com.incubyte",            // Step definitions package
        tags = "@TC01",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        dryRun = false,
        monochrome = true
)

public class CucumberRunner {

}
