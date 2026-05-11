package library_system.panels.librarian;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.BorrowRecord;
import library_system.models.Member;
import library_system.models.User;
import library_system.Main;

public class ReturnBookPage extends JPanel {

    private JLabel title;
    private JLabel statusLabel;

    private JTextField memberEmailField;
    private JTextField bookTitleField;
    private StyledButton returnBtn;

    private JTextArea resultArea;

    public ReturnBookPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Return Book");
        statusLabel = new JLabel(" ");

        memberEmailField = new JTextField(20);
        bookTitleField   = new JTextField(20);
        returnBtn        = new StyledButton("Return Book");

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        refreshRecords();
    }

    private void refreshRecords() {
        List<BorrowRecord> records = Main.library.getBorrowRecords();
        if (records.isEmpty()) {
            resultArea.setText("No borrow records found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (BorrowRecord r : records) {
            if (r.getStatus().equals("Borrowed")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("  →  ").append(r.getMember().getName())
                  .append("\n   Due: ").append(r.getDueDate())
                  .append(r.isOverdue() ? "  ⚠ OVERDUE" : "").append("\n\n");
            }
        }
        resultArea.setText(sb.length() == 0 ? "No active borrows." : sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(220, 50, 50));

        resultArea.setBackground(new Color(30, 41, 59));
        resultArea.setForeground(Color.WHITE);
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

        JPanel formRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        formRow.setBackground(new Color(15, 23, 42));
        formRow.add(makeLabel("Member Email:"));
        formRow.add(memberEmailField);
        formRow.add(makeLabel("Book Title:"));
        formRow.add(bookTitleField);
        formRow.add(returnBtn);

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(formRow);
        content.add(Box.createVerticalStrut(5));
        content.add(statusLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(scroll);

        add(content, BorderLayout.CENTER);

        addListeners();
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return l;
    }

    private void addListeners() {
        returnBtn.addActionListener(e -> {
            String email     = memberEmailField.getText().trim();
            String bookTitle = bookTitleField.getText().trim();

            if (email.isEmpty() || bookTitle.isEmpty()) {
                statusLabel.setText("Please fill in both fields.");
                return;
            }

            Member member = null;
            for (User u : Main.library.getUsersList()) {
                if (u.getEmail().equals(email) && u instanceof Member) {
                    member = (Member) u;
                    break;
                }
            }

            if (member == null) {
                statusLabel.setText("Member not found.");
                return;
            }

            BorrowRecord found = null;
            for (BorrowRecord r : member.getBorrowedBooks()) {
                if (r.getBook().getTitle().equalsIgnoreCase(bookTitle)
                        && r.getStatus().equals("Borrowed")) {
                    found = r;
                    break;
                }
            }

            if (found == null) {
                statusLabel.setText("No active borrow found for that book.");
                return;
            }

            found.markReturned(LocalDate.now());
            found.getBook().addCopy();

            statusLabel.setForeground(new Color(50, 200, 100));
            if (found.getFine() > 0) {
                statusLabel.setText("Returned. Fine: $" + String.format("%.2f", found.getFine()));
            } else {
                statusLabel.setText("Book returned successfully. No fine.");
            }

            memberEmailField.setText("");
            bookTitleField.setText("");
            refreshRecords();
        });
    }
}
