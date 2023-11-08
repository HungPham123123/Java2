package AptechLibrary.ui;

import AptechLibrary.entity.book;
import AptechLibrary.model.BookDao;
import AptechLibrary.model.BookDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class usermenu {
    private final Scanner sc;
    private BookDao bookDao;

    public usermenu(BookDao bookDao) throws IOException {
        this.sc = new Scanner(System.in);
        this.bookDao = bookDao;
    }

    private int usermenu() {
        System.out.println("--- User Menu ---");
        System.out.println("1. Borrow Book");
        System.out.println("2. View All Book");
        System.out.println("3. Search Book By Name");
        System.out.println("0. Logout");
        int choice = readInt(0, 2);
        return choice;
    }

    public void start() throws SQLException {
        while (true) {
            int choice = usermenu();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    BorrowBook(choice);
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    ViewBookByName();
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

    private void BorrowBook(int userID) throws SQLException {

        int bookID = getBookID();

        bookDao.borrowBook(userID, bookID);
    }

    private int getBookID() {
        System.out.print("Enter the Book ID you want to borrow: ");
        return readInt(1, Integer.MAX_VALUE);
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

}


