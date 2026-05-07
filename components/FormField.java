package library_system.components;

import javax.swing.*;
import java.awt.*;

public class FormField extends JPanel {
    public JLabel label;
    public JTextField textField;

    public FormField(String labelText) {
        label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(LEFT_ALIGNMENT);

        textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setAlignmentX(LEFT_ALIGNMENT);

        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(300, 70));
        
        add(label);
        add(Box.createVerticalStrut(5));
        add(textField);
    }

    public String getText() {
        return textField.getText();
    }
}


