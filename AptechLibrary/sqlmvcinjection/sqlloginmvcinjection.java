package AptechLibrary.sqlmvcinjection;

import AptechLibrary.ui.loginConsole;

import java.io.IOException;
import java.sql.SQLException;

public class sqlloginmvcinjection {

    public static void main(String[] args) throws IOException, SQLException {
        loginConsole lc = new loginConsole();
        lc.start();
    }
}
