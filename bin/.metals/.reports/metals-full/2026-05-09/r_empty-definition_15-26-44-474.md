error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java:library_system/database/DBConnection#getConnection().
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
empty definition using pc, found symbol in pc: library_system/database/DBConnection#getConnection().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 4320
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

import library_system.Main;
import library_system.components.LabelField;
import library_system.components.StyledButton;
import library_system.database.DBConnection;
import library_system.models.User;
import library_system.utils.Navigator;
import library_system.utils.Session;

public class LoginPage extends JPanel {

    private JLabel title;
    private JLabel errorLabel;

    private LabelField emailLabel;
    private LabelField passwordLabel;

    private JTextField emailField;
    private JPasswordField passwordField;

    private StyledButton loginBtn;
    private StyledButton signupBtn;

    public LoginPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title     = new JLabel("Login");
        errorLabel = new JLabel(" ");

        emailLabel    = new LabelField("Email:");
        passwordLabel = new LabelField("Password:");

        emailField    = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginBtn  = new StyledButton("Log In");
        signupBtn = new StyledButton("Sign Up");
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(new Color(220, 50, 50));
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        emailField.setMaximumSize(new Dimension(300, 35));
        emailField.setAlignmentX(CENTER_ALIGNMENT);

        passwordField.setMaximumSize(new Dimension(300, 35));
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(40));

        add(emailLabel);
        add(emailField);
        add(Box.createVerticalStrut(20));

        add(passwordLabel);
        add(passwordField);
        add(Box.createVerticalStrut(10));

        add(errorLabel);
        add(Box.createVerticalStrut(20));

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setBackground(Color.BLACK);
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(loginBtn);
        buttonRow.add(Box.createHorizontalStrut(20));
        buttonRow.add(signupBtn);
        buttonRow.add(Box.createHorizontalGlue());

        add(buttonRow);
        add(Box.createVerticalGlue());

        addListeners();
    }

    public static User login(String email, String password) {
        for (User user : Main.library.getUsersList()) {
            if (user.login(email, password)) {
                return user;
            }
        }
        
        return null;
    }

    private void addListeners() {
        signupBtn.addActionListener(e -> Navigator.navigateTo(new SignupPage()));

        loginBtn.addActionListener(e -> {
            String email    = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            User user = LoginPage.login(email, password);

            if (user != null) {
                Session.login(user);
                Navigator.navigateTo(new Dashboard());
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });
    }



    public static library_system.models.User login(String email, String password) {
    String sql = "SELECT user_id, name, email, password_hash, role FROM users WHERE email = ? AND password_hash = ?";

    try (Connection connection = DBConnection.getCon@@nection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);
        statement.setString(2, password);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password_hash");
                String role = resultSet.getString("role");

                if (role.equalsIgnoreCase("Admin")) {
                    return new library_system.models.Admin(userId, name, userEmail, userPassword);
                } else if (role.equalsIgnoreCase("Librarian")) {
                    return new library_system.models.Librarian(userId, name, userEmail, userPassword);
                } else {
                    return new library_system.models.Member(userId, name, userEmail, userPassword);
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: library_system/database/DBConnection#getConnection().