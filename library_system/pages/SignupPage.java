package library_system.pages;

import library_system.database.DBConnection;
import library_system.utils.Navigator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends JPanel {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton signupButton;
    private JButton loginButton;

    public SignupPage() {
        setLayout(null);
        setBackground(new Color(25, 45, 65));

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(55, 72, 92));
        card.setBounds(200, 50, 400, 480);
        add(card);

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setBounds(65, 35, 300, 45);
        card.add(title);

        JLabel subtitle = new JLabel("Join the Library System and manage your books easily");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(45, 85, 330, 25);
        card.add(subtitle);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        usernameLabel.setBounds(50, 130, 300, 25);
        card.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 155, 300, 35);
        card.add(usernameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 13));
        emailLabel.setBounds(50, 205, 300, 25);
        card.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(50, 230, 300, 35);
        card.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passwordLabel.setBounds(50, 280, 300, 25);
        card.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 305, 300, 35);
        card.add(passwordField);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        roleLabel.setBounds(50, 355, 300, 25);
        card.add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});
        roleBox.setBounds(50, 380, 300, 35);
        card.add(roleBox);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(50, 430, 300, 35);
        signupButton.setBackground(new Color(90, 170, 220));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        card.add(signupButton);

        loginButton = new JButton("Already have an account? Log In");
        loginButton.setBounds(50, 465, 300, 25);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        card.add(loginButton);

        signupButton.addActionListener(e -> signup());
        loginButton.addActionListener(e -> Navigator.navigateTo(new LoginPage()));
    }

    private void signup() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        if (emailExists(email)) {
            JOptionPane.showMessageDialog(this, "This email already exists");
            return;
        }

        boolean success = signupUser(username, email, password, role);

        if (success) {
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            Navigator.navigateTo(new LoginPage());
        } else {
            JOptionPane.showMessageDialog(this, "Signup failed");
        }
    }

    private boolean emailExists(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            return result.next();

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean signupUser(String username, String email, String password, String role) {
        String sql = "INSERT INTO users (name, email, password_hash, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, role);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    insertIntoRoleTable(connection, userId, role);
                }

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void insertIntoRoleTable(Connection connection, int userId, String role) throws Exception {
        String sql;

        if (role.equals("Admin")) {
            sql = "INSERT INTO admins (user_id) VALUES (?)";
        } else if (role.equals("Librarian")) {
            sql = "INSERT INTO librarians (user_id) VALUES (?)";
        } else {
            sql = "INSERT INTO members (user_id) VALUES (?)";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }
}