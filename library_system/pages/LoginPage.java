package library_system.pages;

<<<<<<< HEAD
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.models.User;
import library_system.services.UserService;
=======
import library_system.database.DBConnection;
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d
import library_system.utils.Navigator;

<<<<<<< HEAD
public class LoginPage extends JPanel {
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

        subtitle = new JLabel(
            "Login to continue using the Library System"
        );

        errorLabel = new JLabel(" ");

        emailField = new JTextField();

        passwordField = new JPasswordField();

        loginBtn = new JButton("Log In");

        signupBtn = new JButton(
            "Don't have an account? Sign Up"
        );
    }

    private void styleComponents() {

        setOpaque(false);

        title.setFont(new Font("Segoe UI", Font.BOLD, 42));

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

        field.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(210, 225, 245),
                    1
                ),
                new EmptyBorder(8, 12, 8, 12)
            )
        );

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

        button.setBackground(new Color(0, 0, 0, 0));

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

        cardPanel.setLayout(
            new BoxLayout(cardPanel, BoxLayout.Y_AXIS)
        );

        cardPanel.setOpaque(false);

        cardPanel.setBorder(
            new EmptyBorder(35, 45, 35, 45)
        );

        cardPanel.setPreferredSize(
            new Dimension(520, 500)
        );

        cardPanel.add(title);

        cardPanel.add(Box.createVerticalStrut(8));

        cardPanel.add(subtitle);

        cardPanel.add(Box.createVerticalStrut(30));

        cardPanel.add(createLabel("Email"));

        cardPanel.add(Box.createVerticalStrut(6));

        cardPanel.add(emailField);

        cardPanel.add(Box.createVerticalStrut(16));

        cardPanel.add(createLabel("Password"));

        cardPanel.add(Box.createVerticalStrut(6));

        cardPanel.add(passwordField);

        cardPanel.add(Box.createVerticalStrut(14));

        cardPanel.add(errorLabel);

        cardPanel.add(Box.createVerticalStrut(18));

        cardPanel.add(loginBtn);

        cardPanel.add(Box.createVerticalStrut(15));

        cardPanel.add(signupBtn);

        add(cardPanel);
    }

    private void addListeners() {
        signupBtn.addActionListener(
            e -> Navigator.navigateTo(new SignupPage())
        );

        loginBtn.addActionListener(e -> {

            String email =
                emailField.getText().trim();

            String password =
                new String(passwordField.getPassword())
                    .trim();

            if (email.isEmpty() || password.isEmpty()) {

                errorLabel.setText(
                    "Please fill in all fields."
                );

                return;
            }

            User user = UserService.login(email, password);

            if (user != null) {
                Session.login(user);
                Navigator.navigateTo(
                    new Dashboard()
                );

            } else {
                errorLabel.setText(
                    "Invalid email or password."
                );
=======
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends BasePage {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {
        setLayout(new GridBagLayout());

        JPanel card = createGlassCard(520, 420);
        card.setLayout(null);

        JLabel title = createTitle("Login", 46);
        title.setBounds(0, 30, 520, 55);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(title);

        JLabel subtitle = createSubtitle("Welcome back to Library System");
        subtitle.setBounds(0, 85, 520, 30);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(subtitle);

        JLabel emailLabel = createFormLabel("Email");
        emailLabel.setBounds(100, 145, 320, 25);
        card.add(emailLabel);

        emailField = createTextField();
        emailField.setBounds(100, 175, 320, 38);
        card.add(emailField);

        JLabel passwordLabel = createFormLabel("Password");
        passwordLabel.setBounds(100, 230, 320, 25);
        card.add(passwordLabel);

        passwordField = createPasswordField();
        passwordField.setBounds(100, 260, 320, 38);
        card.add(passwordField);

        loginButton = createPrimaryButton("Login");
        loginButton.setBounds(100, 325, 320, 40);
        card.add(loginButton);

        signupButton = createLinkButton("Create new account");
        signupButton.setBounds(140, 375, 240, 30);
        card.add(signupButton);

        add(card);

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> Navigator.navigateTo(new SignupPage()));
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
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
<<<<<<< HEAD

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        GradientPaint background =
            new GradientPaint(0, 0, new Color(20, 30, 48), width, height, new Color(36, 59, 85));

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

        g2d.setColor(
            new Color(255, 255, 255, 30)
        );

        g2d.fillRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);
        g2d.setColor(new Color(255, 255, 255, 45));
        g2d.drawRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);
        g2d.dispose();
    }
=======
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d
}