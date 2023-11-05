package sqllibrary.controller;

import sqllibrary.dao.BookDAO;
import sqllibrary.entity.Book;
import sqllibrary.entity.BookStatus;
import sqllibrary.entity.Student;

import java.sql.Connection;
import java.util.List;

public class BookController {
    private BookDAO bookDAO;

    public BookController(Connection connection) {
        bookDAO = new BookDAO(connection);
    }

    // Step 2: Implement the method to add a new book
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    // Step 3: Implement the method to search for books
    public List<Book> searchBooks(String keyword) {
        return bookDAO.searchBooks(keyword);
    }

    // Step 4: Implement the method to update the status of a book
    public void updateBookStatus(int bookId, BookStatus status) {
        bookDAO.updateBookStatus(bookId, status);
    }

    // Step 5: Implement the method to display borrowed books by a student
    public List<Book> displayBorrowedBooks(Student student) {
        // Use the BookDAO method to get borrowed books by the student
        // Implement this method based on your application's logic
        return bookDAO.getBorrowedBooksByStudent(student.getStudentId());
    }
}

