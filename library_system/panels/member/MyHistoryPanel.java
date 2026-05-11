package library_system.panels.member;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.components.Navbar;
import library_system.models.BorrowRecord;
import library_system.models.Member;
import library_system.utils.Session;

public class MyHistoryPanel extends JPanel {

    private JLabel title;
    private JTextArea historyArea;

    public MyHistoryPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("My History");
        historyArea = new JTextArea();
        historyArea.setEditable(false);

        Member member = (Member) Session.getCurrentUser();
        List<BorrowRecord> records = member.getBorrowedBooks();

        if (records.isEmpty()) {
            historyArea.setText("No borrow history yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (BorrowRecord r : records) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("\n   Borrowed: ").append(r.getBorrowDate())
                  .append("   Due: ").append(r.getDueDate())
                  .append("\n   Returned: ").append(r.getReturnDate() != null ? r.getReturnDate() : "Not yet")
                  .append("   Fine: $").append(String.format("%.2f", r.getFine()))
                  .append("   Status: ").append(r.getStatus())
                  .append("\n\n");
            }
            historyArea.setText(sb.toString().trim());
        }
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyArea.setBackground(new Color(30, 41, 59));
        historyArea.setForeground(Color.WHITE);
        historyArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        historyArea.setMargin(new Insets(15, 15, 15, 15));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(20, 30, 48), getWidth(), getHeight(), new Color(36, 59, 85));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scroll = new JScrollPane(historyArea);
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
