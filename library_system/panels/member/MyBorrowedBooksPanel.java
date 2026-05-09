package library_system.panels.member;

import java.awt.*;
import javax.swing.*;

import library_system.components.Navbar;

public class MyBorrowedBooksPanel extends JPanel {
    private JLabel title;
    private JList<String> booksList;
    private DefaultListModel<String> booksModel;

    public MyBorrowedBooksPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("My Borrowed Books");
        booksModel = new DefaultListModel<>();

        // Mock data for now
        booksModel.addElement("Clean Code - Due: 2026-06-01");
        booksModel.addElement("Atomic Habits - Due: 2026-05-15");

        booksList = new JList<>(booksModel);
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        booksList.setBackground(new Color(30, 30, 30));
        booksList.setForeground(Color.WHITE);
        booksList.setFont(new Font("Arial", Font.PLAIN, 20));
        booksList.setFixedCellHeight(50);
        booksList.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(booksList);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        scrollPane.setMaximumSize(new Dimension(700, 300));
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);

        content.add(Box.createVerticalGlue());
        content.add(title);

        content.add(Box.createVerticalStrut(20));

        content.add(scrollPane);
        content.add(Box.createVerticalGlue());

        add(content, BorderLayout.CENTER);
    }
}
