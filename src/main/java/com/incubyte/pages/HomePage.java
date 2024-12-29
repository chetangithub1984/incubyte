package com.incubyte.pages;

import com.incubyte.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.incubyte.base.TestBase.getDriver;

public class HomePage {
    private final WebDriver driver = getDriver();
    private final By createAccountButton = By.linkText("Create an Account");
    private final By signInButton = By.xpath("//a[contains(text(),'Sign In')]");

    public void clickOnCreateAnAccountLink() {
        driver.get(ConfigReader.getBaseUrl());
        driver.findElement(createAccountButton).click();
    }

    public void signInButtonClick() {
        driver.findElement(signInButton).click();
    }

}
