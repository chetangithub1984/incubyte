package com.incubyte.pages;

import com.incubyte.base.TestBase;
import com.incubyte.utils.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.incubyte.base.TestBase.getDriver;

public class CustomerLoginPage {
    private final WebDriver driver = getDriver();
    private final By email = By.id("email");
    private final By password = By.id("pass");
    private final By signInButton = By.id("send2");

    public void customerSignIn() throws IOException {
        String filePath = "src/test/java/com/incubyte/testData/TestData.xlsx";
        String sheetName = "CreateNewCustomerAccount";
        ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);
        driver.findElement(email).sendKeys(excelUtility.readDataByTagName(TestBase.tagName, "Email"));
        driver.findElement(password).sendKeys(excelUtility.readDataByTagName(TestBase.tagName, "Password"));
        driver.findElement(signInButton).click();
    }
}
