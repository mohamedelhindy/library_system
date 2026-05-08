package library_system;

import javax.swing.*;

import library_system.pages.WelcomePage;
import library_system.utils.Navigator;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library System");

        frame.setSize(800, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Navigator.initialize(frame);
        frame.add(new WelcomePage());

        frame.setVisible(true);
    }
}
