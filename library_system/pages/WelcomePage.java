package pages;

import javax.swing.*;
import java.awt.*;

import utils.Navigator;

public class WelcomePage extends JPanel {
    private JLabel title;
    private JLabel subtitle;
    private JProgressBar progressBar;

    public WelcomePage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Library System");
        subtitle = new JLabel("Manage books, users, and borrowing easily");
        progressBar = new JProgressBar();
        progressBar.setValue(0);
    }

    private void styleComponents() {
        setOpaque(false);

        title.setFont(new Font("Segoe UI", Font.BOLD, 54));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(new Color(220, 230, 255));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        progressBar.setMaximumSize(new Dimension(320, 24));
        progressBar.setPreferredSize(new Dimension(320, 24));
        progressBar.setAlignmentX(CENTER_ALIGNMENT);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(93, 173, 226));
        progressBar.setBackground(new Color(235, 245, 255));
        progressBar.setBorder(BorderFactory.createEmptyBorder());
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setMaximumSize(new Dimension(650, 300));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(subtitle);
        cardPanel.add(Box.createVerticalStrut(55));
        cardPanel.add(progressBar);

        add(cardPanel);

        add(Box.createVerticalGlue());

        startLoading();
    }

    private void startLoading() {
        Timer timer = new Timer(40, e -> {
            int value = progressBar.getValue();

            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                ((Timer) e.getSource()).stop();
                Navigator.navigateTo(new SignupPage());
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        GradientPaint background = new GradientPaint(
                0, 0, new Color(20, 30, 48),
                width, height, new Color(36, 59, 85)
        );

        g2d.setPaint(background);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(255, 255, 255, 25));
        g2d.fillOval(-120, -120, 350, 350);

        g2d.setColor(new Color(93, 173, 226, 35));
        g2d.fillOval(width - 260, height - 260, 420, 420);

        g2d.setColor(new Color(255, 255, 255, 18));
        g2d.fillRoundRect(
                width / 2 - 380,
                height / 2 - 190,
                760,
                380,
                40,
                40
        );

        g2d.dispose();
    }
}