package AptechLibrary.ui;

import AptechLibrary.controller.LoginController;
import AptechLibrary.entity.staff;
import AptechLibrary.entity.users;
import AptechLibrary.model.BookDaoImpl;
import AptechLibrary.model.LoginDao;
import AptechLibrary.model.LoginDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class loginConsole {
    private final Scanner sc;
    LoginController loginController = new LoginController();

    public loginConsole() throws IOException {
        this.sc = new Scanner(System.in);
    }

    private int menu() {
        System.out.println("--- Login Menu ---");
        System.out.println("1. Login");
        System.out.println("0. Exit");
        int choice = readInt(0, 2);
        return choice;
    }

    public void start() throws SQLException, IOException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    LoginPage();
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

    private void LoginPage() throws SQLException, IOException {
        LoginDao loginDao = new LoginDaoImpl();
        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            users user = new users();
            user.setUsername(username);
            user.setPassword(password);

            staff staff = new staff();
            staff.setUsername(username);
            staff.setPassword(password);

            String userResult = loginDao.checkUserLogin(user);
            String staffResult = loginDao.checkStaffLogin(staff);

            if (!userResult.equals("not in the database")) {
                loggedIn = true;
                displayUserMenu();
            } else if (!staffResult.equals("not in the database")) {
                loggedIn = true;
                try {
                    displayStaffMenu();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Login failed. User not found. Please try again.");
                System.out.print("Press 1 to try again or 2 to return to the main menu: ");
                int choice = readInt(1, 2);
                if (choice == 2) {
                    break;
                }
            }
        }
    }

    private void displayUserMenu() throws IOException, SQLException {
        usermenu userMenu = new usermenu(new BookDaoImpl());
        userMenu.start();
    }

    private void displayStaffMenu() throws IOException, SQLException {
        staffmenu staffMenu = new staffmenu(new BookDaoImpl()); // Provide an instance of BookDao implementation
        staffMenu.start();
    }

}

