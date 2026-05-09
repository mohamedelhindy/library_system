package library_system.pages;

import java.awt.*;
import javax.swing.*;
import library_system.components.Navbar;

public class Dashboard extends JPanel {

    private JLabel title;
    private JLabel welcomeLabel;

    public Dashboard() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Dashboard");
        welcomeLabel = new JLabel("Welcome back!");
    }

    private void styleComponents() {
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomeLabel.setForeground(Color.LIGHT_GRAY);
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(Box.createVerticalGlue());
        content.add(title);

        content.add(Box.createVerticalStrut(10));
        
        content.add(welcomeLabel);
        content.add(Box.createVerticalGlue());

        add(content, BorderLayout.CENTER);
    }
}