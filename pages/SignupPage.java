package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.Main;
import library_system.components.LabelField;
import library_system.components.StyledButton;
import library_system.models.Admin;
import library_system.models.Librarian;
import library_system.models.Member;
import library_system.models.User;
import library_system.utils.Navigator;

public class SignupPage extends JPanel {

    private JLabel title;
    private JLabel errorLabel;

    private LabelField usernameLabel;
    private LabelField emailLabel;
    private LabelField passwordLabel;
    private LabelField roleLabel;

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleDropdown;

    private StyledButton signupBtn;
    private StyledButton loginBtn;

    public SignupPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title      = new JLabel("Sign Up");
        errorLabel = new JLabel(" ");

        usernameLabel = new LabelField("Username:");
        emailLabel    = new LabelField("Email:");
        passwordLabel = new LabelField("Password:");
        roleLabel     = new LabelField("Role:");

        usernameField = new JTextField(20);
        emailField    = new JTextField(20);
        passwordField = new JPasswordField(20);
        roleDropdown  = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});

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

        Dimension fieldSize = new Dimension(300, 35);
        usernameField.setMaximumSize(fieldSize);
        usernameField.setAlignmentX(CENTER_ALIGNMENT);

        emailField.setMaximumSize(fieldSize);
        emailField.setAlignmentX(CENTER_ALIGNMENT);

        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(CENTER_ALIGNMENT);

        roleDropdown.setMaximumSize(fieldSize);
        roleDropdown.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(30));

        add(usernameLabel);
        add(usernameField);
        add(Box.createVerticalStrut(15));

        add(emailLabel);
        add(emailField);
        add(Box.createVerticalStrut(15));

        add(passwordLabel);
        add(passwordField);
        add(Box.createVerticalStrut(15));

        add(roleLabel);
        add(roleDropdown);
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

    public static boolean signup(String name, String email, String password, String role) {
        for (User user : Main.library.getUsersList()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }

        int newId = Main.library.getUsersList().size() + 1;

        switch (role) {
            case "Admin" -> Main.library.getUsersList().add(new Admin(newId, name, email, password));
            case "Librarian" -> Main.library.getUsersList().add(new Librarian(newId, name, email, password));
            default -> Main.library.getUsersList().add(new Member(newId, name, email, password));
        }

        return true;
    }

    private void addListeners() {
        loginBtn.addActionListener(e -> Navigator.navigateTo(new LoginPage()));

        signupBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role = (String) roleDropdown.getSelectedItem();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            boolean success = SignupPage.signup(username, email, password, role);
            

            if (success) {
                Navigator.navigateTo(new LoginPage());
            } else {
                errorLabel.setText("Email already exists.");
            }
        });
    }
}
