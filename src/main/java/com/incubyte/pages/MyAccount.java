package com.incubyte.pages;

import com.incubyte.base.TestBase;
import com.incubyte.utils.ConfigReader;
import com.incubyte.utils.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

import static com.incubyte.base.TestBase.getDriver;

public class MyAccount {
    private final WebDriver driver = getDriver();
    private final By createAccountButton = By.linkText("Create an Account");

    public void clickOnCreateAnAccountLink() {
        driver.get(ConfigReader.getBaseUrl());
        driver.findElement(createAccountButton).click();
    }

    public void myAccountWelcomePageCheck() throws IOException {
        String filePath = "src/test/java/com/incubyte/testData/TestData.xlsx";
        String sheetName = "CreateNewCustomerAccount";
        ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);
        String firstName = excelUtility.readDataByTagName(TestBase.tagName, "FirstName");
        String lastName = excelUtility.readDataByTagName(TestBase.tagName, "LastName");
        String expectedFullName = driver.findElement(getContactInformationFullName(firstName + " " + lastName)).getText();
        String actualFullName = firstName + " " + lastName;
        Assert.assertTrue(expectedFullName.contains(actualFullName));
    }

    /**
     * Generates a dynamic locator for contact information based on the provided link text.
     *
     * @param linkText The visible text of the link (e.g., "Sign In", "Register").
     * @return A By locator for the specified link text.
     */
    public By getContactInformationFullName(String linkText) {
        // Dynamic XPath using parameterized link text
        return By.xpath("//*[contains(text(),'" + linkText + "')]");
    }

}
