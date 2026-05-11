error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java:library_system/pages/LoginPage#
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol library_system/pages/LoginPage#
empty definition using fallback
non-local guesses:

offset: 301
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/LoginPage.java
text:
```scala
package library_system.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.models.User;
import library_system.services.UserService;
import library_system.utils.Navigator;
import library_system.utils.Session;

public class LoginPage@@ extends JPanel {

    private JLabel title;
    private JLabel subtitle;
    private JLabel errorLabel;

    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton loginBtn;
    private JButton signupBtn;

    public LoginPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
        addListeners();
    }

    private void initializeComponents() {
        title = new JLabel("Welcome Back");
        subtitle = new JLabel("Log in to continue to your Library System");
        errorLabel = new JLabel(" ");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        loginBtn = new JButton("Log In");
        signupBtn = new JButton("Don't have an account? Sign Up");
    }

    private void styleComponents() {
        setOpaque(false);

        title.setFont(new Font("Segoe UI", Font.BOLD, 44));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(new Color(220, 230, 255));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        errorLabel.setForeground(new Color(255, 120, 120));
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        styleTextField(emailField);
        styleTextField(passwordField);

        styleMainButton(loginBtn);
        styleLinkButton(signupBtn);
    }

    private void styleTextField(JTextField field) {
        field.setMaximumSize(new Dimension(360, 42));
        field.setPreferredSize(new Dimension(360, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(new Color(30, 40, 60));
        field.setBackground(Color.WHITE);
        field.setCaretColor(new Color(36, 59, 85));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 225, 245), 1),
                new EmptyBorder(8, 12, 8, 12)
        ));

        field.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void styleMainButton(JButton button) {
        button.setMaximumSize(new Dimension(360, 45));
        button.setPreferredSize(new Dimension(360, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(93, 173, 226));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void styleLinkButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(new Color(220, 230, 255));
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(360, 25));
        return label;
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(40, 45, 40, 45));
        cardPanel.setPreferredSize(new Dimension(520, 500));

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(8));
        cardPanel.add(subtitle);
        cardPanel.add(Box.createVerticalStrut(35));

        cardPanel.add(createLabel("Email"));
        cardPanel.add(Box.createVerticalStrut(7));
        cardPanel.add(emailField);
        cardPanel.add(Box.createVerticalStrut(18));

        cardPanel.add(createLabel("Password"));
        cardPanel.add(Box.createVerticalStrut(7));
        cardPanel.add(passwordField);
        cardPanel.add(Box.createVerticalStrut(14));

        cardPanel.add(errorLabel);
        cardPanel.add(Box.createVerticalStrut(20));

        cardPanel.add(loginBtn);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(signupBtn);

        add(cardPanel);
    }

    private void addListeners() {
        signupBtn.addActionListener(e -> Navigator.navigateTo(new SignupPage()));

        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                errorLabel.setText("Please enter a valid email.");
                return;
            }

            User user = UserService.login(email, password);

            if (user != null) {
                Session.login(user);

                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                Navigator.navigateTo(new Dashboard());
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint background = new GradientPaint(
                0, 0, new Color(20, 30, 48),
                width, height, new Color(36, 59, 85)
        );

        g2d.setPaint(background);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(255, 255, 255, 25));
        g2d.fillOval(-120, -120, 350, 350);

        g2d.setColor(new Color(93, 173, 226, 35));
        g2d.fillOval(width - 260, height - 260, 420, 420);

        int cardWidth = 560;
        int cardHeight = 540;
        int cardX = width / 2 - cardWidth / 2;
        int cardY = height / 2 - cardHeight / 2;

        g2d.setColor(new Color(255, 255, 255, 30));
        g2d.fillRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        g2d.setColor(new Color(255, 255, 255, 45));
        g2d.drawRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        g2d.dispose();
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 