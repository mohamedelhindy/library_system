package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.LabelField;
import library_system.components.TextField;
import library_system.components.StyledButton;

import library_system.utils.Navigator;

public class SignupPage extends JPanel {

    private JLabel title;

    private LabelField usernameLabel;
    private LabelField emailLabel;
    private LabelField passwordLabel;

    private TextField usernameTextField;
    private TextField emailTextField;
    private TextField passwordTextField;

    private StyledButton signupBtn;
    private StyledButton loginBtn;

    public SignupPage() {
        initializeComponents();

        styleComponents();

        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Sign Up");

        usernameLabel = new LabelField("Username:");
        emailLabel = new LabelField("Email:");
        passwordLabel = new LabelField("Password:");

        usernameTextField = new TextField();
        emailTextField = new TextField();
        passwordTextField = new TextField();

        loginBtn = new StyledButton("Log In");
        signupBtn = new StyledButton("Sign Up");
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);

        add(Box.createVerticalStrut(40));

        add(usernameLabel);
        add(usernameTextField);

        add(Box.createVerticalStrut(20));

        add(emailLabel);
        add(emailTextField);

        add(Box.createVerticalStrut(20));

        add(passwordLabel);
        add(passwordTextField);

        add(Box.createVerticalStrut(40));

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

    private void addListeners() {
        loginBtn.addActionListener(e -> Navigator.navigateTo(new LoginPage()));

        signupBtn.addActionListener(e -> {
            String username = usernameTextField.getText();
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            // TODO: Implement backend Logic
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
        });
    }
}