package library_system.utils;

import library_system.models.User;

public class Session {
    private static User currentUser = null;

    public static void login(User user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getRole() {
        if (currentUser == null) return "";
        return currentUser.getRole();
    }

    public static String getName() {
        if (currentUser == null) return "";
        return currentUser.getName();
    }
}
