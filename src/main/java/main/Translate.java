package main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

//https://translate.google.com/?sl=en&tl=ru&text=how%20is%20that%20possible&op=translate
public class Translate extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();
        String result;

    }

    public String getBotUsername() {
        return "English Russian Translator";
    }

    public String getBotToken() {
        return "1070759418:AAGqIcvEke1SuOfvdWn7nBQKE5Nn4sxbdPM";
    }

}
