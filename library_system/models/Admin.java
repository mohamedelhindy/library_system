package library_system.models;

import java.util.List;

public class Admin extends User {
    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Admin");
    }

    public void addBook(Book book, List<Book> books) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void editBook(String ISBN, String newTitle, List<Book> books) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                book.setTitle(newTitle);
                System.out.println("Book updated successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void removeBook(String ISBN, List<Book> books) {
        boolean removed = books.removeIf(book -> book.getISBN().equals(ISBN));

        if (removed) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void addUser(User user, List<User> users) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    public void removeUser(int userId, List<User> users) {
        boolean removed = users.removeIf(user -> user.getUserId() == userId);

        if (removed) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void assignRole(int userId, String role, List<User> users) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                user.setRole(role);
                System.out.println("Role assigned successfully.");
                return;
            }
        }
        // user not found
        System.out.println("User not found.");
    }
}