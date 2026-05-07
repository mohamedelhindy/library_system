package library_system.models;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private int copiesAvailable;

    public Book(String ISBN, String title, String author, int copiesAvailable) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public boolean isAvailable() {
        return copiesAvailable > 0;
    }

    public void addCopy() {
        copiesAvailable++;
    }

    public void removeCopy() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        } else {
            System.out.println("No copies available.");
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Override
    public String toString() {
        return "ISBN: " + ISBN +
                ", Title: " + title +
                ", Author: " + author +
                ", Copies Available: " + copiesAvailable;
    }
}