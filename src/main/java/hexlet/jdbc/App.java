package hexlet.jdbc;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");
        String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(255), phone VARCHAR(255))";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();

        String sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789')";
        Statement statement2 = connection.createStatement();
        statement2.executeUpdate(sql2);
        statement.close();

        String sql3 = "SELECT * FROM users";
        Statement statement3 = connection.createStatement();
        ResultSet resultSet = statement3.executeQuery(sql3);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        connection.close();
    }
}
