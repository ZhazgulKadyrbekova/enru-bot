package main;

import dao.LessonDao;
import dao.LessonDaoImpl;
import model.Lesson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actions {
    LessonDao lessonDao = new LessonDaoImpl();

    public String start() {
        return "Welcome to BM-3 bot-recorder for homeworks\nUse commands" +
                " to add new and/or to see old tasks";
    }
    public String addDescription() {
        return "Use this command to add info and/or task to class in the form command, " +
                "class name, task, date each in new line\n" +
                "Ex:\n" +
                "/add\n" +
                "bil300\n" +
                "To finish project\n" +
                "2021-01-16";
    }
    public List<Lesson> addLesson(String name, String hw, String date) {
        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setHw(hw);
        lesson.setDate(Date.valueOf(date));
        return lessonDao.update(lesson);
    }
    public String addLessonForm(String name) {
        return "/add\n" +
                        name + "\n" +
                        "hw description\n" +
                        "date in format [yyyy-mm-dd]";
    }
    public String addInvalidCommand() {
        return "Please, follow the form to add new task or be sure to enter valid lesson name";
    }
    public List<Lesson> all() {
        return lessonDao.findAll();
    }
    public List<Map<String, String>> lesson(String name) {
        List<Map<String, String>> result = new ArrayList<>();
        List<Lesson> lessons = lessonDao.findByName(name);
        for (Lesson lesson : lessons) {
            Map<String, String> res = new HashMap<>();
            res.put("name", lesson.getName());
            res.put("teacher", lesson.getTeacher());
            res.put("hw", lesson.getHw());
            res.put("date", String.valueOf(lesson.getDate()));
            res.put("hw id", String.valueOf(lesson.getHwId()));
            result.add(res);
        }
        return result;
    }
    public void markDone(int id) {
        lessonDao.markDone(id);
    }

}
