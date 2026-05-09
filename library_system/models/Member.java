package library_system.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<BorrowRecord> borrowedBooks;

    public Member(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Member");
        borrowedBooks = new ArrayList<>();
    }

    public List<Book> searchBook(String keyword, List<Book> books) {
        List<Book> result = new ArrayList<>();

        if (keyword == null || books == null) {
            return result;
        }

        String lowerKeyword = keyword.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lowerKeyword)
                    || book.getAuthor().toLowerCase().contains(lowerKeyword)
                    || book.getGenre().toLowerCase().contains(lowerKeyword)
                    || book.getISBN().toLowerCase().contains(lowerKeyword)) {
                result.add(book);
            }
        }

        return result;
    }

    public void borrowBook(Book book) {
        if (book == null) {
            System.out.println("Invalid book.");
            return;
        }

        if (book.isAvailable()) {
            book.removeCopy();

            BorrowRecord record = new BorrowRecord(
                    borrowedBooks.size() + 1,
                    this,
                    book,
                    LocalDate.now(),
                    LocalDate.now().plusDays(14)
            );

            borrowedBooks.add(record);
            System.out.println("Book borrowed successfully: " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (book == null) {
            System.out.println("Invalid book.");
            return;
        }

        for (BorrowRecord record : borrowedBooks) {
            if (record.getBook().equals(book) && record.getStatus().equals("Borrowed")) {
                record.markReturned(LocalDate.now());
                book.addCopy();

                System.out.println("Book returned successfully: " + book.getTitle());
                System.out.println("Fine: " + record.getFine());
                return;
            }
        }

        System.out.println("No active borrow record found for this book.");
    }

    public void viewBorrowHistory() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrow history found.");
            return;
        }

        for (BorrowRecord record : borrowedBooks) {
            System.out.println(record);
        }
    }

    public void addBorrowRecord(BorrowRecord record) {
        if (record != null) {
            borrowedBooks.add(record);
        }
    }

    public List<BorrowRecord> getBorrowedBooks() {
        return borrowedBooks;
    }
}