package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;


public class TransferPage {
    SelenideElement h1 = $("h1");
    private  SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement error = $("[data-test-id=error-notification]");



    public DashboardPage moneyTransfer(DataHelper.CardInfo from, String howMuch) {
        amount.setValue(howMuch);
        fromField.setValue(from.getNumberCard());
        transferButton.click();
        return new DashboardPage();
    }
        public void getError () {
            error.shouldBe(Condition.visible);
        }


    }

