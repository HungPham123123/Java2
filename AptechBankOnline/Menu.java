package AptechBankOnline;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu extends BankApp{
    public static void displayMenu() {
        System.out.println("*** Welcome to Aptech Bank Online ***");
        System.out.println("1. Create new Customer");
        System.out.println("2. Update customer");
        System.out.println("3. Delete customer");
        System.out.println("4. Find customer by id");
        System.out.println("5. Display all customers information");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        if (getMyConnection() != null) {
            System.out.println("Connect success");

            while (true) {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter customer ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter customer name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        createCustomer(id, name);
                        break;
                    case 2:
                        System.out.print("Enter customer ID to update: ");
                        int updateId = scanner.nextInt();
                        System.out.print("Enter new customer name: ");
                        scanner.nextLine();
                        String newName = scanner.nextLine();
                        updateCustomer(updateId, newName);
                        break;
                    case 3:
                        System.out.print("Enter customer ID to delete: ");
                        int deleteId = scanner.nextInt();
                        deleteCustomer(deleteId);
                        break;
                    case 4:
                        System.out.print("Enter customer ID to find: ");
                        int findId = scanner.nextInt();
                        findCustomerById(findId);
                        break;
                    case 5:
                        getDataCustomers();
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } else {
            System.out.println("Connect Fail");
        }
    }

}
