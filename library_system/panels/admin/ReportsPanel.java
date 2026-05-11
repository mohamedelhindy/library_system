package library_system.panels.admin;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.models.BorrowRecord;
import library_system.models.Member;
import library_system.models.User;

public class ReportsPanel extends JPanel {

    private JLabel title;
    private JTextArea resultArea;

    public ReportsPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title      = new JLabel("Reports");
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        buildReport();
    }

    private void buildReport() {
        List<User> users           = Main.library.getUsersList();
        List<BorrowRecord> records = Main.library.getBorrowRecords();

        int totalBooks    = Main.library.getBooksList().size();
        int totalUsers    = users.size();
        int totalBorrowed = 0;
        int totalOverdue  = 0;
        double totalFines = 0;

        for (BorrowRecord r : records) {
            if (r.getStatus().equals("Borrowed")) totalBorrowed++;
            if (r.isOverdue() && r.getStatus().equals("Borrowed")) totalOverdue++;
            totalFines += r.getFine();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("====== Library Summary ======\n\n");
        sb.append("Total Books:       ").append(totalBooks).append("\n");
        sb.append("Total Users:       ").append(totalUsers).append("\n");
        sb.append("Active Borrows:    ").append(totalBorrowed).append("\n");
        sb.append("Overdue Books:     ").append(totalOverdue).append("\n");
        sb.append("Total Fines:       $").append(String.format("%.2f", totalFines)).append("\n\n");

        sb.append("====== Active Borrows ======\n\n");
        int i = 1;
        for (BorrowRecord r : records) {
            if (r.getStatus().equals("Borrowed")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("  →  ").append(r.getMember().getName())
                  .append("\n   Due: ").append(r.getDueDate())
                  .append(r.isOverdue() ? "  ⚠ OVERDUE" : "").append("\n\n");
            }
        }
        if (i == 1) sb.append("No active borrows.\n\n");

        sb.append("====== All Users ======\n\n");
        int j = 1;
        for (User u : users) {
            int borrowed = u instanceof Member ? ((Member) u).getBorrowedBooks().stream()
                .filter(r -> r.getStatus().equals("Borrowed")).mapToInt(r -> 1).sum() : 0;
            sb.append(j++).append(". ").append(u.getName())
              .append("  [").append(u.getRole()).append("]")
              .append("  Active borrows: ").append(borrowed).append("\n\n");
        }

        resultArea.setText(sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

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

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
    }
}
