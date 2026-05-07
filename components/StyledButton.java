package library_system.components;

import javax.swing.*;
import java.awt.*;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        
        setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(new Color(50, 50, 50));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        // setMaximumSize(new Dimension(280, 50));
    }
}


