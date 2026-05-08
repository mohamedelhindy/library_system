package library_system.components;

import java.awt.*;
import javax.swing.*;

import library_system.utils.Navigator;

import library_system.pages.LoginPage;
import library_system.pages.Dashboard;

public class Navbar extends JPanel {

    public Navbar() {
        styleComponents();
        layoutComponents();
    }

    private void styleComponents() {
        setBackground(new Color(20, 20, 20));
        setPreferredSize(new Dimension(180, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void layoutComponents() {
        add(Box.createVerticalStrut(40));

        addNavButton("Dashboard", e -> Navigator.navigateTo(new Dashboard()));
        addNavButton("Books", null);
        addNavButton("History", null);
        addNavButton("Profile", null);

        add(Box.createVerticalGlue());

        addNavButton("Logout", e -> Navigator.navigateTo(new LoginPage()));

        add(Box.createVerticalStrut(20));
    }

    private void addNavButton(String text, java.awt.event.ActionListener action) {
        StyledButton btn = new StyledButton(text);
        
        btn.setMaximumSize(new Dimension(160, 45));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setFont(new Font("Arial", Font.BOLD, 14));

        if (action != null) btn.addActionListener(action);

        add(Box.createVerticalStrut(8));
        add(btn);
    }
}