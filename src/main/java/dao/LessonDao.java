package dao;

import model.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> findAll();

    List<Lesson> findByName(String name);

    Lesson findHWByID(int id);

    Lesson save(Lesson lesson);

    List<Lesson> update(Lesson lesson);

    void markDone(int id);

    void delete(int id);

}
