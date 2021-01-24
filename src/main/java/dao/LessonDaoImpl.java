package dao;

import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao{

    private static final String FIND_ALL_STATEMENT = "select description, date, done, lesson_name from hws where done = false";
    private static final String FIND_BY_NAME_STATEMENT = "select l.teacher, h.id, h.description, h.date, h.done " +
            "from lessons l, hws h where l.name = ? and h.done = false and l.name = h.lesson_name";
    private static final String FIND_NAME_BY_ID_STATEMENT = "select name from lessons where id = ?";
    private static final String FIND_HW_BY_ID_STATEMENT = "select lesson_name, description, date from hws where id = ?";
    private static final String SAVE_LESSON_STATEMENT = "insert into lessons(name, teacher) values (?,?)";
    private static final String SAVE_HW_STATEMENT = "INSERT INTO hws(description, date, done, lesson_name) values (?, ?, ?, ?);\n";
    private static final String MARK_DONE_STATEMENT = "update hws set done = true where id = ?";
    private static final String DELETE_STATEMENT = "delete from lessons where id = ?";
    private static final String DELETE_HWS_STATEMENT = "delete from hws where lesson_name = ?";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:postgresql://ec2-54-156-73-147.compute-1.amazonaws.com:5432/" +
                            "d3hpob7gtq52vr?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
                    "sehskmunuokrmc", "161453cfdaa0d1d7761e84aed166f1c8cbd5671a65b1f8d3e842330dd962632c");
        } catch (SQLException e) {
            System.err.println("Failed to connect to db");
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Exception while closing connection");
        }
    }

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        Connection connection = getConnection();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setName(resultSet.getString(4));
                lesson.setHw(resultSet.getString(1));
                lesson.setDate(resultSet.getDate(2));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            System.err.println("Exception while getting lessons: " + e.getMessage());
            close(connection);
        }
        close(connection);
        return lessons;
    }

    @Override
    public List<Lesson> findByName(String name) {
        List<Lesson> lessons = new ArrayList<>();
        Connection connection = getConnection();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_STATEMENT);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setName(name);
                lesson.setTeacher(resultSet.getString(1));
                lesson.setHwId(resultSet.getInt(2));
                lesson.setHw(resultSet.getString(3));
                lesson.setDate(resultSet.getDate(4));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            System.err.println("Exception while getting lesson by name: " + e.getMessage());
            close(connection);
        }
        close(connection);
        return lessons;
    }

    @Override
    public Lesson findHWByID(int id) {
        Lesson lesson = new Lesson();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_HW_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lesson.setName(resultSet.getString(1));
                lesson.setHw(resultSet.getString(2));
                lesson.setDate(resultSet.getDate(3));
            }
        } catch (SQLException e) {
            System.err.println("Exception while getting lesson: " + e.getMessage());
            close(connection);
        }
        return lesson;
    }

    @Override
    public Lesson save(Lesson lesson) {
        Connection connection = getConnection();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_LESSON_STATEMENT);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getTeacher());
            int id = preparedStatement.executeUpdate();
            lesson.setId(id);
        } catch (SQLException e) {
            System.err.println("Exception while saving the lesson: " + e.getMessage());
            close(connection);
        }
        return lesson;
    }

    @Override
    public List<Lesson> update(Lesson lesson) {
        List<Lesson> lessons = new ArrayList<>();
        Connection connection = getConnection();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_HW_STATEMENT);
            preparedStatement.setString(1, lesson.getHw());
            preparedStatement.setDate(2, lesson.getDate());
            preparedStatement.setBoolean(3, false);
            preparedStatement.setString(4, lesson.getName());
            preparedStatement.executeUpdate();
            lessons = findByName(lesson.getName());
        } catch (SQLException e) {
            System.err.println("Exception while updating the lesson: " + e.getMessage());
            close(connection);
        }
        return lessons;
    }

    @Override
    public void markDone(int id) {
        Connection connection = getConnection();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(MARK_DONE_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Exception when hw mark done: " + e.getMessage());
            close(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = getConnection();
        try {
            String name = "";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_NAME_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                name = set.getString(1);
            }

            preparedStatement = connection.prepareStatement(DELETE_HWS_STATEMENT);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception while deleting the lesson: " + e.getMessage());
            close(connection);
        }
    }

}
