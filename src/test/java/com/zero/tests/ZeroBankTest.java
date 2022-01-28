package com.zero.tests;

import com.zero.pages.ActivityFeature;
import com.zero.pages.LoginPage;
import com.zero.pages.SummaryFeature;
import org.testng.annotations.Test;

public class ZeroBankTest extends TestBase {
    @Test
    public void ZeroBank() {

        LoginPage loginpage = new LoginPage();

        loginpage.WrongUsernameANDPassword();
        loginpage.BlankUsernameAndPassword();
        loginpage.loginwithValidCredentials();


        SummaryFeature sf = new SummaryFeature();
        sf.SummaryPageZero();
        sf.AccountTypes();
        sf.Accountcolumns();


        ActivityFeature af = new ActivityFeature();
        af.AccountActivity();
        af.accountSaving();
        af.dropDownOptions();
        af.transactionColumns();


    }

    }
