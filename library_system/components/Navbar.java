package library_system.components;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.pages.LoginPage;
import library_system.pages.ProfilePage;
import library_system.pages.Dashboard;

import library_system.panels.member.MyBorrowedBooksPanel;
import library_system.panels.member.MyHistoryPanel;
import library_system.panels.member.MyReservationsPanel;
import library_system.panels.member.SearchBooksPanel;

import library_system.utils.Navigator;
import library_system.utils.Session;

public class Navbar extends JPanel {

    private JLabel logoLabel;

    public Navbar() {

        initializeComponents();
        styleComponents();
        layoutComponents();
    }

    private void initializeComponents() {

        logoLabel = new JLabel("Library");
    }

    private void styleComponents() {

        setPreferredSize(new Dimension(250, 0));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(15, 23, 42));

        setBorder(
            new EmptyBorder(35, 20, 35, 20)
        );

        logoLabel.setFont(
            new Font("Segoe UI", Font.BOLD, 30)
        );

        logoLabel.setForeground(Color.WHITE);

        logoLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );
    }

    private void layoutComponents() {

        add(logoLabel);

        add(Box.createVerticalStrut(45));

        addNavButton(
            "Dashboard",
            e -> Navigator.navigateTo(
                new Dashboard()
            )
        );

        addNavButton(
            "Profile",
            e -> Navigator.navigateTo(
                new ProfilePage()
            )
        );

        if (
            Session.getCurrentUser() != null
            &&
            Session.getCurrentUser()
                .getRole()
                .equals("Member")
        ) {

            add(Box.createVerticalStrut(25));

            JLabel memberLabel =
                new JLabel("MEMBER");

            memberLabel.setForeground(
                new Color(148, 163, 184)
            );

            memberLabel.setFont(
                new Font(
                    "Segoe UI",
                    Font.BOLD,
                    13
                )
            );

            memberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );

            add(memberLabel);

            add(Box.createVerticalStrut(15));

            addNavButton(
                "Search Books",
                e -> Navigator.navigateTo(
                    new SearchBooksPanel()
                )
            );

            addNavButton(
                "Borrowed Books",
                e -> Navigator.navigateTo(
                    new MyBorrowedBooksPanel()
                )
            );

            addNavButton(
                "History",
                e -> Navigator.navigateTo(
                    new MyHistoryPanel()
                )
            );

            addNavButton(
                "Reservations",
                e -> Navigator.navigateTo(
                    new MyReservationsPanel()
                )
            );
        }

        add(Box.createVerticalGlue());

        addNavButton(
            "Logout",
            e -> {

                Session.logout();

                Navigator.navigateTo(
                    new LoginPage()
                );
            }
        );
    }

    private void addNavButton(
        String text,
        java.awt.event.ActionListener action
    ) {

        JButton button =
            new JButton(text);

        button.setMaximumSize(
            new Dimension(210, 50)
        );

        button.setPreferredSize(
            new Dimension(210, 50)
        );

        button.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                15
            )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
            new Color(30, 41, 59)
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        button.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        button.setBorder(
            new EmptyBorder(
                12,
                20,
                12,
                20
            )
        );

        button.addMouseListener(
            new java.awt.event.MouseAdapter() {

                public void mouseEntered(
                    java.awt.event.MouseEvent evt
                ) {

                    button.setBackground(
                        new Color(52, 152, 219)
                    );
                }

                public void mouseExited(
                    java.awt.event.MouseEvent evt
                ) {

                    button.setBackground(
                        new Color(30, 41, 59)
                    );
                }
            }
        );

        button.addActionListener(action);

        add(button);

        add(Box.createVerticalStrut(14));
    }
}