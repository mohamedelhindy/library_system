package library_system.components;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(JButton btn) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setMaximumSize(new Dimension(600, 50));
        setAlignmentX(CENTER_ALIGNMENT);
        
        btn.setAlignmentX(CENTER_ALIGNMENT);
        add(btn);
    }
}