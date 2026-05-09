error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/pages/SignupPage.java:java/io/PrintStream#println(+8).
file:///C:/Users/Gana/Downloads/library_system-main/library_system/pages/SignupPage.java
empty definition using pc, found symbol in pc: java/io/PrintStream#println(+8).
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 4610
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/pages/SignupPage.java
text:
```scala
package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.LabelField;
import library_system.components.TextField;
import library_system.components.StyledButton;

import library_system.utils.Navigator;

public class SignupPage extends JPanel {

    private JLabel title;
    private JLabel subtitle;

    private LabelField usernameLabel;
    private LabelField emailLabel;
    private LabelField passwordLabel;

    private TextField usernameTextField;
    private TextField emailTextField;
    private TextField passwordTextField;

    private StyledButton signupBtn;
    private StyledButton loginBtn;

    private JPanel formPanel;
    private JPanel buttonPanel;

    public SignupPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
        addListeners();
    }

    private void initializeComponents() {
        title = new JLabel("Welcome");
        subtitle = new JLabel("Create your library account");

        usernameLabel = new LabelField("Username");
        emailLabel = new LabelField("Email");
        passwordLabel = new LabelField("Password");

        usernameTextField = new TextField();
        emailTextField = new TextField();
        passwordTextField = new TextField();

        signupBtn = new StyledButton("Sign Up");
        loginBtn = new StyledButton("Log In");

        formPanel = new JPanel();
        buttonPanel = new JPanel();
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitle.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.setBackground(Color.BLACK);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setMaximumSize(new Dimension(420, 420));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameTextField.setMaximumSize(new Dimension(360, 45));
        emailTextField.setMaximumSize(new Dimension(360, 45));
        passwordTextField.setMaximumSize(new Dimension(360, 45));

        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setMaximumSize(new Dimension(380, 55));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        add(title);
        add(Box.createVerticalStrut(10));
        add(subtitle);

        add(Box.createVerticalStrut(45));

        formPanel.add(usernameLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(usernameTextField);

        formPanel.add(Box.createVerticalStrut(22));

        formPanel.add(emailLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(emailTextField);

        formPanel.add(Box.createVerticalStrut(22));

        formPanel.add(passwordLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(passwordTextField);

        formPanel.add(Box.createVerticalStrut(35));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(loginBtn);
        buttonPanel.add(Box.createHorizontalStrut(25));
        buttonPanel.add(signupBtn);
        buttonPanel.add(Box.createHorizontalGlue());

        formPanel.add(buttonPanel);

        add(formPanel);

        add(Box.createVerticalGlue());
    }

    private void addListeners() {
        loginBtn.addActionListener(e -> Navigator.navigateTo(new LoginPage()));

        signupBtn.addActionListener(e -> {
            String username = usernameTextField.getText();
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            System.out.printl@@n("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
        });
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/io/PrintStream#println(+8).