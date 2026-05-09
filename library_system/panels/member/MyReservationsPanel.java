package library_system.panels.member;

import java.awt.*;
import javax.swing.*;

import library_system.components.Navbar;

public class MyReservationsPanel extends JPanel {
    private JLabel title;
    private JList<String> reservationsList;
    private DefaultListModel<String> reservationsModel;

    public MyReservationsPanel() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("My Reservations");
        reservationsModel = new DefaultListModel<>();

        // Mock data for now
        reservationsModel.addElement("Harry Potter - Status: Waiting");
        reservationsModel.addElement("Lord of the Rings - Status: Available");

        reservationsList = new JList<>(reservationsModel);
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        reservationsList.setBackground(new Color(30, 30, 30));
        reservationsList.setForeground(Color.WHITE);
        reservationsList.setFont(new Font("Arial", Font.PLAIN, 20));
        reservationsList.setFixedCellHeight(50);
        reservationsList.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(reservationsList);

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
