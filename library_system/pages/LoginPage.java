package library_system.pages;

import library_system.database.DBConnection;
import library_system.utils.Navigator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends BasePage {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {
        setLayout(new GridBagLayout());

        JPanel card = createGlassCard(520, 420);
        card.setLayout(null);

        JLabel title = createTitle("Login", 46);
        title.setBounds(0, 30, 520, 55);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(title);

        JLabel subtitle = createSubtitle("Welcome back to Library System");
        subtitle.setBounds(0, 85, 520, 30);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(subtitle);

        JLabel emailLabel = createFormLabel("Email");
        emailLabel.setBounds(100, 145, 320, 25);
        card.add(emailLabel);

        emailField = createTextField();
        emailField.setBounds(100, 175, 320, 38);
        card.add(emailField);

        JLabel passwordLabel = createFormLabel("Password");
        passwordLabel.setBounds(100, 230, 320, 25);
        card.add(passwordLabel);

        passwordField = createPasswordField();
        passwordField.setBounds(100, 260, 320, 38);
        card.add(passwordField);

        loginButton = createPrimaryButton("Login");
        loginButton.setBounds(100, 325, 320, 40);
        card.add(loginButton);

        signupButton = createLinkButton("Create new account");
        signupButton.setBounds(140, 375, 240, 30);
        card.add(signupButton);

        add(card);

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> Navigator.navigateTo(new SignupPage()));
    }

    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email and password");
            return;
        }

        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String role = result.getString("role");

                JOptionPane.showMessageDialog(this, "Welcome " + name + " (" + role + ")");
                Navigator.navigateTo(new Dashboard());

            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}