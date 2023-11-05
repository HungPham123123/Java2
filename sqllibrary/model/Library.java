package sqllibrary.model;

import sqllibrary.controller.BookController;
import sqllibrary.controller.StudentController;
import sqllibrary.entity.Book;
import sqllibrary.entity.BookStatus;
import sqllibrary.entity.Student;

import java.sql.Connection;
import java.util.List;

public class Library {
    private BookController bookController;
    private StudentController studentController;

    public Library(Connection connection) {
        bookController = new BookController(connection);
        studentController = new StudentController(connection);
    }

    // Step 2: Implement methods for various library operations

    // Method to add a new book to the library
    public void addBook(Book book) {
        bookController.addBook(book);
    }

    // Method to search for books in the library
    public List<Book> searchBooks(String keyword) {
        return bookController.searchBooks(keyword);
    }

    // Method to update the status of a book when it's borrowed
    public void borrowBook(int bookId, BookStatus status) {
        bookController.updateBookStatus(bookId, status);
    }

    // Method to display borrowed books by a student
    public List<Book> displayBorrowedBooks(Student student) {
        return bookController.displayBorrowedBooks(student);
    }

    // Method to add a new student to the library
    public void addStudent(Student student) {
        studentController.addStudent(student);
    }

    // Method to get a student by their unique ID
    public Student getStudentById(int studentId) {
        return studentController.getStudentById(studentId);
    }
}
