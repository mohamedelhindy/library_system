package library_system.pages;

import javax.swing.*;

import java.awt.*;

public class WelcomePage extends JPanel {
    private JProgressBar progressBar;

    public WelcomePage(JFrame frame) {
        setBackground(Color.BLACK);

        JLabel title = new JLabel("Library System");

        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        progressBar = new JProgressBar();

        progressBar.setValue(0);
        progressBar.setMaximumSize(new Dimension(250, 30));
        progressBar.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(60));
        add(progressBar);
        add(Box.createVerticalGlue());
        
        startLoading(frame);
    }

    private void startLoading(JFrame frame) {
        Timer timer = new Timer(40, e -> {
            int value = progressBar.getValue();

            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                ((Timer)e.getSource()).stop();
                frame.dispose();
            }
        });

        timer.start();
    }
}
