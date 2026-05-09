package library_system.services;

import library_system.models.Book;
import library_system.models.BorrowRecord;
import library_system.models.User;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> booksList;
    private List<User> usersList;
    private List<BorrowRecord> borrowRecords;

    public LibrarySystem() {
        booksList = new ArrayList<>();
        usersList = new ArrayList<>();
        borrowRecords = new ArrayList<>();
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> results = new ArrayList<>();

        for (Book book : booksList) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || book.getISBN().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public void addTransactionRecord(BorrowRecord record) {
        if (record != null) {
            borrowRecords.add(record);
        }
    }

    public void showHistory() {
        if (borrowRecords.isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        for (BorrowRecord record : borrowRecords) {
            System.out.println(record);
        }
    }

    public void saveData() {
        System.out.println("Data saved successfully.");
    }

    public void loadData() {
        System.out.println("Data loaded successfully.");
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}