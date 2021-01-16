package dao;

import model.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> findAll();

    Lesson findByName(String name);

    Lesson save(Lesson lesson);

    Lesson update(Lesson lesson);

    void delete(Integer id);

}
