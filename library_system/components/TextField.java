package library_system.components;

import javax.swing.*;
import java.awt.*;

public class TextField extends JPanel {

    private JTextField textField;

    public TextField() {

        textField = new JTextField(20);

        textField.setMaximumSize(new Dimension(300, 40));

        setBackground(Color.BLACK);

        add(textField);
    }

    public String getText() {

        return textField.getText();
    }
}