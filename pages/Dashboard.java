package library_system.pages;

import java.awt.*;
import javax.swing.*;

import library_system.components.StyledButton;
import library_system.utils.Navigator;

public class Dashboard extends JPanel {

    private JLabel title;
    private JLabel welcomeLabel;

    private StyledButton booksBtn;
    private StyledButton historyBtn;
    private StyledButton profileBtn;
    private StyledButton logoutBtn;

    public Dashboard() {
        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        title = new JLabel("Dashboard");
        welcomeLabel = new JLabel("Welcome back!");

        booksBtn = new StyledButton("Books");
        historyBtn = new StyledButton("History");
        profileBtn = new StyledButton("Profile");
        logoutBtn = new StyledButton("Logout");
    }

    private void styleComponents() {
        setBackground(Color.BLACK);

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomeLabel.setForeground(Color.LIGHT_GRAY);
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);

        Dimension btnSize = new Dimension(200, 50);
        booksBtn.setMaximumSize(btnSize);
        historyBtn.setMaximumSize(btnSize);
        profileBtn.setMaximumSize(btnSize);
        logoutBtn.setMaximumSize(btnSize);

        booksBtn.setAlignmentX(CENTER_ALIGNMENT);
        historyBtn.setAlignmentX(CENTER_ALIGNMENT);
        profileBtn.setAlignmentX(CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(CENTER_ALIGNMENT);

        logoutBtn.setBackground(new Color(180, 30, 30));
    }

    private void layoutComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(10));
        add(welcomeLabel);
        add(Box.createVerticalStrut(50));
        add(booksBtn);
        add(Box.createVerticalStrut(15));
        add(historyBtn);
        add(Box.createVerticalStrut(15));
        add(profileBtn);
        add(Box.createVerticalStrut(30));
        add(logoutBtn);
        add(Box.createVerticalGlue());

        addListeners();
    }

    private void addListeners() {
        logoutBtn.addActionListener(e -> Navigator.navigateTo(new LoginPage()));
    }
}
