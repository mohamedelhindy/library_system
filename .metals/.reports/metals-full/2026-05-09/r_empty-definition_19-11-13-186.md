error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java:java/sql/PreparedStatement#executeUpdate().
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java
empty definition using pc, found symbol in pc: java/sql/PreparedStatement#executeUpdate().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 5732
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java
text:
```scala
package library_system.pages;

import library_system.database.DBConnection;
import library_system.utils.Navigator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends BasePage {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton signupButton;
    private JButton loginButton;

    public SignupPage() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        JLabel title = createTitle("Create Account", 46);
        JLabel subtitle = createSubtitle("Join the Library System and manage your books easily");

        JPanel card = createGlassCard(460, 430);
        card.setLayout(null);

        JLabel usernameLabel = createFormLabel("Username");
        usernameLabel.setBounds(65, 25, 330, 25);
        card.add(usernameLabel);

        usernameField = createTextField();
        usernameField.setBounds(65, 55, 330, 36);
        card.add(usernameField);

        JLabel emailLabel = createFormLabel("Email");
        emailLabel.setBounds(65, 105, 330, 25);
        card.add(emailLabel);

        emailField = createTextField();
        emailField.setBounds(65, 135, 330, 36);
        card.add(emailField);

        JLabel passwordLabel = createFormLabel("Password");
        passwordLabel.setBounds(65, 185, 330, 25);
        card.add(passwordLabel);

        passwordField = createPasswordField();
        passwordField.setBounds(65, 215, 330, 36);
        card.add(passwordField);

        JLabel roleLabel = createFormLabel("Role");
        roleLabel.setBounds(65, 265, 330, 25);
        card.add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});
        roleBox.setBounds(65, 295, 330, 36);
        card.add(roleBox);

        signupButton = createPrimaryButton("Sign Up");
        signupButton.setBounds(65, 350, 330, 40);
        card.add(signupButton);

        loginButton = createLinkButton("Already have an account? Login");
        loginButton.setBounds(105, 395, 250, 25);
        card.add(loginButton);

        add(title);
        add(Box.createVerticalStrut(15));
        add(subtitle);
        add(Box.createVerticalStrut(25));
        add(card);

        add(Box.createVerticalGlue());

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

        if (signupUser(username, email, password, role)) {
            JOptionPane.showMessageDialog(this, "Account created successfully");
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

            int rows = statement.executeUpdate();

            if (rows > 0) {
                ResultSet keys = statement.getGeneratedKeys();

                if (keys.next()) {
                    int userId = keys.getInt(1);
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
            statement.execute@@Update();
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/sql/PreparedStatement#executeUpdate().