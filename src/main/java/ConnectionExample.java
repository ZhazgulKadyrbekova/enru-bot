import java.sql.*;
import java.sql.Date;

public class ConnectionExample {

    private static Connection connection = null;
    private static PreparedStatement statement = null;

    public static void main(String[] args) {
        try {
            System.out.println("Make JDBC Connection");
            makeConnection();
            System.out.println("------------------------------------------------------");

            System.out.println("Add data");
//            addData("bil205", "KASIM BARIKTABASOV");
//            addData("bil301", "BAKIT SARSEMBAYEV");
//            addData("bil303", "CINARA CUMABAYEVA");
//            addData("bil305", "BAKIT SARSEMBAYEV");
//            addData("bil307", "MEHMET SELiM ELMALI");
//            addData("bil309", "AYCARKIN SAiT KIZI");
//            addData("bil311", "RAYIMBEK SULTANOV");
//            addData("bil371", "KASIM BARIKTABASOV");
//            addData("bil373", "AYCARKIN SAiT KIZI");
            System.out.println("------------------------------------------------------");

            System.out.println("Get data by id");
//            getDataById(1);
            System.out.println("------------------------------------------------------");

            System.out.println("Get all data ");
            getAllData();
            System.out.println("------------------------------------------------------");

            System.out.println("Update data");
//            updateData("bil305", "subd on mysql");
            System.out.println("------------------------------------------------------");

            System.out.println("Get all data ");
//            getAllData();
            System.out.println("------------------------------------------------------");

            System.out.println("Delete data");
//            deleteDataById(2);
//            deleteDataByName("bil303");
            System.out.println("------------------------------------------------------");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void makeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/enruubot", "root", "Zxcvbnm00_");

            if (connection == null)
                System.out.println("Failed to connect to db");
        } catch (SQLException e) {
            System.out.println("Failed to connect to db");
            e.printStackTrace();
            return;
        }
    }

    private static void addData(String name, String teacher) {
        try {
            makeConnection();
            String insertQuery = "insert into lessons(name, teacher) values (?, ?)";
            statement = connection.prepareStatement(insertQuery);
            statement.setString(1, name);
//            statement.setString(2, hw);
            statement.setString(2, teacher);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDataByName(String name) {
        try {
            String result = "";
            makeConnection();
            String query = "select * from lessons where name = ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();

            String hw = "", teacher = "";
            if (resultSet.next())
                name = resultSet.getString("name");
                hw = resultSet.getString("hw");
                Date date = resultSet.getDate("data");
                teacher = resultSet.getString("teacher");

            result += name + "\t" + hw + "\t" + teacher + "\t" + date + "\n";

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getAllData() {
        try {
            String result = "";
            makeConnection();
            String query = "select * from lessons";
            statement = connection.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String hw = set.getString("hw");
                Date date = set.getDate("data");
                String teacher = set.getString("teacher");

                result += "\t" + name + "\t" + hw + "\t" + date + '\n';
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean updateData(String name, String hw, java.sql.Date date) {
        try {
            makeConnection();
            String query = "update lessons set hw = ?, date = ? where name = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, hw);
            statement.setDate(2, date);
            statement.setString(3, name);

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private static void deleteDataById(int id) {
        try {
            makeConnection();
            String deleteQuery = "delete from lessons where id = ?";
            statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteDataByName(String name) {
        try {
            makeConnection();
            String deleteQuery = "delete from lessons where name = ?";
            statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, name);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
