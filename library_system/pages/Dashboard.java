package library_system.pages;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.components.Navbar;
import library_system.utils.Session;

public class Dashboard extends JPanel {

    private JLabel title;

    private JLabel subtitle;

    private JLabel roleLabel;

    private JPanel cardPanel;

    public Dashboard() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        String username = Session.getCurrentUser().getName();

        String role = Session.getCurrentUser().getRole();

        title = new JLabel("Welcome Back, " + username);

        subtitle = new JLabel("Manage your books and library activity");

        roleLabel = new JLabel("Current Role: " + role);

        cardPanel = new JPanel();
    }

    private void styleComponents() {
        setLayout(new BorderLayout());

        setBackground(new Color(20, 30, 48));

        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(Color.WHITE);

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(new Color(220, 230, 255));

        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        roleLabel.setForeground(new Color(93, 173, 226));

        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(50, 60, 50, 60));
    }

    private void layoutComponents() {

        add(new Navbar(), BorderLayout.WEST);

        JPanel content = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient =
                    new GradientPaint(
                        0,
                        0,
                        new Color(20, 30, 48),
                        getWidth(),
                        getHeight(),
                        new Color(36, 59, 85)
                    );

                g2d.setPaint(gradient);
                g2d.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
                );

                g2d.dispose();
            }
        };

        content.setLayout(new GridBagLayout());

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(12));
        cardPanel.add(subtitle);
        cardPanel.add(Box.createVerticalStrut(25));
        cardPanel.add(roleLabel);
        cardPanel.add(Box.createVerticalStrut(40));

        JPanel statsCard = new JPanel();

        statsCard.setPreferredSize(new Dimension(450, 180));

        statsCard.setMaximumSize(new Dimension(450, 180));

        statsCard.setBackground(new Color(255, 255, 255, 30));

        statsCard.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(255, 255, 255, 40)
                ),
                new EmptyBorder(25, 25, 25, 25)
            )
        );

        statsCard.setLayout(new BoxLayout(statsCard, BoxLayout.Y_AXIS));

        JLabel cardTitle = new JLabel("Library Overview");

        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        cardTitle.setForeground(Color.WHITE);

        JLabel cardText = new JLabel("Browse books, manage reservations, and track your activity.");

        cardText.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cardText.setForeground(new Color(220, 230, 255));

        statsCard.add(cardTitle);
        statsCard.add(Box.createVerticalStrut(15));
        statsCard.add(cardText);

        cardPanel.add(statsCard);

        content.add(cardPanel);

        add(content, BorderLayout.CENTER);
    }
}