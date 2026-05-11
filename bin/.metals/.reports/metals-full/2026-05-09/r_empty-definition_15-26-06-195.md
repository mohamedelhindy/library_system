error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/services/UserService.java:java/sql/ResultSet#next().
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/services/UserService.java
empty definition using pc, found symbol in pc: java/sql/ResultSet#next().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 669
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/services/UserService.java
text:
```scala
package library_system.services;

import library_system.Main;
import library_system.models.Admin;
import library_system.models.Librarian;
import library_system.models.Member;
import library_system.models.User;

public class UserService {

    public static boolean emailExists(String email) {
<<<<<<< HEAD:library_system/services/UserService.java
        for (User user : Main.library.getUsersList()) {
            if (user.getEmail().equalsIgnoreCase(email)) return true;
=======
        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.n@@ext();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d:bin/.metals/.reports/metals-full/2026-05-09/r_empty-definition_15-26-06-195.md
        }
        return false;
    }

    public static boolean signup(String name, String email, String password, String role) {
        if (emailExists(email)) return false;

        int newId = Main.library.getUsersList().size() + 1;

        switch (role) {
            case "Admin"     -> Main.library.getUsersList().add(new Admin(newId, name, email, password));
            case "Librarian" -> Main.library.getUsersList().add(new Librarian(newId, name, email, password));
            default          -> Main.library.getUsersList().add(new Member(newId, name, email, password));
        }
        return true;
    }
<<<<<<< HEAD:library_system/services/UserService.java

    public static User login(String email, String password) {
        for (User user : Main.library.getUsersList()) {
            if (user.login(email, password)) return user;
        }
        return null;
    }
}
=======
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/sql/ResultSet#next().
>>>>>>> 6b818e3aa263f46240aed7212eafb2959ab4ac4d:bin/.metals/.reports/metals-full/2026-05-09/r_empty-definition_15-26-06-195.md
