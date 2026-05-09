package library_system;

import javax.swing.JFrame;

import library_system.pages.WelcomePage;
import library_system.services.LibrarySystem;
import library_system.utils.Navigator;

public class Main {

    public static LibrarySystem library = new LibrarySystem();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library System");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Navigator.initialize(frame);
        Navigator.navigateTo(new WelcomePage());

        frame.setVisible(true);
    }
}