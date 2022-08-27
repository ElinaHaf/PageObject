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
        DashboardPage dashboardPage = new DashboardPage();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        var amount = transferPage.getAmountPositive();
        transferPage.transferFromFirstToSecond(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard+amount, actualBalance1);
        assertEquals(startAmountSecondCard, actualBalance2+amount);
    }
   @Test
    public void shouldTransferFromSecondToFirst() {
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
        var amount = transferPage.getAmountPositive();
        transferPage.transferFromSecondToFirst(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard, actualBalance1+amount);
        assertEquals(startAmountSecondCard+amount, actualBalance2);
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
        var firstCardId = DataHelper.getFirstCardInfo();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        var amount = transferPage.getAmountMinusSum();
        transferPage.transferFromFirstToSecondWithMinusSum(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard-amount, actualBalance1);
        assertEquals(startAmountSecondCard+amount, actualBalance2);
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
        var firstCardId = DataHelper.getFirstCardInfo();
        var startAmountFirstCard = dashboardPage.getCardBalance(0);
        var startAmountSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = new TransferPage();
        var amount = transferPage.getAmountAboveBalance();
        transferPage.transferFromFirstToSecondOverBalance(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        assertEquals(startAmountFirstCard - amount, actualBalance1);
        assertEquals(startAmountSecondCard + amount, actualBalance2);
    }
}


