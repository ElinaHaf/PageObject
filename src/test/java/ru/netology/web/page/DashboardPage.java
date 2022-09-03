package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    private SelenideElement personalAccount = $("[data-test-id=dashboard]");
    private final SelenideElement appearedFirstCard = $$("[data-test-id=action-deposit]").get(0);
    private final SelenideElement appearedSecondCard = $$("[data-test-id=action-deposit]").get(1);
    private final SelenideElement title = $("h1.heading");
    private final String balanceStart = "баланс:";
    private final String balanceFinish = "р.";

    public DashboardPage() {
        heading.shouldBe(visible);
        title.shouldHave(text("Ваши карты"));
    }
    private ElementsCollection cards = $$(".list__item div");

    public int getCardBalance(int index){
        String text = cards.get(index).text();
        return getBalanceFromText(text);
    }

    private int getBalanceFromText (String cardInfo) {
        var value = cardInfo.substring
                (cardInfo.indexOf(balanceStart) + balanceStart.length(),
                        cardInfo.indexOf(balanceFinish)).trim();
        return Integer.parseInt(value);
    }
    public void appearedFirstCard(){
        appearedFirstCard.click();
    }
    public void appearedSecondCard(){
        appearedSecondCard.click();
    }
}





