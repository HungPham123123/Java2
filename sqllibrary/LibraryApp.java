package sqllibrary;

import sqllibrary.util.DBConnection;
import sqllibrary.model.Library;
import sqllibrary.entity.Book;
import sqllibrary.entity.BookStatus;
import sqllibrary.entity.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.createConnection()) {
            Library library = new Library(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Library Management System Menu:");
                System.out.println("1. Add a new book");
                System.out.println("2. Search for books");
                System.out.println("3. Borrow a book");
                System.out.println("4. Display borrowed books by a student");
                System.out.println("5. Add a new student");
                System.out.println("6. Get a student by ID");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        // Add a new book
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        Book newBook = new Book(title, author, price, new java.util.Date(), BookStatus.CHECKED_OUT);
                        library.addBook(newBook);
                        System.out.println("Book added successfully.");
                        break;

                    case 2:
                        // Search for books
                        System.out.print("Enter search keyword: ");
                        String keyword = scanner.nextLine();
                        List<Book> searchResults = library.searchBooks(keyword);

                        if (!searchResults.isEmpty()) {
                            System.out.println("Search results:");
                            for (Book book : searchResults) {
                                System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle());
                            }
                        } else {
                            System.out.println("No books found matching the keyword.");
                        }
                        break;

                    case 3:
                        // Borrow a book
                        System.out.print("Enter the book ID to borrow: ");
                        int bookIdToBorrow = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        // Implement the logic to borrow the book, updating its status
                        // Handle cases where the book is not available or the student doesn't exist
                        // Display appropriate messages to the user
                        break;

                    case 4:
                        // Display borrowed books by a student
                        System.out.print("Enter student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        // Use the library to retrieve and display borrowed books by the student
                        // Implement this logic based on your application's requirements
                        break;

                    case 5:
                        // Add a new student
                        System.out.print("Enter student name: ");
                        String studentName = scanner.nextLine();

                        Student newStudent = new Student(studentName);
                        library.addStudent(newStudent);
                        System.out.println("Student added successfully.");
                        break;

                    case 6:
                        // Get a student by ID
                        System.out.print("Enter student ID: ");
                        int studentIdToGet = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        Student student = library.getStudentById(studentIdToGet);
                        if (student != null) {
                            System.out.println("Student found: ID " + student.getStudentId() + ", Name: " + student.getStudentName());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 7:
                        // Exit the application
                        DBConnection.closeConnection(); // Close the database connection
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
