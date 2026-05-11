package library_system.services;

// import library_system.Main;
import library_system.database.DBConnection;
import library_system.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public static boolean emailExists(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean signup(String name, String email, String password, String role) {
        String insertUserSql = "INSERT INTO users (name, email, password_hash, role) VALUES (?, ?, ?, ?)";
        String insertRoleSql;

        if (role.equalsIgnoreCase("Admin")) {
            insertRoleSql = "INSERT INTO admins (user_id) VALUES (?)";
        } else if (role.equalsIgnoreCase("Librarian")) {
            insertRoleSql = "INSERT INTO librarians (user_id) VALUES (?)";
        } else {
            insertRoleSql = "INSERT INTO members (user_id) VALUES (?)";
        }

        try (Connection connection = DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement userStatement = connection.prepareStatement(
                    insertUserSql,
                    Statement.RETURN_GENERATED_KEYS
            )) {
                userStatement.setString(1, name);
                userStatement.setString(2, email);
                userStatement.setString(3, password);
                userStatement.setString(4, role);

                int affectedRows = userStatement.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();
                    return false;
                }

                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);

                        try (PreparedStatement roleStatement = connection.prepareStatement(insertRoleSql)) {
                            roleStatement.setInt(1, userId);
                            roleStatement.executeUpdate();
                        }

                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");

                String name = resultSet.getString("name");

                String role = resultSet.getString("role");

                return new User(userId, name, email, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}