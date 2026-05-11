package library_system.panels.librarian;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import library_system.components.Navbar;
import library_system.models.BorrowRecord;
import library_system.Main;

public class OverdueBooksPanel extends JPanel {

    private JLabel title;
    private JTextArea resultArea;

    public OverdueBooksPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title      = new JLabel("Overdue Books");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        List<BorrowRecord> records = Main.library.getBorrowRecords();
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for (BorrowRecord r : records) {
            if (r.isOverdue() && r.getStatus().equals("Borrowed")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("  →  ").append(r.getMember().getName())
                  .append("\n   Due: ").append(r.getDueDate())
                  .append("   Days Late: ").append(r.getLateDays())
                  .append("   Fine: $").append(String.format("%.2f", r.calculateFine()))
                  .append("\n\n");
            }
        }

        resultArea.setText(sb.length() == 0 ? "No overdue books." : sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        resultArea.setBackground(new Color(30, 41, 59));
        resultArea.setForeground(new Color(255, 100, 100));
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        resultArea.setMargin(new Insets(15, 15, 15, 15));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(new Color(15, 23, 42));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
    }
}
