package AptechBankOnline;

import java.sql.*;


    import java.sql.*;
import java.util.Scanner;

    public class BankApp {

        public static Connection getMyConnection() throws SQLException {
            String hostName = "localhost";  // Use "localhost" instead of "localHost"
            String dbName = "aptechbank";
            String userName = "root";
            String password = "";
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);
            return connection;
        }

        public static void createCustomer(int id, String name) throws SQLException {
            Connection connection = getMyConnection();
            String query = "INSERT INTO customer (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            int count = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + count);
            connection.close();
        }

        public static void getDataCustomers() throws SQLException {
            Connection connection = getMyConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("Customer ID: " + id + ", Name: " + name);
            }
            connection.close();
        }

        public static void updateCustomer(int id, String newName) throws SQLException {
            Connection connection = getMyConnection();
            String query = "UPDATE customer SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            int count = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + count);
            connection.close();
        }

        public static void deleteCustomer(int id) throws SQLException {
            Connection connection = getMyConnection();
            String query = "DELETE FROM customer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + count);
            connection.close();
        }

        public static void findCustomerById(int id) throws SQLException {
            Connection connection = getMyConnection();
            String query = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println("Customer ID: " + id + ", Name: " + name);
            }
            connection.close();
        }

    }

