package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.LabelField;
import library_system.components.TextField;
import library_system.components.StyledButton;

import library_system.utils.Navigator;;

public class LoginPage extends JPanel {

    private JLabel title;

    private LabelField emailLabel;
    private LabelField passwordLabel;

    private TextField emailTextField;
    private TextField passwordTextField;

    private StyledButton loginBtn;
    private StyledButton signupBtn;

    public LoginPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Login");

        emailLabel = new LabelField("Email:");
        passwordLabel = new LabelField("Password:");

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
        signupBtn.addActionListener(e -> Navigator.navigateTo(new SignupPage()));

        loginBtn.addActionListener(e -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            Navigator.navigateTo(new Dashboard());

            // TODO: Implement backend Logic
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
        });
    }
}