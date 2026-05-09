package library_system.pages;

import library_system.database.DBConnection;
import library_system.utils.Navigator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {
        setLayout(null);
        setBackground(new Color(25, 45, 65));

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(55, 72, 92));
        card.setBounds(200, 90, 400, 400);
        add(card);

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setForeground(Color.WHITE);
        title.setBounds(145, 45, 150, 50);
        card.add(title);

        JLabel subtitle = new JLabel("Welcome back to Library System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(95, 95, 250, 25);
        card.add(subtitle);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 13));
        emailLabel.setBounds(50, 145, 300, 25);
        card.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(50, 170, 300, 35);
        card.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passwordLabel.setBounds(50, 220, 300, 25);
        card.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 245, 300, 35);
        card.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 305, 300, 35);
        loginButton.setBackground(new Color(90, 170, 220));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        card.add(loginButton);

        signupButton = new JButton("Don't have an account? Sign Up");
        signupButton.setBounds(50, 345, 300, 25);
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        card.add(signupButton);

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

                JOptionPane.showMessageDialog(this, "Welcome " + name + " - " + role);

                Navigator.navigateTo(new Dashboard());

            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Login failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}