package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.Navbar;

public class ProfilePage extends JPanel {
    private JLabel title;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel roleLabel;

    public ProfilePage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Profile");

        // Mock data for now
        // TODO: Replace with backend/auth data later

        usernameLabel = new JLabel("Username: Mohamed");

        emailLabel = new JLabel("Email: mohamed@gmail.com");

        roleLabel = new JLabel("Role: Member");
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        Font infoFont = new Font("Arial", Font.PLAIN, 24);

        usernameLabel.setFont(infoFont);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);

        emailLabel.setFont(infoFont);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setAlignmentX(CENTER_ALIGNMENT);

        roleLabel.setFont(infoFont);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel();

        content.setBackground(Color.BLACK);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.add(title);
        content.add(Box.createVerticalStrut(40));
        content.add(usernameLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(emailLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(roleLabel);

        add(content, BorderLayout.CENTER);
    }
}