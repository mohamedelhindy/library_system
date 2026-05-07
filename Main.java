package library_system;

import javax.swing.*;

import library_system.pages.LoginPage;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library System");

        frame.add(new LoginPage());

        frame.setSize(800, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
