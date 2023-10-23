package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PageTransit {
    private SelenideElement headText = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement executeField = $("[data-test-id=action-transfer] .button__content");
    private SelenideElement massageError = $(byText("Ошибка!"));

    public PageTransit() {
        headText.shouldBe(visible);
    }

    public void moneyTransit(String cardNumber, String amountMoneyTransit) {
        amountField.setValue(amountMoneyTransit);
        fromField.setValue(cardNumber);
        executeField.click();
    }

    public PageDashboard moneyValidTransit(String cardNumber, String amountMoneyTransit) {
        moneyTransit(cardNumber, amountMoneyTransit);
        return new PageDashboard();
    }

}
