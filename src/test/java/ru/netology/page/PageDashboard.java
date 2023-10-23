package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PageDashboard {
    private SelenideElement header = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private String action = "[data-test-id=action-deposit]";
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PageDashboard() {
        header.shouldBe(visible);
        header.shouldHave(text("Личный кабинет"));
    }

    public int getCardBalance(int index) {
        var text = cards.get(index - 1).getText();
        return extractBalance(text);
    }

    public PageTransit selectTransitCard(int index) {
        cards.get(index - 1).$(action).click();
        return new PageTransit();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
