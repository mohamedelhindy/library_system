package library_system.pages;

import library_system.database.DBConnection;
import library_system.utils.Navigator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupPage extends JPanel {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton signupButton;
    private JButton loginButton;

    public SignupPage() {
        setLayout(null);
        setOpaque(false);

        // Card
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(44, 64, 88, 230));
        card.setBounds(360, 105, 640, 510);
        card.setBorder(BorderFactory.createEmptyBorder());
        add(card);

        // Title
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 25, 640, 55);
        card.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("Join the Library System and manage your books easily");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        subtitle.setForeground(new Color(220, 230, 255));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 78, 640, 30);
        card.add(subtitle);

        // Username Label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(155, 125, 330, 25);
        card.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBounds(155, 153, 330, 36);
        card.add(usernameField);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(155, 198, 330, 25);
        card.add(emailLabel);

        // Email Field
        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBounds(155, 226, 330, 36);
        card.add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(155, 271, 330, 25);
        card.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBounds(155, 299, 330, 36);
        card.add(passwordField);

        // Role Label
        JLabel roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBounds(155, 344, 330, 25);
        card.add(roleLabel);

        // Role ComboBox
        roleBox = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleBox.setBounds(155, 372, 330, 36);
        card.add(roleBox);

        // Signup Button
        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        signupButton.setBackground(new Color(93, 173, 226));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setBounds(155, 430, 330, 40);
        card.add(signupButton);

        // Login Button
        loginButton = new JButton("Already have an account? Login");
        loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginButton.setForeground(new Color(220, 230, 255));
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(185, 474, 270, 28);
        card.add(loginButton);

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

        boolean success = signupUser(username, email, password, role);

        if (success) {
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

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
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
            statement.executeUpdate();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

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

        g2d.dispose();
    }
}