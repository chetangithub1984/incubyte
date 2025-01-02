package com.incubyte.pages;

import com.incubyte.base.TestBase;
import com.incubyte.utils.EmailGenerator;
import com.incubyte.utils.ExcelUtility;
import com.incubyte.utils.NameGenerator;
import com.incubyte.utils.RandomPasswordGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

import static com.incubyte.base.TestBase.getDriver;

public class CreateNewCustomerAccount {
    private final WebDriver driver = getDriver();
    private final By firstName = By.xpath("//input[@id='firstname']");
    private final By lastName = By.xpath("//input[@id='lastname']");
    private final By email = By.xpath("//input[@id='email_address']");
    private final By password = By.xpath("//input[@id='password']");
    private final By passwordConfirmation = By.xpath("//input[@id='password-confirmation']");
    private final By createAnAccountButton = By.xpath("//button[@title='Create an Account']");
    private final By accountCreationConfirmationText = By.xpath("//*[text()='Thank you for registering with Main Website Store.']");
    private final By logoutDropDownArrow = By.xpath("//button[@class='action switch']");
    private final By signOutButton = By.xpath("//a[contains(text(),'Sign Out')]");

    public void createAnAccount() throws IOException {
        String filePath = "src/test/java/com/incubyte/testData/TestData.xlsx";
        String sheetName = "CreateNewCustomerAccount";
        ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);
        String first_name = NameGenerator.getRandomFirstName();
        String last_name = NameGenerator.getRandomLastName();
        String email_id = EmailGenerator.generateEmailId(first_name, last_name);
        String password = RandomPasswordGenerator.generateRandomPassword();
        excelUtility.updateDataByTagName(TestBase.tagName, "FirstName", first_name);
        excelUtility.updateDataByTagName(TestBase.tagName, "LastName", last_name);
        excelUtility.updateDataByTagName(TestBase.tagName, "Email", email_id);
        excelUtility.updateDataByTagName(TestBase.tagName, "Password", password);
        driver.findElement(firstName).sendKeys(first_name);
        driver.findElement(lastName).sendKeys(last_name);
        driver.findElement(email).sendKeys(email_id);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(passwordConfirmation).sendKeys(password);
        driver.findElement(createAnAccountButton).click();
        Assert.assertEquals(driver.findElement(accountCreationConfirmationText).getText(), "Thank you for registering with Main Website Store.");
        driver.findElement(logoutDropDownArrow).click();
        driver.findElement(signOutButton).click();
    }

}
