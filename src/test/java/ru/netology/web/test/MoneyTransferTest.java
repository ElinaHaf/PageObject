package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        //var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldCardBalancing() {
        var dashboardPage = new DashboardPage();
        var firstCardId = DataHelper.getFirstCardId();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var secondCardId = DataHelper.getSecondCardId();
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardId);
    }

    @Test
    public void shouldTransferFromFirstToSecond() {
        var dashboardPage = new DashboardPage();
        var firstCardId = DataHelper.getFirstCardId();
        var initialBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var secondCardId = DataHelper.getSecondCardId();
        var initialBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        var transferPage = dashboardPage.transfer(secondCardId);
        var transferInfo = DataHelper.getSecondCardTransferInfoPositive();
        transferPage.transferBetweenOwnCards(transferInfo);
        var finalBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var finalBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        assertEquals(transferInfo.getAmount(), initialBalanceFirstCard - finalBalanceFirstCard);
        assertEquals(transferInfo.getAmount(), finalBalanceSecondCard - initialBalanceSecondCard);
    }
    @Test
    public void shouldTransferFromSecondToFirst() {
        var dashboardPage = new DashboardPage();
        var firstCardId = DataHelper.getFirstCardId();
        var initialBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var secondCardId = DataHelper.getSecondCardId();
        var initialBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        var transferPage = dashboardPage.transfer(firstCardId);
        var transferInfo = DataHelper.getFirstCardTransferInfoPositive();
        transferPage.transferBetweenOwnCards(transferInfo);
        var finalBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var finalBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        assertEquals(transferInfo.getAmount(), finalBalanceFirstCard - initialBalanceFirstCard);
        assertEquals(transferInfo.getAmount(), initialBalanceSecondCard - finalBalanceSecondCard);
    }

    @Test
    public void shouldTransferFromSecondToFirstNegativeValue() {
        var dashboardPage = new DashboardPage();
        var firstCardId = DataHelper.getFirstCardId();
        var initialBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var secondCardId = DataHelper.getSecondCardId();
        var initialBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        var transferPage = dashboardPage.transfer(firstCardId);
        var transferInfo = DataHelper.getFirstCardTransferInfoNegative();
        transferPage.transferBetweenOwnCards(transferInfo);
        var finalBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var finalBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        assertEquals(-transferInfo.getAmount(), finalBalanceFirstCard - initialBalanceFirstCard);
        assertEquals(-transferInfo.getAmount(), initialBalanceSecondCard - finalBalanceSecondCard);
    }

    @Test
    public void shouldTransferFromFirstToSecondNegativeBalance() {
        var dashboardPage = new DashboardPage();
        var firstCardId = DataHelper.getFirstCardId();
        var initialBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var secondCardId = DataHelper.getSecondCardId();
        var initialBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        var transferPage = dashboardPage.transfer(secondCardId);
        var transferInfo = DataHelper.getSecondCardTransferInfoNegative();
        transferPage.transferBetweenOwnCards(transferInfo);
        var finalBalanceFirstCard = dashboardPage.getCardBalance(firstCardId);
        var finalBalanceSecondCard = dashboardPage.getCardBalance(secondCardId);
        assertEquals(initialBalanceFirstCard, finalBalanceFirstCard,
                "Операция не может быть выполнена");
        assertEquals(initialBalanceSecondCard, finalBalanceSecondCard,
                "Операция не может быть выполнена");
    }
}


