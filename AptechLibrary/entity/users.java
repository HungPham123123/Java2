package AptechLibrary.entity;

public class users {
    private int userID;
    private String username;
    private String password;


    public users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public users() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
