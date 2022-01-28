package com.zero.pages;

import com.zero.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zero.utilities.Driver.getDriver;

public class ActivityFeature extends TestBase {
    public ActivityFeature() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"account_activity_tab\"]/a")
    public WebElement Activity;

    @FindBy(css = "[name=accountId]")
    public WebElement accountDrop;

    @FindBy(xpath = "//*[@id=\"all_transactions_for_account\"]/table/thead/tr/th")
    public WebElement Tablecolumns;



    public void AccountActivity() {
        Assert.assertTrue(Activity.isDisplayed());
    }

    public void accountSaving() {
        Activity.click();
        Select stateDropdown = new Select(accountDrop);
        stateDropdown.selectByValue("1");
        String actual = stateDropdown.getFirstSelectedOption().getText();
        String expected = "Savings";
        Assert.assertEquals(actual, expected);
    }

    public void dropDownOptions() {
        Activity.click();
        Select stateDropdown = new Select(accountDrop);
        List<WebElement> options = stateDropdown.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
            Assert.assertTrue(option.isDisplayed());
        }
    }

    public void transactionColumns() {
        Activity.click();
        List<String> expectedList = new ArrayList<>(Arrays.asList("Date", "Description", "Deposit", "Withdrawal"));
        List<WebElement> columns = driver.findElements(By.xpath("//*[@id=\"all_transactions_for_account\"]/table/thead/tr/th"));
        for (int i = 0; i < columns.size(); i++) {
            System.out.println(columns.get(i).getText());
            Assert.assertEquals(columns.get(i).getText(), expectedList.get(i));
        }
    }


}

