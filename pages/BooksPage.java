package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.Navbar;
import library_system.components.StyledButton;
import library_system.components.LabelField;

public class BooksPage extends JPanel {

    private JLabel title;
    private JTextField searchField;
    private StyledButton searchBtn;
    private JTextArea booksList;
    private LabelField roleLabel;
    private JComboBox<String> roleDropdown;

    public BooksPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Books");
        searchField = new JTextField(20);
        searchBtn = new StyledButton("Search");
        roleLabel = new LabelField("Role:");
        roleDropdown = new JComboBox<>(new String[]{"Admin", "Librarian", "Member"});

        booksList = new JTextArea();
        booksList.setText(
            "1. The Great Gatsby - F. Scott Fitzgerald\n\n" +
            "2. 1984 - George Orwell\n\n" +
            "3. To Kill a Mockingbird - Harper Lee\n\n" +
            "4. Clean Code - Robert C. Martin"
        );
        booksList.setEditable(false);
    }

    private void styleComponents() {
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        searchField.setFont(new Font("Arial", Font.PLAIN, 16));

        searchBtn.setMaximumSize(new Dimension(100, 35));
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));

        booksList.setBackground(new Color(30, 30, 30));
        booksList.setForeground(Color.WHITE);
        booksList.setFont(new Font("Arial", Font.PLAIN, 18));
        booksList.setMargin(new Insets(20, 20, 20, 20));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel searchRow = new JPanel();
        searchRow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchRow.setBackground(Color.BLACK);
        searchRow.add(searchField);
        searchRow.add(Box.createHorizontalStrut(10));
        searchRow.add(searchBtn);

        JScrollPane scrollPane = new JScrollPane(booksList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(searchRow);
        content.add(Box.createVerticalStrut(20));
        content.add(scrollPane);

        add(content, BorderLayout.CENTER);

        addListeners();
    }

    private void addListeners() {
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            String[] lines = booksList.getText().split("\n\n");
            StringBuilder result = new StringBuilder();

            for (String line : lines) {
                if (line.toLowerCase().contains(keyword)) {
                    result.append(line).append("\n\n");
                }
            }

            booksList.setText(result.toString().trim());
        });
    }
}