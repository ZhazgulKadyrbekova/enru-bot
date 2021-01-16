package dao;

import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao{

    private static final String FIND_ALL_STATEMENT = "select id, name, teacher, hw, date from lessons";
    private static final String FIND_BY_NAME_STATEMENT = "select id, name, teacher, hw, date from lessons where name = ?";
    private static final String SAVE_STATEMENT = "insert into lessons(name, teacher) values (?,?)";
    private static final String UPDATE_STATEMENT = "update lessons set hw = ?, date = ? where name = ?";
    private static final String DELETE_STATEMENT = "delete from lessons where id = ?";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/enruubot", "root", "Zxcvbnm00_");
        } catch (SQLException e) {
            System.err.println("Failed to connect to db " + e.getMessage());
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt(1));
                lesson.setName(resultSet.getString(2));
                lesson.setTeacher(resultSet.getString(3));
                lesson.setHw(resultSet.getString(4));
                lesson.setDate(resultSet.getDate(5));
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
    public Lesson findByName(String name) {
        Lesson lesson = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_STATEMENT);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lesson = new Lesson();
                lesson.setId(resultSet.getInt(1));
                lesson.setName(name);
                lesson.setTeacher(resultSet.getString(3));
                lesson.setHw(resultSet.getString(4));
                lesson.setDate(resultSet.getDate(5));
            }
        } catch (SQLException e) {
            System.err.println("Exception while getting lesson by name: " + e.getMessage());
            close(connection);
        }
        close(connection);
        return lesson;
    }

    @Override
    public Lesson save(Lesson lesson) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STATEMENT);
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
    public Lesson update(Lesson lesson) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, lesson.getHw());
            preparedStatement.setDate(2, lesson.getDate());
            preparedStatement.setString(3, lesson.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception while updating the lesson: " + e.getMessage());
            close(connection);
        }
        return lesson;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception while deleting the lesson: " + e.getMessage());
            close(connection);
        }
    }
}
