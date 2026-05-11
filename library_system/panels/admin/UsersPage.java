package library_system.panels.admin;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.Member;
import library_system.models.User;

public class UsersPage extends JPanel {
    private JLabel title;
    private JTextField searchField;
    private StyledButton searchBtn;
    private JTextArea resultArea;

    public UsersPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Users");
        searchField = new JTextField(20);
        searchBtn   = new StyledButton("Search");
        resultArea  = new JTextArea();
        resultArea.setEditable(false);
        showAllUsers();
    }

    private void showAllUsers() {
        List<User> users = Main.library.getUsersList();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (User u : users) {
            int borrowed = u instanceof Member ? ((Member) u).getBorrowedBooks().size() : 0;
            sb.append(i++).append(". ").append(u.getName())
              .append("  [").append(u.getRole()).append("]")
              .append("  ").append(u.getEmail())
              .append("  Borrowed: ").append(borrowed)
              .append("\n\n");
        }
        resultArea.setText(sb.length() == 0 ? "No users found." : sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        searchField.setBackground(new Color(30, 41, 59));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(new EmptyBorder(8, 12, 8, 12));

        resultArea.setBackground(new Color(30, 41, 59));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        resultArea.setMargin(new Insets(15, 15, 15, 15));
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
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchRow.setOpaque(false);
        JLabel lbl = new JLabel("Search:");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchRow.add(lbl);
        searchRow.add(searchField);
        searchRow.add(searchBtn);

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(searchRow);
        content.add(Box.createVerticalStrut(15));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
        addListeners();
    }

    private void addListeners() {
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) { showAllUsers(); return; }

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (User u : Main.library.getUsersList()) {
                if (u.getName().toLowerCase().contains(keyword) || u.getEmail().toLowerCase().contains(keyword)) {
                    int borrowed = u instanceof Member ? ((Member) u).getBorrowedBooks().size() : 0;
                    sb.append(i++).append(". ").append(u.getName())
                      .append("  [").append(u.getRole()).append("]")
                      .append("  ").append(u.getEmail())
                      .append("  Borrowed: ").append(borrowed).append("\n\n");
                }
            }
            resultArea.setText(sb.length() == 0 ? "No users found." : sb.toString().trim());
        });
    }
}
