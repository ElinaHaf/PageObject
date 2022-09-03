package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    public void shouldTransferFromFirstToSecond() {
        var loginPage = new LoginPageV2();
        //var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        dashboardPage.appearedSecondCard();
        transferPage.moneyTransfer(DataHelper.getSecondСardInfo(),"1000");
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard, actualBalance1);
        assertEquals(startAmountSecondCard, actualBalance2);
    }
   @Test
    public void shouldTransferFromSecondToFirst() {
       var loginPage = new LoginPageV2();
        //var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        dashboardPage.appearedFirstCard();
        transferPage.moneyTransfer(DataHelper.getFirstCardInfo(),"5000");
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard, actualBalance1);
        assertEquals(startAmountSecondCard, actualBalance2);
    }

    @Test
    public void shouldTransferFromSecondToFirstNegativeValue() {
        var loginPage = new LoginPageV2();
        //var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        dashboardPage.appearedFirstCard();
        transferPage.moneyTransfer(DataHelper.getFirstCardInfo(), "-3000");
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard, actualBalance1);
        assertEquals(startAmountSecondCard, actualBalance2);
    }

    @Test
    public void shouldTransferFromFirstToSecondNegativeBalance() {
        var loginPage = new LoginPageV2();
        //var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage dashboardPage = new DashboardPage();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        dashboardPage.appearedSecondCard();
        transferPage.moneyTransfer(DataHelper.getSecondСardInfo(), "120000");
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        transferPage.getError();

        assertEquals(startAmountFirstCard, actualBalance1);
        assertEquals(startAmountSecondCard, actualBalance2);
    }
}


