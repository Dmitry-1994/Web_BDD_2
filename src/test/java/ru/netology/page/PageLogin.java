package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PageLogin {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement buttonField = $("[data-test-id=action-login] .button__content");

    public PageVer validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        buttonField.click();
        return new PageVer();
    }
}
