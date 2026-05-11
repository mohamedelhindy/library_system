package library_system.panels.member;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.Book;
import library_system.models.BorrowRecord;
import library_system.models.Member;
import library_system.utils.Session;

public class SearchBooksPanel extends JPanel {

    private JLabel title;
    private JTextField searchField;
    private StyledButton searchBtn;
    private JTextArea booksList;
    private JLabel statusLabel;

    private List<Book> currentResults;

    public SearchBooksPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Search Books");
        searchField = new JTextField(20);
        searchBtn   = new StyledButton("Search");
        statusLabel = new JLabel(" ");
        booksList   = new JTextArea();
        booksList.setEditable(false);

        currentResults = Main.library.getBooksList();
        displayBooks(currentResults);
    }

    private void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            booksList.setText("No books found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Book b : books) {
            sb.append(i++).append(". ").append(b.getTitle())
              .append(" - ").append(b.getAuthor())
              .append("  [").append(b.getCopiesAvailable()).append(" available]\n\n");
        }
        booksList.setText(sb.toString().trim());
        currentResults = books;
    }

    private void styleComponents() {
        setBackground(new Color(15, 23, 42));

        title.setFont(new Font("Segoe UI", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setBackground(new Color(30, 41, 59));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(new EmptyBorder(10, 15, 10, 15));

        searchBtn.setMaximumSize(new Dimension(110, 40));
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchRow.setOpaque(false);
        searchRow.add(searchField);
        searchRow.add(searchBtn);

        JScrollPane scroll = new JScrollPane(booksList);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40)),
            new EmptyBorder(5, 5, 5, 5)
        ));

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(searchRow);
        content.add(Box.createVerticalStrut(10));
        content.add(statusLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(scroll);

        add(content, BorderLayout.CENTER);
        addListeners();
    }

    private void addListeners() {
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (keyword.isEmpty()) {
                displayBooks(Main.library.getBooksList());
                statusLabel.setText(" ");
                return;
            }
            List<Book> results = Main.library.searchBooks(keyword);
            displayBooks(results);
            statusLabel.setText(results.isEmpty() ? "No results found." : results.size() + " book(s) found.");
        });

        booksList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int lineIndex = booksList.getCaretPosition();
                    String text = booksList.getText();
                    int lineStart = text.lastIndexOf('\n', lineIndex - 1) + 1;
                    int lineEnd = text.indexOf('\n', lineIndex);
                    if (lineEnd == -1) lineEnd = text.length();
                    String line = text.substring(lineStart, lineEnd).trim();
                    if (line.isEmpty()) return;

                    // extract title from "1. Title - Author  [X available]"
                    String bookName = "";
                    if (line.contains(". ") && line.contains(" - ")) {
                        bookName = line.substring(line.indexOf(". ") + 2, line.indexOf(" - ")).trim();
                    }

                    if (bookName.isEmpty()) return;

                    List<Book> found = Main.library.searchBooks(bookName);
                    if (found.isEmpty()) return;
                    Book book = found.get(0);

                    if (!book.isAvailable()) {
                        statusLabel.setForeground(new Color(220, 50, 50));
                        statusLabel.setText("\"" + book.getTitle() + "\" is not available.");
                        return;
                    }

                    Member member = (Member) Session.getCurrentUser();
                    member.borrowBook(book);
                    Main.library.addTransactionRecord(
                        member.getBorrowedBooks().get(member.getBorrowedBooks().size() - 1)
                    );

                    statusLabel.setForeground(new Color(50, 200, 100));
                    statusLabel.setText("Borrowed \"" + book.getTitle() + "\"! Due: " + LocalDate.now().plusDays(14));
                    displayBooks(Main.library.getBooksList());
                }
            }
        });
    }
}
