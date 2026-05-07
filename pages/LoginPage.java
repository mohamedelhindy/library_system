package library_system.pages;

import javax.swing.*;
import library_system.components.FormField;
import library_system.components.StyledButton;
import library_system.components.ButtonPanel;
import java.awt.*;

public class LoginPage extends JPanel {
    private JLabel title;
    private FormField emailField;
    private FormField passwordField;
    private StyledButton loginBtn;
    private StyledButton signupBtn;

    public LoginPage() {
        title = new JLabel("Login");
        emailField = new FormField("Email:");
        passwordField = new FormField("Password:");
        signupBtn = new StyledButton("Sign Up");
        loginBtn = new StyledButton("Log In");

        emailField.setAlignmentX(CENTER_ALIGNMENT);
        passwordField.setAlignmentX(CENTER_ALIGNMENT);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(40));
        add(emailField);
        add(Box.createVerticalStrut(30));
        add(passwordField);
        add(Box.createVerticalStrut(40));
        
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setBackground(Color.BLACK);
        buttonRow.setMaximumSize(new Dimension(900, 100));
        buttonRow.setAlignmentX(CENTER_ALIGNMENT);
        
        loginBtn.setMaximumSize(new Dimension(500, 100));
        signupBtn.setMaximumSize(new Dimension(500, 100));
        
        buttonRow.add(Box.createHorizontalGlue());
        buttonRow.add(loginBtn);
        buttonRow.add(Box.createHorizontalStrut(20));
        buttonRow.add(signupBtn);
        buttonRow.add(Box.createHorizontalGlue());
        
        add(buttonRow);
        add(Box.createVerticalGlue());

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
        });
    }
}
