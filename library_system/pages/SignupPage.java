package library_system.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.services.UserService;
import library_system.utils.Navigator;

public class SignupPage extends JPanel {

    private JLabel title;
    private JLabel subtitle;
    private JLabel errorLabel;

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleDropdown;

    private JButton signupBtn;
    private JButton loginBtn;

    public SignupPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
        addListeners();
    }

    private void initializeComponents() {
        title = new JLabel("Create Account");
        subtitle = new JLabel("Join the Library System and manage your books easily");
        errorLabel = new JLabel(" ");

        usernameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        roleDropdown = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});

        signupBtn = new JButton("Sign Up");
        loginBtn = new JButton("Already have an account? Log In");
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

        styleTextField(usernameField);
        styleTextField(emailField);
        styleTextField(passwordField);

        roleDropdown.setMaximumSize(new Dimension(360, 42));
        roleDropdown.setPreferredSize(new Dimension(360, 42));
        roleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        roleDropdown.setBackground(Color.WHITE);
        roleDropdown.setForeground(new Color(30, 40, 60));
        roleDropdown.setAlignmentX(CENTER_ALIGNMENT);

        styleMainButton(signupBtn);
        styleLinkButton(loginBtn);
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
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(35, 45, 35, 45));
        cardPanel.setPreferredSize(new Dimension(520, 610));

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(8));
        cardPanel.add(subtitle);
        cardPanel.add(Box.createVerticalStrut(30));

        cardPanel.add(createLabel("Username"));
        cardPanel.add(Box.createVerticalStrut(6));
        cardPanel.add(usernameField);
        cardPanel.add(Box.createVerticalStrut(16));

        cardPanel.add(createLabel("Email"));
        cardPanel.add(Box.createVerticalStrut(6));
        cardPanel.add(emailField);
        cardPanel.add(Box.createVerticalStrut(16));

        cardPanel.add(createLabel("Password"));
        cardPanel.add(Box.createVerticalStrut(6));
        cardPanel.add(passwordField);
        cardPanel.add(Box.createVerticalStrut(16));

        cardPanel.add(createLabel("Role"));
        cardPanel.add(Box.createVerticalStrut(6));
        cardPanel.add(roleDropdown);
        cardPanel.add(Box.createVerticalStrut(14));

        cardPanel.add(errorLabel);
        cardPanel.add(Box.createVerticalStrut(18));

        cardPanel.add(signupBtn);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(loginBtn);

        add(cardPanel);
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

            if (!email.contains("@") || !email.contains(".")) {
                errorLabel.setText("Please enter a valid email.");
                return;
            }

            if (password.length() < 6) {
                errorLabel.setText("Password must be at least 6 characters.");
                return;
            }

            if (UserService.emailExists(email)) {
                errorLabel.setText("Email already exists.");
                return;
            }

            boolean success = UserService.signup(username, email, password, role);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Account created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                Navigator.navigateTo(new LoginPage());
            } else {
                errorLabel.setText("Signup failed. Please try again.");
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
        int cardHeight = 650;
        int cardX = width / 2 - cardWidth / 2;
        int cardY = height / 2 - cardHeight / 2;

        g2d.setColor(new Color(255, 255, 255, 30));
        g2d.fillRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        g2d.setColor(new Color(255, 255, 255, 45));
        g2d.drawRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        g2d.dispose();
    }
}