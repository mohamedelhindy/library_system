package library_system;

import javax.swing.JFrame;

import library_system.models.User;
import library_system.pages.Dashboard;
// import library_system.pages.WelcomePage;
import library_system.services.LibrarySystem;

import library_system.utils.Session;
import library_system.utils.Navigator;

public class Main {

    public static LibrarySystem library = new LibrarySystem();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library System");

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Navigator.initialize(frame);
        // Navigator.navigateTo(new WelcomePage());
        Session.login(
            new User(
            1,
            "Mohamed",
            "mohamed@gmail.com",
            "123456",
            "Member"
            )
        );

        Navigator.navigateTo(new Dashboard());

        frame.setVisible(true);
    }
}