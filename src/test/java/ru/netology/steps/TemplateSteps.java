package ru.netology.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.PageDashboard;
import ru.netology.page.PageLogin;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataUser.getAutInfo;
import static ru.netology.data.DataUser.getVerCode;

public class TemplateSteps {
    PageDashboard pageDashboard;

    @Пусть("Пусть пользователь залогинен с именем {string} и паролем {string},")
    public void loginWithNameAndPassword(String login, String password) {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new PageLogin();
        var autInfo = getAutInfo();
        var loginVer = loginPage.validLogin(login, password);
        var verCode = getVerCode(autInfo);
        pageDashboard = loginVer.validVer(verCode);
    }

    @И("Когда пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы,")
    public void validTransfer(String countOfMoney, String fromCardNumber, int toCardINdex){
        var transitPage = pageDashboard.selectTransitCard(toCardINdex);
        pageDashboard = transitPage.moneyValidTransit(fromCardNumber, String.valueOf(countOfMoney));
    }

    @Тогда("Тогда баланс его {int} карты из списка на главной странице должен стать {string} рублей.")
    public void finishBalance(int toCardIndex, String countOfMoneyFinish) {
        var actualCardBalance = Integer.toString(pageDashboard.getCardBalance(toCardIndex));
        String countOfMoneyFinished = countOfMoneyFinish.replaceAll(" ", "");
        assertEquals(countOfMoneyFinished, actualCardBalance);
    }


}
