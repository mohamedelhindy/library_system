package library_system.models;

import java.time.LocalDate;
import java.util.List;

public class Librarian extends User {

    public Librarian(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Librarian");
    }

    public BorrowRecord issueBook(Member member, Book book) {
        if (book.isAvailable()) {
            book.removeCopy();

            BorrowRecord record = new BorrowRecord(
                    member.getBorrowedBooks().size() + 1,
                    member,
                    book,
                    LocalDate.now(),
                    LocalDate.now().plusDays(14)
            );

            member.getBorrowedBooks().add(record);

            System.out.println("Book issued successfully.");
            return record;
        }

        System.out.println("Book is not available.");
        return null;
    }

    public void returnBook(BorrowRecord record) {
        if (record == null) {
            System.out.println("Invalid record.");
            return;
        }

        if (record.getStatus().equals("Returned")) {
            System.out.println("This book is already returned.");
            return;
        }

        record.markReturned(LocalDate.now());
        record.getBook().addCopy();

        System.out.println("Book returned successfully.");
        System.out.println("Fine: " + record.getFine());
    }

    public double calculateFine(BorrowRecord record) {
        if (record == null) {
            return 0;
        }

        return record.calculateFine();
    }

    public void viewOverdueBooks(List<BorrowRecord> records) {
        boolean found = false;

        for (BorrowRecord record : records) {
            if (record.isOverdue() && record.getStatus().equals("Borrowed")) {
                System.out.println(record);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }
    }

    public void viewTransactionHistory(List<BorrowRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (BorrowRecord record : records) {
            System.out.println(record);
        }
    }
}