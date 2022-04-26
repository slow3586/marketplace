package ru.teamtwo.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.teamtwo.telegrambot.service.TelegramBotUpdateHandler;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final TelegramBotUpdateHandler telegramBotUpdateHandler;

    public TelegramBot(TelegramBotUpdateHandler telegramBotUpdateHandler) {
        this.telegramBotUpdateHandler = telegramBotUpdateHandler;
    }

    @Override
    public String getBotUsername() {
        return "Marketplace Bot";
    }

    @Override
    public String getBotToken() {
        return "5189238062:AAEAKaN6jnS223nSlwKu3QPsLKS0rXi3C6c";
    }

    @Override
    public void onUpdateReceived(Update update) {
        telegramBotUpdateHandler.handle(update);
    }
}
