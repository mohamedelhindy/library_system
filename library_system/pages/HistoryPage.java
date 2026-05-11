package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.Navbar;

public class HistoryPage extends JPanel {
    private JLabel title;
    private JList<String> historyList;
    private DefaultListModel<String> historyModel;

    public HistoryPage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("History");
    historyModel = new DefaultListModel<>();

        // Mock data for now
        // TODO: Will do ONCE Backend integrated with database
        historyModel.addElement("Borrowed: Clean Code");

        historyModel.addElement("Returned: 1984");

        historyModel.addElement("Borrowed: Atomic Habits");

        historyModel.addElement("Returned: The Great Gatsby");

        historyList = new JList<>(historyModel);
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        historyList.setBackground(new Color(30, 30, 30));
        historyList.setForeground(Color.WHITE);
        historyList.setFont(new Font("Arial", Font.PLAIN, 20));
        historyList.setFixedCellHeight(50);
        historyList.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(historyList);

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