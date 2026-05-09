error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java:javax/swing/JTextField#
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
empty definition using pc, found symbol in pc: javax/swing/JTextField#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1579
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
text:
```scala
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
    private JButton backButton;

    public LoginPage() {
        setLayout(null);
        setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(new Color(30, 41, 59));
        title.setBounds(345, 70, 200, 45);
        add(title);

        JLabel subtitle = new JLabel("Welcome back to Library System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(100, 116, 139));
        subtitle.setBounds(285, 115, 300, 30);
        add(subtitle);

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(240, 165, 320, 310);
        card.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        add(card);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setForeground(new Color(30, 41, 59));
        emailLabel.setBounds(35, 30, 250, 25);
        card.add(emailLabel);

        emailField = new JTextField@@();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBounds(35, 60, 250, 35);
        card.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(30, 41, 59));
        passwordLabel.setBounds(35, 110, 250, 25);
        card.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(35, 140, 250, 35);
        card.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(37, 99, 235));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(35, 200, 250, 38);
        card.add(loginButton);

        signupButton = new JButton("Create new account");
        signupButton.setFont(new Font("Arial", Font.PLAIN, 13));
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setForeground(new Color(37, 99, 235));
        signupButton.setFocusPainted(false);
        signupButton.setBounds(55, 245, 210, 25);
        card.add(signupButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 13));
        backButton.setBounds(20, 20, 80, 30);
        backButton.setFocusPainted(false);
        add(backButton);

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> Navigator.navigateTo(new SignupPage()));
        backButton.addActionListener(e -> Navigator.navigateTo(new WelcomePage()));
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
```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/swing/JTextField#