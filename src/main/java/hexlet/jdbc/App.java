package hexlet.jdbc;

import hexlet.jdbc.dao.UserDAO;
import hexlet.jdbc.model.User;

import java.sql.*;
import java.util.Optional;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }

            UserDAO userDAO = new UserDAO(connection);
            User user = new User("Tomas", "123456");
            System.out.println(user.getId());
            userDAO.save(user);
            System.out.println(user.getId());
            Optional<User> optional = userDAO.findById(user.getId());
            if (optional.isPresent()) {
                User userFromDB = optional.get();
                System.out.println(userFromDB.getId() == user.getId());
            }

            userDAO.deleteById(user.getId());
        }
    }
}
