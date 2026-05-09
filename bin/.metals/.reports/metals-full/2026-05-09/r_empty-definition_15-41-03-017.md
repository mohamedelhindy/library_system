error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java:library_system/services/UserService#
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
empty definition using pc, found symbol in pc: library_system/services/UserService#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 4683
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
text:
```scala
package library_system.pages;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import library_system.database.DBConnection;
import library_system.services.UserService;
import library_system.utils.Navigator;

public class LoginPage extends JPanel {

    private JLabel title;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;

    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
        addListeners();
    }

    private void initializeComponents() {
        title = new JLabel("Log In");

        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        messageLabel = new JLabel(" ");

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Log In");
        signupButton = new JButton("Sign Up");
    }

    private void styleComponents() {
        setBackground(new Color(20, 30, 48));

        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setAlignmentX(CENTER_ALIGNMENT);

        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);

        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(255, 120, 120));
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        Dimension fieldSize = new Dimension(300, 35);

        emailField.setMaximumSize(fieldSize);
        emailField.setAlignmentX(CENTER_ALIGNMENT);

        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(CENTER_ALIGNMENT);

        loginButton.setMaximumSize(new Dimension(140, 40));
        signupButton.setMaximumSize(new Dimension(140, 40));

        loginButton.setFocusPainted(false);
        signupButton.setFocusPainted(false);

        loginButton.setBackground(new Color(93, 173, 226));
        loginButton.setForeground(Color.WHITE);

        signupButton.setBackground(new Color(70, 90, 115));
        signupButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(36, 59, 85));
        card.setMaximumSize(new Dimension(450, 430));
        card.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        card.add(title);
        card.add(Box.createVerticalStrut(30));

        card.add(emailLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(emailField);
        card.add(Box.createVerticalStrut(18));

        card.add(passwordLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(15));

        card.add(messageLabel);
        card.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(signupButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalGlue());

        card.add(buttonPanel);

        add(card);
        add(Box.createVerticalGlue());
    }

    private void addListeners() {
        signupButton.addActionListener(e -> {
            Navigator.navigateTo(new SignupPage());
        });

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }

            boolean success = UserSe@@rvice.login(email, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                Navigator.navigateTo(new Dashboard());
            } else {
                messageLabel.setText("Invalid email or password.");
            }
        });
    }



    public static boolean login(String email, String password) {
    String sql = "SELECT user_id FROM users WHERE email = ? AND password_hash = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: library_system/services/UserService#