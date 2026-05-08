package library_system.pages;

import javax.swing.*;

import java.awt.*;

import library_system.utils.Navigator;


public class WelcomePage extends JPanel {
    private JLabel title;
    private JProgressBar progressBar;

    public WelcomePage() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Library System");
        progressBar = new JProgressBar();
        progressBar.setValue(0);
    }

    private void styleComponents() {
        setBackground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        progressBar.setMaximumSize(new Dimension(250, 30));
        progressBar.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(60));
        add(progressBar);
        add(Box.createVerticalGlue());
        
        startLoading();
    }

    private void startLoading() {
        Timer timer = new Timer(40, e -> {
            int value = progressBar.getValue();

            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                ((Timer)e.getSource()).stop();
                Navigator.navigateTo(new SignupPage());
            }
        });

        timer.start();
    }
}
