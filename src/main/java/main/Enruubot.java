package main;

import model.Lesson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class Enruubot extends TelegramLongPollingBot {
    Actions actions = new Actions();

/*
    String bil205 = "NESNE YoNELiMLi PROGRAMLAMA I\nKASIM BARIKTABASOV\nMFFB 523\n",
            bil301 = "PROGRAMLAMA DiLLERi III\nBAKIT SARSEMBAYEV\nMFFB 523",
            bil303 = "YAZILIM MuHENDiSLigi I\nCINARA CUMABAYEVA\nMFFB 524",
            bil305 = "VERiTABANI YoNETiM SiSTEMLERi I\nBAKIT SARSEMBAYEV\nMFFB 525",
            bil307 = "MANTIKSAL TASARIM\nMEHMET SELiM ELMALI\nMFFB 524",
            bil309 = "VERi iLETisiMi VE BiLGiSAYAR AgLARI\nAYCARKIN SAiT KIZI\nMFFB 525",
            bil311 = "YAPAY ZEKA VE UZMAN SiSTEMLER\nRAYIMBEK SULTANOV\nMFFB 523",
            bil371 = "WEB YAZILIMLARI TASARIMI\nKASIM BARIKTABASOV\nMFFB 321",
            bil373 = "GENEL AMAcLi MODELLEME SiSTEMi\nAYCARKIN SAiT KIZI\nMFFB 412";
*/
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            String command = update.getMessage().getText();
            String[] commandArray = command.split("\n");

            if (commandArray[0].equals("/start")) {
                SendMessage msg = new SendMessage();
                msg.setText(actions.start());
                msg.setChatId(update.getMessage().getChatId().toString());
                send(msg);
            } else if (commandArray[0].equals("/add") && commandArray.length == 1) {
                SendMessage msg = new SendMessage();
                msg.setText(actions.addDescription());
                msg.setChatId(update.getMessage().getChatId().toString());
                send(msg);
            } else if (commandArray[0].equals("/add") && commandArray.length == 4) {
                SendMessage msg = new SendMessage();
                List<Lesson> lessons = actions.addLesson(commandArray[1], commandArray[2], commandArray[3]);
                if (lessons.isEmpty())
                    msg.setText(actions.addInvalidCommand());
                else msg.setText(this.toString(lessons));
                msg.setChatId(update.getMessage().getChatId().toString());
                send(msg);
            } else if (commandArray[0].equals("/add")) {
                SendMessage msg = new SendMessage();
                msg.setText(actions.addInvalidCommand());
                msg.setChatId(update.getMessage().getChatId().toString());
                send(msg);
            } else if (commandArray[0].equals("/all")) {
                SendMessage msg = new SendMessage();
                List<Lesson> lessons = actions.all();
                msg.setText(this.toString(lessons));
                msg.setChatId(update.getMessage().getChatId().toString());
                send(msg);
            } else {
                List<Map<String, String>> lessons = actions.lesson(commandArray[0].substring(1));
                for (Map<String, String> lesson : lessons) {
                    String text = this.toString(lesson);

                    SendMessage message = new SendMessage();
                    message.setReplyMarkup(this.addInline(lesson.get("name"), lesson.get("hw id")));
                    message.setText(text);
                    message.setChatId(update.getMessage().getChatId().toString());
                    send(message);
                }
            }

        }
        else if (update.hasCallbackQuery()) {
            CallbackQuery callbackquery = update.getCallbackQuery();
            if (callbackquery.getData().startsWith("done")) {
                SendMessage msg = new SendMessage();
                String[] arr = callbackquery.getData().split(" ");
                actions.markDone(Integer.parseInt(arr[1]));
                msg.setChatId(callbackquery.getMessage().getChatId().toString());
                msg.setText("Home work has marked as done");
                send(msg);
            }
            if (callbackquery.getData().startsWith("hw")) {
                SendMessage message = new SendMessage();
                message.setText("Please, fill the form and send back");
                message.setChatId(callbackquery.getMessage().getChatId().toString());
                send(message);

                SendMessage msg = new SendMessage();
                String[] arr = callbackquery.getData().split(" ");
                msg.setText(actions.addLessonForm(arr[1]));
                msg.setChatId(callbackquery.getMessage().getChatId().toString());
                send(msg);
            }
        }

    }

    private InlineKeyboardMarkup addInline (String name, String id) {
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Add new hw");
        button.setCallbackData("hw " + name);
        row.add(button);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Mark as done");
        button2.setCallbackData("done " + id);
        row.add(button2);

        rows.add(row);
        markupKeyboard.setKeyboard(rows);

        return markupKeyboard;
    }

    private String toString(Map<String, String> map) {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getKey().equals("hw id"))
                text.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
        }
        return text.toString();
    }

    private String toString(List<Lesson> lessons) {
        String text = "";
        for (Lesson lesson : lessons) {
            if (lesson.getName() != null)
                text += "Name:\t" + lesson.getName() + "\n";
            if (lesson.getTeacher() != null)
                text += "Teacher:\t" + lesson.getTeacher() + "\n";
            if (lesson.getHw() != null)
                text += "HW:\t" + lesson.getHw() + "\n";
            if (lesson.getDate() != null)
                text += "Date:\t" + lesson.getDate() + "\n";
            text += "--------------------------------------------------------------\n\n";
        }
        return text;
    }

    private String toString(Lesson lesson) {
        StringBuilder text = new StringBuilder();
        if (lesson.getName() != null)
            text.append("Name:\t").append(lesson.getName()).append("\n");
        if (lesson.getTeacher() != null)
            text.append("Teacher:\t").append(lesson.getTeacher()).append("\n");
        if (lesson.getHw() != null)
            text.append("HW:\t").append(lesson.getHw()).append("\n");
        if (lesson.getDate() != null )
            text.append("Date:\t").append(lesson.getDate()).append("\n");

        return text.toString();
    }

    private void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public String getBotUsername() {
        return "enruubot";
    }

    public String getBotToken() {
        return "1070759418:AAGqIcvEke1SuOfvdWn7nBQKE5Nn4sxbdPM";
    }
}
