package main;

import dao.ConnectionExample;
import dao.LessonDao;
import dao.LessonDaoImpl;
import model.Lesson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Enruubot extends TelegramLongPollingBot {
    LessonDao lessonDao = new LessonDaoImpl();

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
        String command = update.getMessage().getText();
        SendMessage msg = new SendMessage();


        String[] commandArray = command.split("\n");
        if (commandArray[0].equals("/add") && commandArray.length == 1) {
            msg.setText("Use this command to add info and/or task to class in the form command, class name, task, date each in new line\n" +
                    "Ex:\n" +
                    "/add\n" +
                    "bil300\n" +
                    "To finish project\n" +
                    "2021-01-16");
        } else if (commandArray[0].equals("/add") && commandArray.length == 4) {
            Lesson lesson = new Lesson();
            lesson.setName(commandArray[1]);
            lesson.setHw(commandArray[2]);
            lesson.setDate(Date.valueOf(commandArray[3]));
            lesson = lessonDao.update(lesson);
            msg.setText(lesson.toString());

        } else if (commandArray[0].equals("/add")) {
            msg.setText("Please, follow this form:\n" +
                    "command:      /add\n" +
                    "class name:   bil300\n" +
                    "task to do:   To finish project\n" +
                    "deadline:     2021-01-16");
        } else {
            Lesson lesson;

            switch (command) {
                case "/start" : msg.setText("Welcome to BM-3 bot-recorder for homeworks\nUse commands to add new and/or to see old tasks"); break;
                case "/all" :
                    String res = "";
                    List<Lesson> lessons = lessonDao.findAll();
                    for (Lesson l : lessons) {
                        res += l.toString();
                    }
                    msg.setText(res);
                    break;

                case "/bil205" :
                    lesson = lessonDao.findByName("bil205");
                    msg.setText(lesson.toString());
                    break;

                case "/bil301" :
                    lesson = lessonDao.findByName("bil301");
                    msg.setText(lesson.toString());
                    break;

                case "/bil303" :
                    lesson = lessonDao.findByName("bil303");
                    msg.setText(lesson.toString());
                    break;

                case "/bil305" :
                    lesson = lessonDao.findByName("bil305");
                    msg.setText(lesson.toString());
                    break;

                case "/bil307" :
                    lesson = lessonDao.findByName("bil307");
                    msg.setText(lesson.toString());
                    break;

                case "/bil309" :
                    lesson = lessonDao.findByName("bil309");
                    msg.setText(lesson.toString());
                    break;

                case "/bil311" :
                    lesson = lessonDao.findByName("bil311");
                    msg.setText(lesson.toString());
                    break;

                case "/bil371" :
                    lesson = lessonDao.findByName("bil371");
                    msg.setText(lesson.toString());
                    break;

                case "/bil373" :
                    lesson = lessonDao.findByName("bil373");
                    msg.setText(lesson.toString());
                    break;

                default:
                    msg.setText("Invalid command");

            }
        }

        msg.setChatId(update.getMessage().getChatId());

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /*
        String command = update.getMessage().getText();

        SendMessage msg = new SendMessage();

        if (command.equals("/myname")) {
            System.out.println(update.getMessage().getFrom().getFirstName());
            msg.setText(update.getMessage().getFrom().getFirstName());
        } else if (command.equals("/mylastname")) {
            if (update.getMessage().getFrom().getLastName().isEmpty()) {
                msg.setText("You have no lastname");
            } else {
                msg.setText(update.getMessage().getFrom().getLastName());
            }
        } else if (command.equals("/myfullname")) {
            if (update.getMessage().getFrom().getLastName().isEmpty()) {
                msg.setText(update.getMessage().getFrom().getFirstName());
            } else {
                msg.setText(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
            }
        }
        msg.setChatId(update.getMessage().getChatId());
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
    }

    public String getBotUsername() {
        return "enruubot";
    }

    public String getBotToken() {
        return "1070759418:AAGqIcvEke1SuOfvdWn7nBQKE5Nn4sxbdPM";
    }
}
