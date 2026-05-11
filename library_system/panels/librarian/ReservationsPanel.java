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

public class ReservationsPanel extends JPanel {

    private JLabel title;
    private JLabel statusLabel;
    private JTextField memberEmailField;
    private JTextField bookTitleField;
    private StyledButton reserveBtn;
    private JTextArea resultArea;

    public ReservationsPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Reservations");
        statusLabel = new JLabel(" ");

        memberEmailField = new JTextField(20);
        bookTitleField   = new JTextField(20);
        reserveBtn       = new StyledButton("Reserve");

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        refreshList();
    }

    private void refreshList() {
        List<BorrowRecord> records = Main.library.getBorrowRecords();
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for (BorrowRecord r : records) {
            if (r.getStatus().equals("Reserved")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("  →  ").append(r.getMember().getName())
                  .append("\n   Reserved on: ").append(r.getBorrowDate()).append("\n\n");
            }
        }

        resultArea.setText(sb.length() == 0 ? "No reservations." : sb.toString().trim());
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
        formRow.add(reserveBtn);

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
        reserveBtn.addActionListener(e -> {
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

            var results = Main.library.searchBooks(bookTitle);
            if (results.isEmpty()) {
                statusLabel.setText("Book not found.");
                return;
            }

            var book = results.get(0);

            BorrowRecord reservation = new BorrowRecord(
                Main.library.getBorrowRecords().size() + 1,
                member, book,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
            );
            reservation.setStatus("Reserved");
            Main.library.addTransactionRecord(reservation);

            statusLabel.setForeground(new Color(50, 200, 100));
            statusLabel.setText("Reserved \"" + book.getTitle() + "\" for " + member.getName());
            memberEmailField.setText("");
            bookTitleField.setText("");
            refreshList();
        });
    }
}
