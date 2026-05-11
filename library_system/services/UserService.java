package library_system.services;

import library_system.Main;
import library_system.models.Admin;
import library_system.models.Librarian;
import library_system.models.Member;
import library_system.models.User;

public class UserService {

    public static boolean emailExists(String email) {
        for (User user : Main.library.getUsersList()) {
            if (user.getEmail().equalsIgnoreCase(email)) return true;
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

    public static User login(String email, String password) {
        for (User user : Main.library.getUsersList()) {
            if (user.login(email, password)) return user;
        }
        return null;
    }
}
