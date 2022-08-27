package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.web.data.DataHelper;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

@Value
public class TransferPage {
    SelenideElement h1 = $("h1");
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement title = $("h1.heading");
    private final SelenideElement amountField = $("[data-test-id=amount] input");
    private final SelenideElement fromField = $("[data-test-id=from] input");
    SelenideElement from = $("[data-test-id=from] [type=tel]");
    SelenideElement transferTo = $("[data-test-id=to]");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    DashboardPage dashboardPage = new DashboardPage();

    public TransferPage() {
        h1.shouldBe(Condition.visible, Duration.ofMillis(15));
    }

    Faker faker = new Faker(new Locale("ru"));
    private int amountPositive = faker.number().numberBetween(1, 1000);
    private int amountAboveBalance = faker.number().numberBetween(100000,200000);
    private int amountMinusSum = faker.number().numberBetween(-10000,-1);


    public void transferFromFirstToSecond (int amountPositive){
        String firstCard = DataHelper.getFirstCardInfo().getNumberCard();
        String secondCard = DataHelper.getSecondСardInfo().getNumberCard();
        dashboardPage.appearedFirstCard();
        amountField.doubleClick().val(amountPositive + "");
        from.val(secondCard);
        transferButton.click();
    }
    public void transferFromSecondToFirst (int amountPositive) {
        String firstCard = DataHelper.getFirstCardInfo().getNumberCard();
        String secondCard = DataHelper.getSecondСardInfo().getNumberCard();
        dashboardPage.appearedSecondCard();
        amountField.doubleClick().val(amountPositive + "");
        from.val(firstCard);
        transferButton.click();
    }
    public void transferFromFirstToSecondWithMinusSum (int amountMinusSum) {
        String firstCard = DataHelper.getFirstCardInfo().getNumberCard();
        String secondCard = DataHelper.getSecondСardInfo().getNumberCard();
        dashboardPage.appearedFirstCard();
        amountField.doubleClick().val(amountMinusSum + "");
        from.val(secondCard);
        transferButton.click();
    }

    public void transferFromFirstToSecondOverBalance (int amountAboveBalance) {
        String firstCard = DataHelper.getFirstCardInfo().getNumberCard();
        String secondCard = DataHelper.getSecondСardInfo().getNumberCard();
        dashboardPage.appearedFirstCard();
        amountField.doubleClick().val(amountAboveBalance + "");
        from.val(secondCard);
        transferButton.click();
    }


}

