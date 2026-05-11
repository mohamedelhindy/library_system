package library_system.panels.member;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.BorrowRecord;
import library_system.models.Book;
import library_system.models.Member;
import library_system.utils.Session;
import java.time.LocalDate;

public class MyReservationsPanel extends JPanel {

    private JLabel title;
    private JLabel statusLabel;
    private JTextField bookField;
    private StyledButton reserveBtn;
    private JTextArea resultArea;

    public MyReservationsPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("My Reservations");
        statusLabel = new JLabel(" ");
        bookField   = new JTextField(20);
        reserveBtn  = new StyledButton("Reserve");
        resultArea  = new JTextArea();
        resultArea.setEditable(false);
        refreshList();
    }

    private void refreshList() {
        Member member = (Member) Session.getCurrentUser();
        List<BorrowRecord> records = Main.library.getBorrowRecords();

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (BorrowRecord r : records) {
            if (r.getMember().equals(member) && r.getStatus().equals("Reserved")) {
                sb.append(i++).append(". ").append(r.getBook().getTitle())
                  .append("\n   Reserved on: ").append(r.getBorrowDate())
                  .append("   Expires: ").append(r.getDueDate())
                  .append("\n\n");
            }
        }
        resultArea.setText(sb.length() == 0 ? "No reservations yet." : sb.toString().trim());
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));

        bookField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        bookField.setBackground(new Color(30, 41, 59));
        bookField.setForeground(Color.WHITE);
        bookField.setCaretColor(Color.WHITE);
        bookField.setBorder(new EmptyBorder(8, 12, 8, 12));

        reserveBtn.setMaximumSize(new Dimension(130, 38));
        reserveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        resultArea.setBackground(new Color(30, 41, 59));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setMargin(new Insets(15, 15, 15, 15));
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

        JPanel reserveRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        reserveRow.setOpaque(false);
        JLabel lbl = new JLabel("Book Title:");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reserveRow.add(lbl);
        reserveRow.add(bookField);
        reserveRow.add(reserveBtn);

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(reserveRow);
        content.add(Box.createVerticalStrut(8));
        content.add(statusLabel);
        content.add(Box.createVerticalStrut(15));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
        addListeners();
    }

    private void addListeners() {
        reserveBtn.addActionListener(e -> {
            String keyword = bookField.getText().trim();
            if (keyword.isEmpty()) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Enter a book title to reserve.");
                return;
            }

            List<Book> results = Main.library.searchBooks(keyword);
            if (results.isEmpty()) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Book not found.");
                return;
            }

            Book book = results.get(0);
            Member member = (Member) Session.getCurrentUser();

            // check if already reserved
            for (BorrowRecord r : Main.library.getBorrowRecords()) {
                if (r.getMember().equals(member)
                        && r.getBook().equals(book)
                        && r.getStatus().equals("Reserved")) {
                    statusLabel.setForeground(new Color(220, 50, 50));
                    statusLabel.setText("You already reserved this book.");
                    return;
                }
            }

            BorrowRecord reservation = new BorrowRecord(
                Main.library.getBorrowRecords().size() + 1,
                member, book,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
            );
            reservation.setStatus("Reserved");
            Main.library.addTransactionRecord(reservation);

            statusLabel.setForeground(new Color(50, 200, 100));
            statusLabel.setText("Reserved \"" + book.getTitle() + "\" for 7 days.");
            bookField.setText("");
            refreshList();
        });
    }
}
