package AptechLibrary.model;

import AptechLibrary.entity.book;
import AptechLibrary.entity.ticket;

import java.sql.SQLException;

public interface BookDao {

    /* Staff */
    String CheckAllBook(book books) throws SQLException;
    public String SearchBookByName(book books) throws SQLException;
    public String SearchBookByID(book books) throws SQLException;
    public String ViewBookDetails(ticket books) throws SQLException;
    public String BorrowHistory(book books) throws SQLException;
    public String UpdateBookStatus(book books) throws SQLException;

    /* User */
    public void borrowBook(int userID, int bookID) throws SQLException;

}
