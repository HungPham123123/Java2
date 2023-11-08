package AptechLibrary.ui;

import AptechLibrary.entity.book;
import AptechLibrary.entity.ticket;
import AptechLibrary.model.BookDao;
import AptechLibrary.model.BookDaoImpl;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class staffmenu {
    private final Scanner sc;
    private BookDao bookDao;


    public staffmenu(BookDao bookDao) throws IOException {
        this.sc = new Scanner(System.in);
        this.bookDao = bookDao;
    }


    private int staffmenu() {
        System.out.println("--- Staff Menu ---");
        System.out.println("1. View All Book");
        System.out.println("2. Search Book By Name");
        System.out.println("3. Search Book By ID");
        System.out.println("4. View Book Detail");
        System.out.println("5. Borrow History");
        System.out.println("6. Update Book Status");
        System.out.println("0. Logout");
        int choice = readInt(0, 7);
        return choice;
    }

    public void start() throws SQLException {
        while (true) {
            int choice = staffmenu();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    ViewBookByName();
                    break;
                case 3:
                    ViewBookByID();
                    break;
                case 4:
                    ViewTicket();
                    break;
                case 5:
                    ViewBorrowHistory();
                    break;
                case 6:
                    UpdateBookStatus();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return choice;
    }


    private void viewAllBooks() throws SQLException {
        book books = new book();
        String allBooksInfo = bookDao.CheckAllBook(books);
        System.out.println(allBooksInfo);
    }

    private void ViewBookByName() throws SQLException {
        book books = new book();

        System.out.print("Enter the book name: ");
        String bookName = sc.nextLine();
        books.setBookName(bookName);

        String searchResults = bookDao.SearchBookByName(books);

        // Display the search results
        if (searchResults.isEmpty()) {
            System.out.println("No books found with the provided name.");
        } else {
            System.out.println(searchResults);
        }
    }


    private void ViewBookByID() throws SQLException {
        book books = new book();

        System.out.print("Enter the book ID: ");
        int bookID = Integer.parseInt(sc.nextLine());
        books.setBookID(bookID);

        String searchResults = bookDao.SearchBookByID(books);

        if (searchResults.isEmpty()) {
            System.out.println("No books found with the provided ID.");
        } else {
            System.out.println(searchResults);
        }
    }

    private void ViewTicket() throws SQLException {
        ticket tickets = new ticket();
        String viewAllTicket = bookDao.ViewBookDetails(tickets);
        System.out.println(viewAllTicket);
    }

    private void ViewBorrowHistory() throws SQLException {

        System.out.print("Enter the book ID to view its borrow history: ");
        int bookID = readInt(1, Integer.MAX_VALUE); 

        book books = new book();
        books.setBookID(bookID);

        String borrowHistory = bookDao.BorrowHistory(books);

        if (borrowHistory.isEmpty()) {
            System.out.println("No borrow history found for the book with the provided ID.");
        } else {
            System.out.println(borrowHistory);
        }
    }


    private void UpdateBookStatus() throws SQLException {
        // Input the book ID
        System.out.print("Enter the Book ID: ");
        int bookID = readInt(1, Integer.MAX_VALUE); 

        boolean newBorrowStatus;
        while (true) {
            System.out.print("Enter the new borrow status (true/false): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("true")) {
                newBorrowStatus = true;
                break;
            } else if (input.equalsIgnoreCase("false")) {
                newBorrowStatus = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }

        book books = new book();
        books.setBookID(bookID);
        books.setBorrow(newBorrowStatus);

        String updateResult = bookDao.UpdateBookStatus(books);
        System.out.println(updateResult);
    }

}
