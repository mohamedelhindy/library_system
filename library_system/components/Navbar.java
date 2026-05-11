package library_system.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.pages.*;
import library_system.panels.member.*;
import library_system.panels.librarian.*;
import library_system.panels.admin.*;

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
        setPreferredSize(new Dimension(240, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(15, 23, 42));
        setBorder(new EmptyBorder(30, 20, 30, 20));

        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void layoutComponents() {
        add(logoLabel);
        add(Box.createVerticalStrut(40));

        addNavButton("Dashboard", e -> Navigator.navigateTo(new Dashboard()));

        String role = Session.getCurrentUser().getRole();

        if (role.equals("Member")) {
            addNavButton("Search Books",   e -> Navigator.navigateTo(new SearchBooksPanel()));
            addNavButton("My Borrowed",    e -> Navigator.navigateTo(new MyBorrowedBooksPanel()));
            addNavButton("My History",     e -> Navigator.navigateTo(new MyHistoryPanel()));
            addNavButton("Reservations",   e -> Navigator.navigateTo(new MyReservationsPanel()));
        }

        else if (role.equals("Librarian")) {
            addNavButton("Borrow Book",    e -> Navigator.navigateTo(new BorrowPage()));
            addNavButton("Return Book",    e -> Navigator.navigateTo(new ReturnBookPage()));
            addNavButton("Overdue Books",  e -> Navigator.navigateTo(new OverdueBooksPanel()));
            addNavButton("Reservations",   e -> Navigator.navigateTo(new ReservationsPanel()));
        }

        else if (role.equals("Admin")) {
            addNavButton("Add Book",       e -> Navigator.navigateTo(new AddBookPanel()));
            addNavButton("Edit Book",      e -> Navigator.navigateTo(new EditBookPanel()));
            addNavButton("Users",          e -> Navigator.navigateTo(new UsersPage()));
            addNavButton("Add User",       e -> Navigator.navigateTo(new AddUserPanel()));
            addNavButton("Reports",        e -> Navigator.navigateTo(new ReportsPanel()));
        }

        add(Box.createVerticalGlue());

        addNavButton("Profile", e -> Navigator.navigateTo(new ProfilePage()));
        addNavButton("Logout",  e -> {
            Session.logout();
            Navigator.navigateTo(new LoginPage());
        });
    }

    private void addNavButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 41, 59));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { button.setBackground(new Color(52, 152, 219)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { button.setBackground(new Color(30, 41, 59)); }
        });

        button.addActionListener(action);
        add(button);
        add(Box.createVerticalStrut(12));
    }
}
