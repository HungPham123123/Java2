package AptechLibrary.model;

import AptechLibrary.dao.DBConnection;
import AptechLibrary.entity.staff;
import AptechLibrary.entity.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginDaoImpl implements LoginDao{
    private static final Connection conn;

    static {
        try {
            conn = DBConnection.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement pstm =null;


    @Override
    public String checkUserLogin(users user) {
        String dbQuery = "SELECT username FROM users WHERE username like ? and password like ?";
        return checkLogin(user, dbQuery);
    }



    @Override
    public String checkStaffLogin(staff staff) {
        String dbQuery = "SELECT username FROM staff WHERE username like ? and password like ?";
        return checkLogin(staff, dbQuery);
    }

    public String checkLogin(users user, String dbQuery) {
        try {
            pstm = conn.prepareStatement(dbQuery);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                return username;
            }
            return "not in the database";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "false";
        }
    }
    public String checkLogin(staff staff, String dbQuery) {
        try {
            pstm = conn.prepareStatement(dbQuery);
            pstm.setString(1, staff.getUsername());
            pstm.setString(2, staff.getPassword());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                return username;
            }
            return "not in the database";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "false";
        }
    }



}
