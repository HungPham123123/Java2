package sqllibrary.dao;

import sqllibrary.entity.Book;
import sqllibrary.entity.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    // Step 2: Implement the method to add a new book
    public void addBook(Book book) {
        String sql = "INSERT INTO sach (TenSach, MaTacGia, Gia, NgayNhap, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(book.getDateAdded().getTime()));
            preparedStatement.setString(5, book.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 3: Implement the method to search for books
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM sach WHERE TenSach LIKE ? OR MaTacGia IN (SELECT MaTacGia FROM tacgia WHERE TenTacGia LIKE ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("MaSach");
                String title = resultSet.getString("TenSach");
                int authorId = resultSet.getInt("MaTacGia");
                double price = resultSet.getDouble("Gia");
                java.util.Date dateAdded = resultSet.getDate("NgayNhap");
                String status = resultSet.getString("TrangThai");
                BookStatus bookStatus = BookStatus.valueOf(status);
                books.add(new Book(bookId, title, authorId, price, dateAdded, bookStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Step 4: Implement the method to update the book status
    public void updateBookStatus(int bookId, BookStatus status) {
        String sql = "UPDATE sach SET TrangThai = ? WHERE MaSach = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 5: Implement the method to get borrowed books by student (if applicable)
    public List<Book> getBorrowedBooksByStudent(int studentId) {
        List<Book> borrowedBooks = new ArrayList<>();
        // Your SQL query to retrieve borrowed books by a specific student
        // Implement this method based on your database structure
        return borrowedBooks;
    }
}
