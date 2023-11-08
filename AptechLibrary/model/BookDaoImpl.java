

package AptechLibrary.model;

import AptechLibrary.dao.DBConnection;
import AptechLibrary.entity.book;
import AptechLibrary.entity.ticket;

import java.sql.*;
import java.util.Calendar;

public class BookDaoImpl implements BookDao {


    /* Staff */
    @Override
    public String CheckAllBook(book books) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT * FROM book";
        StringBuilder result = new StringBuilder();

        try (PreparedStatement ps = conn.prepareStatement(dbQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                String bookName = rs.getString("bookName");
                String Author = rs.getString("author");
                boolean borrow = rs.getBoolean("borrow");

                result.append("---- Book Data ----").append("\n");
                result.append("Book ID: ").append(bookID).append("\n");
                result.append("Book Name: ").append(bookName).append("\n");
                result.append("Author: ").append(Author).append("\n");
                result.append("borrow: ").append(borrow).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public String SearchBookByName(book books) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT * FROM book WHERE bookName LIKE ?";
        StringBuilder result = new StringBuilder();

        try (PreparedStatement ps = conn.prepareStatement(dbQuery)) {
            ps.setString(1, "%" + books.getBookName() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                String bookName = rs.getString("bookName");
                String Author = rs.getString("author");

                result.append("---- Book Data ----").append("\n");
                result.append("Book ID: ").append(bookID).append("\n");
                result.append("Book Name: ").append(bookName).append("\n");
                result.append("Author: ").append(Author).append("\n");
                result.append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @Override
    public String SearchBookByID(book books) throws SQLException {
        {
            Connection conn = DBConnection.createConnection();
            String dbQuery = "SELECT * FROM book WHERE bookID LIKE ?";
            StringBuilder result = new StringBuilder();

            try (PreparedStatement ps = conn.prepareStatement(dbQuery)) {
                ps.setString(1, "%" + books.getBookID() + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String bookName = rs.getString("bookName");
                    String Author = rs.getString("author");

                    result.append("---- Book Data ----").append("\n");
                    result.append("Book ID: ").append(bookID).append("\n");
                    result.append("Book Name: ").append(bookName).append("\n");
                    result.append("Author: ").append(Author).append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result.toString();
        }
    }

    @Override
    public String ViewBookDetails(ticket books) throws SQLException {

        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT * FROM ticket";
        StringBuilder result = new StringBuilder();

        try (PreparedStatement ps = conn.prepareStatement(dbQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int ticketID = rs.getInt("ticketID");
                int userID = rs.getInt("userID");
                int bookID = rs.getInt("bookID");
                Date borrowDate = rs.getDate("borrowDate");
                Date returnDate = rs.getDate("returnDate");

                result.append("---- Ticket Data ----").append("\n");
                result.append("Ticket ID: ").append(ticketID).append("\n");
                result.append("User ID: ").append(userID).append("\n");
                result.append("Book ID: ").append(bookID).append("\n");
                result.append("Borrow Date: ").append(borrowDate).append("\n");
                result.append("Return Date: ").append(returnDate).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public String BorrowHistory(book books) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "SELECT * FROM ticket WHERE bookID = ?";
        StringBuilder result = new StringBuilder();

        try (PreparedStatement ps = conn.prepareStatement(dbQuery)) {
            ps.setInt(1, books.getBookID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ticketID = rs.getInt("ticketID");
                int bookID = rs.getInt("bookID");
                Date borrowDate = rs.getDate("borrowDate");
                Date returnDate = rs.getDate("returnDate");

                result.append("---- Ticket Data ----").append("\n");
                result.append("Ticket ID: ").append(ticketID).append("\n");
                result.append("Book ID: ").append(bookID).append("\n");
                result.append("Borrow Date: ").append(borrowDate).append("\n");
                result.append("Return Date: ").append(returnDate).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }



    @Override
    public String UpdateBookStatus(book books) throws SQLException {
        Connection conn = DBConnection.createConnection();
        String dbQuery = "UPDATE book SET borrow = ? WHERE bookID = ?";
        int updatedRows = 0;

        try (PreparedStatement ps = conn.prepareStatement(dbQuery)) {
            ps.setBoolean(1, books.getBorrow());
            ps.setInt(2, books.getBookID());
            updatedRows = ps.executeUpdate();
        }

        StringBuilder result = new StringBuilder();
        if (updatedRows > 0) {
            result.append("Updated status successfully");
        } else {
            result.append("Update failed status");
        }

        return result.toString();
    }


    /* User */

    @Override
    public void borrowBook(int userID, int bookID) throws SQLException {
        Connection conn = DBConnection.createConnection();

        Date borrowDate = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.MONTH, 2);
        Date returnDate = new Date(calendar.getTimeInMillis());

        String dbQuery = "INSERT INTO ticket (userID, bookID, borrowDate, returnDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(dbQuery)) {
            ps.setInt(1, userID);
            ps.setInt(2, bookID);
            ps.setDate(3, borrowDate);
            ps.setDate(4, returnDate);

            int updatedRows = ps.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Failed to borrow the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
