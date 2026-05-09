package library_system.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord {
    private static final double FINE_PER_DAY = 5.0;

    private int recordId;
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private String status;

    public BorrowRecord(int recordId, Member member, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fine = 0.0;
        this.status = "Borrowed";
    }

    public boolean isOverdue() {
        LocalDate checkDate = (returnDate == null) ? LocalDate.now() : returnDate;
        return checkDate.isAfter(dueDate);
    }

    public long getLateDays() {
        LocalDate checkDate = (returnDate == null) ? LocalDate.now() : returnDate;

        if (checkDate.isAfter(dueDate)) {
            return ChronoUnit.DAYS.between(dueDate, checkDate);
        }

        return 0;
    }

    public double calculateFine() {
        long lateDays = getLateDays();
        fine = lateDays * FINE_PER_DAY;
        return fine;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = "Returned";
        calculateFine();
    }

    public int getRecordId() {
        return recordId;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getFine() {
        return fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFine(double fine) {
        if (fine >= 0) {
            this.fine = fine;
        }
    }

    @Override
    public String toString() {
        String memberName = (member != null) ? member.getName() : "Unknown Member";
        String bookTitle = (book != null) ? book.getTitle() : "Unknown Book";

        return "Record ID: " + recordId +
                ", Member: " + memberName +
                ", Book: " + bookTitle +
                ", Borrow Date: " + borrowDate +
                ", Due Date: " + dueDate +
                ", Return Date: " + returnDate +
                ", Fine: " + fine +
                ", Status: " + status;
    }
}