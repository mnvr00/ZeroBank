package com.zero.pages;

import com.zero.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zero.utilities.Driver.getDriver;

public class SummaryFeature extends TestBase {
    public SummaryFeature(){
        PageFactory.initElements(getDriver(),this);
    }


    @FindBy(xpath = "//strong[contains(text(),'Online Banking')]")
    public WebElement onlineBanking;

    @FindBy(xpath = "//*[@id=\"account_summary_tab\"]/a")
    public WebElement accountSummaryPage;

    @FindBy(xpath ="//*[@class='board-header']" )
    public WebElement accountTYpes;


    public void SummaryPageZero() {
        //onlineBanking.click();
        Assert.assertTrue(accountSummaryPage.isDisplayed());

    }

    public void AccountTypes(){
        List<String> expectedList = new ArrayList<String>(Arrays.asList("Cash Accounts", "Investment Accounts", "Credit Accounts", "Loan Accounts"));
        List<WebElement> elements = getDriver().findElements(By.tagName("h2"));
        for (int i = 0; i < elements.size(); i ++ ) {
            System.out.println(elements.get(i).getText());
            Assert.assertEquals(elements.get(i).getText(), expectedList.get(i));
        }

    }

    public void Accountcolumns(){
        List<String> expectedList = new ArrayList<>(Arrays.asList("Account", "Credit Card", "Balance"));
        List<WebElement> elements = driver.findElements(By.xpath("(//*[@class='table'])[3]//th"));
        for (int i = 0; i < elements.size(); i ++ ) {
            System.out.println(elements.get(i).getText());
            Assert.assertEquals(elements.get(i).getText(), expectedList.get(i));

        }
    }



}
