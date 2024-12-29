package com.incubyte.pages;

import com.incubyte.base.TestBase;
import com.incubyte.stepdefination.Stepdefs;
import com.incubyte.utils.ExcelUtility;
import com.incubyte.utils.EmailGenerator;
import com.incubyte.utils.RandomPasswordGenerator;
import com.incubyte.utils.NameGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

import static com.incubyte.base.TestBase.getDriver;

public class CreateNewCustomerAccount {
    private final WebDriver driver = getDriver();
    private final By FirstName = By.xpath("//input[@id='firstname']");
    private final By LastName = By.xpath("//input[@id='lastname']");
    private final By Email = By.xpath("//input[@id='email_address']");
    private final By Password = By.xpath("//input[@id='password']");
    private final By PasswordConfirmation = By.xpath("//input[@id='password-confirmation']");
    private final By CreateAnAccountButton = By.xpath("//button[@title='Create an Account']");
    private final By AccountCreationConfirmationText = By.xpath("//*[text()='Thank you for registering with Main Website Store.']");
    private final By LogoutDropDownArrow = By.xpath("//button[@class='action switch']");
    private final By SignOutButton = By.xpath("//a[contains(text(),'Sign Out')]");

    public void createAnAccount() throws IOException {
        String filePath = "src/test/java/com/incubyte/testData/TestData.xlsx";
        String sheetName = "CreateNewCustomerAccount";
        ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);
        String first_name = NameGenerator.getRandomFirstName();
        String last_name = NameGenerator.getRandomLastName();
        String email_id = EmailGenerator.generateEmailId(first_name,last_name);
        String password = RandomPasswordGenerator.generateRandomPassword();
        excelUtility.updateDataByTagName(TestBase.tagName, "FirstName", first_name);
        excelUtility.updateDataByTagName(TestBase.tagName, "LastName", last_name);
        excelUtility.updateDataByTagName(TestBase.tagName, "Email", email_id);
        excelUtility.updateDataByTagName(TestBase.tagName, "Password", password);
        driver.findElement(FirstName).sendKeys(first_name);
        driver.findElement(LastName).sendKeys(last_name);
        driver.findElement(Email).sendKeys(email_id);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(PasswordConfirmation).sendKeys(password);
        driver.findElement(CreateAnAccountButton).click();
        Assert.assertEquals(driver.findElement(AccountCreationConfirmationText).getText(),"Thank you for registering with Main Website Store.");
        driver.findElement(LogoutDropDownArrow).click();
        driver.findElement(SignOutButton).click();
    }

}
