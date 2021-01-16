package main;

import dao.LessonDao;
import dao.LessonDaoImpl;
import model.Lesson;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Enruubot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("Enruubot successfully started");

/*
        LessonDao lessonDao = new LessonDaoImpl();
        List<Lesson> lessons = lessonDao.findAll();
        for (Lesson lesson1 : lessons) {
            System.out.println(lesson1);
        }

        System.out.println("*************************");

        System.out.println(lessonDao.findByName("bil303"));

        System.out.println("*************************");


        Lesson lesson = new Lesson();
        lesson.setName("bil205");
        lesson.setTeacher("KASIM BARIKTABASOV");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil301");
        lesson.setTeacher("BAKIT SARSEMBAYEV");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil303");
        lesson.setTeacher("CINARA CUMABAYEVA");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil305");
        lesson.setTeacher("BAKIT SARSEMBAYEV");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil307");
        lesson.setTeacher("MEHMET SELiM ELMALI");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil309");
        lesson.setTeacher("AYCARKIN SAiT KIZI");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil311");
        lesson.setTeacher("RAYIMBEK SULTANOV");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil371");
        lesson.setTeacher("KASIM BARIKTABASOV");
        System.out.println(lessonDao.save(lesson));

        lesson = new Lesson();
        lesson.setName("bil373");
        lesson.setTeacher("AYCARKIN SAiT KIZI");
        System.out.println(lessonDao.save(lesson));
*/


    }

}
