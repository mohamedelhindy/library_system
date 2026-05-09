package library_system.utils;

import javax.swing.*;

public class Navigator {
    private static JFrame frame;

    public static void initialize(JFrame f) {
        frame = f;
    }

    public static void navigateTo(JPanel page) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(page);
        frame.revalidate();
        frame.repaint();
    }
}
