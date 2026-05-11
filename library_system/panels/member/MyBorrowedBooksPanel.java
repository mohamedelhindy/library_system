package library_system.panels.member;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.BorrowRecord;
import library_system.models.Member;
import library_system.utils.Session;

public class MyBorrowedBooksPanel extends JPanel {

    private JLabel title;
    private JTextArea booksList;
    private JLabel statusLabel;
    private JTextField returnField;
    private StyledButton returnBtn;

    public MyBorrowedBooksPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("My Borrowed Books");
        statusLabel = new JLabel(" ");
        returnField = new JTextField(20);
        returnBtn   = new StyledButton("Return Book");
        booksList   = new JTextArea();
        booksList.setEditable(false);
        refreshList();
    }

    private void refreshList() {
        Member member = (Member) Session.getCurrentUser();
        List<BorrowRecord> records = member.getBorrowedBooks();

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (BorrowRecord r : records) {
            if (r.getStatus().equals("Borrowed")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("\n   Due: ").append(r.getDueDate())
                  .append(r.isOverdue() ? "  ⚠ OVERDUE" : "")
                  .append("\n\n");
            }
        }
        booksList.setText(sb.length() == 0 ? "You have no borrowed books." : sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));

        returnField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        returnField.setBackground(new Color(30, 41, 59));
        returnField.setForeground(Color.WHITE);
        returnField.setCaretColor(Color.WHITE);
        returnField.setBorder(new EmptyBorder(8, 12, 8, 12));

        returnBtn.setMaximumSize(new Dimension(150, 38));
        returnBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        booksList.setBackground(new Color(30, 41, 59));
        booksList.setForeground(Color.WHITE);
        booksList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        booksList.setMargin(new Insets(15, 15, 15, 15));
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

        JPanel returnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        returnRow.setOpaque(false);

        JLabel lbl = new JLabel("Book Title:");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        returnRow.add(lbl);
        returnRow.add(returnField);
        returnRow.add(returnBtn);

        JScrollPane scroll = new JScrollPane(booksList);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(returnRow);
        content.add(Box.createVerticalStrut(8));
        content.add(statusLabel);
        content.add(Box.createVerticalStrut(15));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
        addListeners();
    }

    private void addListeners() {
        returnBtn.addActionListener(e -> {
            String bookTitle = returnField.getText().trim();
            if (bookTitle.isEmpty()) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Enter a book title to return.");
                return;
            }

            Member member = (Member) Session.getCurrentUser();
            BorrowRecord found = null;

            for (BorrowRecord r : member.getBorrowedBooks()) {
                if (r.getBook().getTitle().equalsIgnoreCase(bookTitle) && r.getStatus().equals("Borrowed")) {
                    found = r;
                    break;
                }
            }

            if (found == null) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("No active borrow found for that title.");
                return;
            }

            found.markReturned(LocalDate.now());
            found.getBook().addCopy();

            statusLabel.setForeground(new Color(50, 200, 100));
            if (found.getFine() > 0) {
                statusLabel.setText("Returned. Fine: $" + String.format("%.2f", found.getFine()));
            } else {
                statusLabel.setText("Returned successfully. No fine.");
            }

            returnField.setText("");
            refreshList();
        });
    }
}
