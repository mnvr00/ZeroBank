package com.zero.pages;

import com.zero.tests.TestBase;
import com.zero.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends TestBase {

   public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "[id=user_login]")
    public WebElement usernameInput;

    @FindBy(css = "[id=user_password]")
    public WebElement passwordInput;

    @FindBy(css = "[type=submit]")
    public WebElement signinButton;

    @FindBy(css="[class='alert alert-error']")
    public WebElement errorMessage;

    @FindBy(css = "[id=signin_button]")
    public WebElement firstsignin;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div")
    public WebElement summarypage;

     public void loginwithValidCredentials() {

        usernameInput.sendKeys("username");
        passwordInput.sendKeys("password");
        signinButton.click();

       Assert.assertTrue(summarypage.isDisplayed());
    }



    public void WrongUsernameANDPassword() {
        firstsignin.click();
        usernameInput.sendKeys("abcde");
        passwordInput.sendKeys("12344567");
        signinButton.click();
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    public void BlankUsernameAndPassword(){

        usernameInput.sendKeys("");
        passwordInput.sendKeys("");
        signinButton.click();
        Assert.assertTrue(errorMessage.isDisplayed());

    }








}
