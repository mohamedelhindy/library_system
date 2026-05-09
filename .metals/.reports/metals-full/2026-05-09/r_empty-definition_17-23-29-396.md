error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/ProfilePage.java:java/awt/Font#PLAIN.
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/ProfilePage.java
empty definition using pc, found symbol in pc: java/awt/Font#PLAIN.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 992
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/ProfilePage.java
text:
```scala
package library_system.pages;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {

    public ProfilePage() {
        setLayout(null);
        setBackground(new Color(25, 45, 65));

        JLabel title = new JLabel("Profile Page");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(280, 80, 300, 50);
        add(title);

        JLabel name = new JLabel("Name: Guest");
        name.setFont(new Font("Arial", Font.PLAIN, 18));
        name.setForeground(Color.WHITE);
        name.setBounds(280, 170, 300, 30);
        add(name);

        JLabel email = new JLabel("Email: guest@email.com");
        email.setFont(new Font("Arial", Font.PLAIN, 18));
        email.setForeground(Color.WHITE);
        email.setBounds(280, 220, 300, 30);
        add(email);

        JLabel role = new JLabel("Role: Member");
        role.setFont(new Font("Arial", Font.PLA@@IN, 18));
        role.setForeground(Color.WHITE);
        role.setBounds(280, 270, 300, 30);
        add(role);
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/awt/Font#PLAIN.