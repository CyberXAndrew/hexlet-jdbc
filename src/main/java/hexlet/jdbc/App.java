package hexlet.jdbc;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }

            String sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789')";
            try (Statement statement2 = connection.createStatement()) {
                statement2.executeUpdate(sql2);
            }

            String sql3 = "SELECT * FROM users";
            try (Statement statement3 = connection.createStatement()) {
                ResultSet resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
        }
    }
}
