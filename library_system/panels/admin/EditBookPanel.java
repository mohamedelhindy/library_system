package library_system.panels.admin;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import library_system.Main;
import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.models.Book;

public class EditBookPanel extends JPanel {

    private JLabel title;
    private JLabel statusLabel;

    private JTextField isbnField;
    private JTextField newTitleField;
    private JTextField newAuthorField;
    private JTextField newGenreField;
    private JTextField newCopiesField;

    private StyledButton searchBtn;
    private StyledButton saveBtn;

    public EditBookPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title       = new JLabel("Edit Book");
        statusLabel = new JLabel(" ");

        isbnField      = new JTextField(25);
        newTitleField  = new JTextField(25);
        newAuthorField = new JTextField(25);
        newGenreField  = new JTextField(25);
        newCopiesField = new JTextField(25);

        searchBtn = new StyledButton("Find Book");
        saveBtn   = new StyledButton("Save Changes");
    }

    private void styleComponents() {
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(50, 200, 100));

        for (JTextField f : new JTextField[]{isbnField, newTitleField, newAuthorField, newGenreField, newCopiesField}) {
            f.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            f.setBackground(new Color(30, 41, 59));
            f.setForeground(Color.WHITE);
            f.setCaretColor(Color.WHITE);
            f.setBorder(new EmptyBorder(8, 12, 8, 12));
        }
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

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchRow.setOpaque(false);
        JLabel lbl = new JLabel("ISBN:");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchRow.add(lbl);
        searchRow.add(isbnField);
        searchRow.add(searchBtn);

        content.add(title);
        content.add(Box.createVerticalStrut(25));
        content.add(searchRow);
        content.add(Box.createVerticalStrut(20));
        content.add(makeRow("New Title:", newTitleField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("New Author:", newAuthorField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("New Genre:", newGenreField));
        content.add(Box.createVerticalStrut(12));
        content.add(makeRow("New Copies:", newCopiesField));
        content.add(Box.createVerticalStrut(20));
        content.add(saveBtn);
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
        lbl.setPreferredSize(new Dimension(100, 30));
        row.add(lbl);
        row.add(field);
        return row;
    }

    private void addListeners() {
        searchBtn.addActionListener(e -> {
            String isbn = isbnField.getText().trim();
            if (isbn.isEmpty()) { statusLabel.setText("Enter an ISBN."); return; }

            for (Book b : Main.library.getBooksList()) {
                if (b.getISBN().equals(isbn)) {
                    newTitleField.setText(b.getTitle());
                    newAuthorField.setText(b.getAuthor());
                    newGenreField.setText(b.getGenre());
                    newCopiesField.setText(String.valueOf(b.getCopiesAvailable()));
                    statusLabel.setForeground(new Color(50, 200, 100));
                    statusLabel.setText("Book found. Edit the fields and click Save.");
                    return;
                }
            }
            statusLabel.setForeground(new Color(220, 50, 50));
            statusLabel.setText("Book not found.");
        });

        saveBtn.addActionListener(e -> {
            String isbn = isbnField.getText().trim();
            if (isbn.isEmpty()) { statusLabel.setText("Enter an ISBN first."); return; }

            for (Book b : Main.library.getBooksList()) {
                if (b.getISBN().equals(isbn)) {
                    if (!newTitleField.getText().trim().isEmpty())  b.setTitle(newTitleField.getText().trim());
                    if (!newAuthorField.getText().trim().isEmpty()) b.setAuthor(newAuthorField.getText().trim());
                    if (!newGenreField.getText().trim().isEmpty())  b.setGenre(newGenreField.getText().trim());
                    try {
                        int c = Integer.parseInt(newCopiesField.getText().trim());
                        b.setCopiesAvailable(c);
                    } catch (NumberFormatException ignored) {}

                    statusLabel.setForeground(new Color(50, 200, 100));
                    statusLabel.setText("Book updated successfully.");
                    return;
                }
            }
            statusLabel.setForeground(new Color(220, 50, 50));
            statusLabel.setText("Book not found.");
        });
    }
}
