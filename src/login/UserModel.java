package login;


import database.dbConnect;

import java.sql.ResultSet;

public class UserModel {
    private String username;
    private String password;
    private String email;
    private String accountType;
    private static UserModel currentUser;


    /**
     * This is the default constructor for the Login class
     **/
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public UserModel(String username, String email, String accountType){
        this.username = username;
        this.email = email;
        this.accountType = accountType;
    }

    /**
     * This gets the username of the user
     * @return username of the account
     */
    public String getUsername() {
        return username;
    }

    /**
     * This sets the username of the user
     * @param username of the account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This gets the username of the user
     * @return password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * This sets the password of the user
     * @param password of the account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This gets the email of the user
     * @return email of the account
     */
    public String getEmail() {
        return email;
    }

    /**
     * This sets the email of the user
     * @param email of the account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }



    public static void setCurrentUser(UserModel user) {
        currentUser = user;
    }

    /**
     * Get the current user.
     * @return The current user.
     */
    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static UserModel getUserDetailsFromDatabase(String username) {
        dbConnect db = new dbConnect();
        try {
            ResultSet rs = db.returnResult("SELECT CustomerUsername, Email, AccountType FROM Accounts " +
                    "WHERE CustomerUsername = '" + username + "'");

            if (rs.next()) {
                String email = rs.getString("Email");
                String accountType = rs.getString("AccountType");

                UserModel user = new UserModel(username, email, accountType);
                user.setAccountType(accountType);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // User not found in the database
    }


}