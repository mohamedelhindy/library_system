package library_system;

import javax.swing.JFrame;
import library_system.models.Book;
import library_system.models.Member;
import library_system.models.Admin;
import library_system.models.Librarian;
import library_system.pages.WelcomePage;
import library_system.services.LibrarySystem;
import library_system.utils.Navigator;

public class Main {
    public static LibrarySystem library = new LibrarySystem();

    public static void main(String[] args) {

        // seed some books
        library.getBooksList().add(new Book("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Novel", 3));
        library.getBooksList().add(new Book("9780451524935", "1984", "George Orwell", "Dystopian", 5));
        library.getBooksList().add(new Book("9780061935466", "To Kill a Mockingbird", "Harper Lee", "Fiction", 2));
        library.getBooksList().add(new Book("9780132350884", "Clean Code", "Robert C. Martin", "Technology", 4));
        library.getBooksList().add(new Book("9780201633610", "Design Patterns", "Gang of Four", "Technology", 2));

        // seed test users
        library.getUsersList().add(new Member(1, "Alice", "alice@email.com", "1234"));
        library.getUsersList().add(new Member(2, "Bob", "bob@email.com", "1234"));
        library.getUsersList().add(new Librarian(3, "Sara", "sara@email.com", "1234"));
        library.getUsersList().add(new Admin(4, "Admin", "admin@email.com", "1234"));

        JFrame frame = new JFrame("Library System");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Navigator.initialize(frame);
        Navigator.navigateTo(new WelcomePage());

        frame.setVisible(true);
    }
}
