package sqllibrary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection createConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "thuvien";
        String userName = "root";
        String password = "";
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

    public static void closeConnection() {
    }
}
