package library_system.panels.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.Admin;
import library_system.models.Librarian;
import library_system.models.Member;
import library_system.models.User;

public class AddUserPanel extends JPanel {

    private JLabel title;
    private JLabel statusLabel;

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleDropdown;

    private StyledButton addBtn;

    public AddUserPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Add User");
        statusLabel = new JLabel(" ");

        nameField     = new JTextField(25);
        emailField    = new JTextField(25);
        passwordField = new JPasswordField(25);
        roleDropdown  = new JComboBox<>(new String[]{"Member", "Librarian", "Admin"});
        addBtn        = new StyledButton("Add User");
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));

        for (JTextField f : new JTextField[]{nameField, emailField, passwordField}) {
            f.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            f.setBackground(new Color(30, 41, 59));
            f.setForeground(Color.WHITE);
            f.setCaretColor(Color.WHITE);
            f.setBorder(new EmptyBorder(8, 12, 8, 12));
        }

        roleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        roleDropdown.setMaximumSize(new Dimension(300, 38));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(20, 30, 48), getWidth(), getHeight(), new Color(36, 59, 85)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        content.add(title);
        content.add(Box.createVerticalStrut(25));
        content.add(makeRow("Name:", nameField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Email:", emailField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Password:", passwordField));
        content.add(Box.createVerticalStrut(12));

        JPanel roleRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        roleRow.setOpaque(false);
        JLabel rlbl = new JLabel("Role:");
        rlbl.setForeground(Color.WHITE);
        rlbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        rlbl.setPreferredSize(new Dimension(80, 30));
        roleRow.add(rlbl);
        roleRow.add(roleDropdown);
        content.add(roleRow);

        content.add(Box.createVerticalStrut(20));
        content.add(addBtn);
        content.add(Box.createVerticalStrut(10));
        content.add(statusLabel);

        add(content, BorderLayout.CENTER);
        addListeners();
    }

    private JPanel makeRow(String labelText, JTextField field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setOpaque(false);
        JLabel lbl = new JLabel(labelText);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setPreferredSize(new Dimension(80, 30));
        row.add(lbl);
        row.add(field);
        return row;
    }

    private void addListeners() {
        addBtn.addActionListener(e -> {
            String name     = nameField.getText().trim();
            String email    = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role     = (String) roleDropdown.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Please fill in all fields.");
                return;
            }

            for (User u : Main.library.getUsersList()) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    statusLabel.setForeground(new Color(220, 50, 50));
                    statusLabel.setText("Email already exists.");
                    return;
                }
            }

            int newId = Main.library.getUsersList().size() + 1;
            switch (role) {
                case "Admin"     -> Main.library.getUsersList().add(new Admin(newId, name, email, password));
                case "Librarian" -> Main.library.getUsersList().add(new Librarian(newId, name, email, password));
                default          -> Main.library.getUsersList().add(new Member(newId, name, email, password));
            }

            statusLabel.setForeground(new Color(50, 200, 100));
            statusLabel.setText("User \"" + name + "\" added as " + role + ".");
            nameField.setText(""); emailField.setText(""); passwordField.setText("");
        });
    }
}
