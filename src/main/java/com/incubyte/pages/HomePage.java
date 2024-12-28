package com.incubyte.pages;

import com.incubyte.base.TestBase;
import com.incubyte.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.incubyte.base.TestBase.getDriver;

public class HomePage {
    private WebDriver driver = getDriver();
    private final By createAccountButton = By.linkText("Create an Account");

    public void clickOnCreateAnAccountLink() {
        driver.get(ConfigReader.getBaseUrl());
        driver.findElement(createAccountButton).click();
    }

}
