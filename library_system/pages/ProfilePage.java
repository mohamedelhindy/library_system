package library_system.pages;

<<<<<<< HEAD
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import library_system.components.Navbar;
import library_system.models.User;
import library_system.utils.Session;

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

        User currentUser =
            Session.getCurrentUser();

        usernameLabel =
            new JLabel(
                "Username: "
                +
                currentUser.getName()
            );

        emailLabel =
            new JLabel(
                "Email: "
                +
                currentUser.getEmail()
            );

        roleLabel =
            new JLabel(
                "Role: "
                +
                currentUser.getRole()
            );
    }

    private void styleComponents() {

        setBackground(
            new Color(15, 23, 42)
        );

        title.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                52
            )
        );

        title.setForeground(Color.WHITE);

        title.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        Font infoFont =
            new Font(
                "Segoe UI",
                Font.PLAIN,
                22
            );

        JLabel[] labels = {
            usernameLabel,
            emailLabel,
            roleLabel
        };

        for (JLabel label : labels) {

            label.setFont(infoFont);

            label.setForeground(
                new Color(226, 232, 240)
            );

            label.setAlignmentX(
                Component.CENTER_ALIGNMENT
            );
        }
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(
            new Navbar(),
            BorderLayout.WEST
        );

        JPanel content = new JPanel() {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d =
            (Graphics2D) g.create();

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

        content.setLayout(
            new GridBagLayout()
        );

        JPanel card =
            new JPanel();

        card.setBackground(
            new Color(30, 41, 59)
        );

        card.setLayout(
            new BoxLayout(
                card,
                BoxLayout.Y_AXIS
            )
        );

        card.setBorder(
            new EmptyBorder(
                40,
                60,
                40,
                60
            )
        );

        card.setPreferredSize(
            new Dimension(500, 320)
        );

        title.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        usernameLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        emailLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        roleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        card.add(title);

        card.add(
            Box.createVerticalStrut(40)
        );

        card.add(usernameLabel);

        card.add(
            Box.createVerticalStrut(25)
        );

        card.add(emailLabel);

        card.add(
            Box.createVerticalStrut(25)
        );

        card.add(roleLabel);

        content.add(card);

        add(
            content,
            BorderLayout.CENTER
        );
    }
}
=======
import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {

    public ProfilePage() {
        setLayout(null);
        setBackground(new Color(25, 45, 65));

        JLabel title = new JLabel("Profile Page");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(280, 80, 300, 50);
        add(title);

        JLabel name = new JLabel("Name: Guest");
        name.setFont(new Font("Arial", Font.PLAIN, 18));
        name.setForeground(Color.WHITE);
        name.setBounds(280, 170, 300, 30);
        add(name);

        JLabel email = new JLabel("Email: guest@email.com");
        email.setFont(new Font("Arial", Font.PLAIN, 18));
        email.setForeground(Color.WHITE);
        email.setBounds(280, 220, 300, 30);
        add(email);

        JLabel role = new JLabel("Role: Member");
        role.setFont(new Font("Arial", Font.PLAIN, 18));
        role.setForeground(Color.WHITE);
        role.setBounds(280, 270, 300, 30);
        add(role);
    }
}
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d
