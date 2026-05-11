package library_system.panels.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.Book;

public class AddBookPanel extends JPanel {

    private JLabel title;
    private JLabel statusLabel;

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField copiesField;

    private StyledButton addBtn;

    public AddBookPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Add Book");
        statusLabel = new JLabel(" ");

        isbnField   = new JTextField(25);
        titleField  = new JTextField(25);
        authorField = new JTextField(25);
        genreField  = new JTextField(25);
        copiesField = new JTextField(25);

        addBtn = new StyledButton("Add Book");
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));

        for (JTextField f : new JTextField[]{isbnField, titleField, authorField, genreField, copiesField}) {
            f.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            f.setBackground(new Color(30, 41, 59));
            f.setForeground(Color.WHITE);
            f.setCaretColor(Color.WHITE);
            f.setBorder(new EmptyBorder(8, 12, 8, 12));
        }

        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addBtn.setMaximumSize(new Dimension(150, 42));
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        content.add(makeRow("ISBN:", isbnField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Title:", titleField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Author:", authorField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Genre:", genreField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("Copies:", copiesField));
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
            String isbn   = isbnField.getText().trim();
            String t      = titleField.getText().trim();
            String author = authorField.getText().trim();
            String genre  = genreField.getText().trim();
            String copies = copiesField.getText().trim();

            if (isbn.isEmpty() || t.isEmpty() || author.isEmpty() || copies.isEmpty()) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Please fill in all required fields.");
                return;
            }

            int numCopies;
            try {
                numCopies = Integer.parseInt(copies);
            } catch (NumberFormatException ex) {
                statusLabel.setForeground(new Color(220, 50, 50));
                statusLabel.setText("Copies must be a number.");
                return;
            }

            for (Book b : Main.library.getBooksList()) {
                if (b.getISBN().equals(isbn)) {
                    statusLabel.setForeground(new Color(220, 50, 50));
                    statusLabel.setText("A book with this ISBN already exists.");
                    return;
                }
            }

            Main.library.getBooksList().add(new Book(isbn, t, author, genre.isEmpty() ? "General" : genre, numCopies));

            statusLabel.setForeground(new Color(50, 200, 100));
            statusLabel.setText("Book \"" + t + "\" added successfully.");
            isbnField.setText(""); titleField.setText(""); authorField.setText("");
            genreField.setText(""); copiesField.setText("");
        });
    }
}
