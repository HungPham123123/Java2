package sqllibrary.entity;

import java.util.Date;

public class Book {
    private int bookId;
    private String title;
    private int authorId;
    private double price;
    private Date dateAdded;
    private BookStatus status;

    public Book(int bookId, String title, int authorId, double price, Date dateAdded, BookStatus bookStatus) {
    }

    public Book(String title, String author, double price, Date date, BookStatus bookStatus) {
        this.dateAdded = dateAdded;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
