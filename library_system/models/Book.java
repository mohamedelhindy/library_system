package library_system.models;

import java.util.Objects;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private int copiesAvailable;

    public Book(String ISBN, String title, String author, String genre, int copiesAvailable) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genre = genre;
        setCopiesAvailable(copiesAvailable);
    }
 
     public Book(String ISBN, String title, String author, int copiesAvailable) {
        this(ISBN, title, author, "General", copiesAvailable);
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        if (copiesAvailable >= 0) {
            this.copiesAvailable = copiesAvailable;
        } else {
            System.out.println("Copies available cannot be negative.");
            this.copiesAvailable = 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Book book = (Book) obj;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    @Override
    public String toString() {
        return "ISBN: " + ISBN +
                ", Title: " + title +
                ", Author: " + author +
                ", Genre: " + genre +
                ", Copies Available: " + copiesAvailable;
    }
}