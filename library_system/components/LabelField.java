package library_system.components;

import javax.swing.*;
import java.awt.*;

public class LabelField extends JPanel {

    public JLabel label;

    public LabelField(String labelText) {

        label = new JLabel(labelText);

        label.setForeground(Color.WHITE);

        label.setFont(new Font("Arial", Font.BOLD, 16));

        setBackground(Color.BLACK);

        setMaximumSize(new Dimension(300, 70));

        add(label);
    }
}