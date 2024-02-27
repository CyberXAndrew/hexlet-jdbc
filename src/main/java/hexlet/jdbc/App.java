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

            String sql2 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    sql2, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, "Tomas");
                preparedStatement.setString(2, "123456");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Alina");
                preparedStatement.setString(2, "654321");
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println(generatedKeys.getLong("id")); // getLong(1)
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }
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
