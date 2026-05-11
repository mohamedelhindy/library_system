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

    private JTextField nameField;
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
        title     = new JLabel("Create Account");
        subtitle  = new JLabel("Join the Library System");
        errorLabel = new JLabel(" ");

        nameField     = new JTextField();
        emailField    = new JTextField();
        passwordField = new JPasswordField();
        roleDropdown  = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});

        signupBtn = new JButton("Sign Up");
        loginBtn  = new JButton("Already have an account? Log In");
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

        styleTextField(nameField);
        styleTextField(emailField);
        styleTextField(passwordField);

        roleDropdown.setMaximumSize(new Dimension(360, 42));
        roleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 15));
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
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(360, 25));
        return label;
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(30, 45, 30, 45));
        card.setPreferredSize(new Dimension(520, 580));

        card.add(title);
        card.add(Box.createVerticalStrut(6));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(24));

        card.add(makeLabel("Full Name"));
        card.add(Box.createVerticalStrut(6));
        card.add(nameField);
        card.add(Box.createVerticalStrut(14));

        card.add(makeLabel("Email"));
        card.add(Box.createVerticalStrut(6));
        card.add(emailField);
        card.add(Box.createVerticalStrut(14));

        card.add(makeLabel("Password"));
        card.add(Box.createVerticalStrut(6));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(14));

        card.add(makeLabel("Role"));
        card.add(Box.createVerticalStrut(6));
        card.add(roleDropdown);
        card.add(Box.createVerticalStrut(10));

        card.add(errorLabel);
        card.add(Box.createVerticalStrut(16));

        card.add(signupBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(loginBtn);

        add(card);
    }

    private void addListeners() {
        loginBtn.addActionListener(e -> Navigator.navigateTo(new LoginPage()));

        signupBtn.addActionListener(e -> {
            String name     = nameField.getText().trim();
            String email    = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role     = (String) roleDropdown.getSelectedItem();

            if (name.isEmpty()) {
                errorLabel.setText("Name is required.");
                return;
            }
            if (email.isEmpty() || !email.contains("@")) {
                errorLabel.setText("Enter a valid email.");
                return;
            }
            if (password.length() < 4) {
                errorLabel.setText("Password must be at least 4 characters.");
                return;
            }

            boolean success = UserService.signup(name, email, password, role);

            if (success) {
                Navigator.navigateTo(new LoginPage());
            } else {
                errorLabel.setText("Email already exists.");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint bg = new GradientPaint(0, 0, new Color(20, 30, 48), getWidth(), getHeight(), new Color(36, 59, 85));
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int cw = 560, ch = 620;
        int cx = getWidth() / 2 - cw / 2;
        int cy = getHeight() / 2 - ch / 2;
        g2d.setColor(new Color(255, 255, 255, 30));
        g2d.fillRoundRect(cx, cy, cw, ch, 40, 40);
        g2d.setColor(new Color(255, 255, 255, 45));
        g2d.drawRoundRect(cx, cy, cw, ch, 40, 40);
        g2d.dispose();
    }
}
