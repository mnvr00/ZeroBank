package com.zero.pages;

import com.zero.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "[id=user_login]")
    public WebElement usernameInput;

    @FindBy(css = "[id=user_password]")
    public WebElement passwordInput;

    @FindBy(css = "[type=submit]")
    public WebElement signinButton;

}
