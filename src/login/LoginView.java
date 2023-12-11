package login;

import database.dbConnect;
import onlinebookclub.HomePageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginView extends JFrame {
    private JPanel LoginPanel;
    private JLabel LoginTitle;
    private JLabel UserNameTitle;
    private JLabel PasswordTitle;
    private JTextField UserNameField;
    private JButton LoginButton;
    private JPasswordField PasswordField;
    private final HomePageView homePageView;

    private String searchTerm;
    dbConnect db = new dbConnect();

    public LoginView(HomePageView homePageView) {
        this.homePageView = homePageView;
        setContentPane(LoginPanel);
        setTitle("Login");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = UserNameField.getText();
                char[] passwordChars = PasswordField.getPassword();
                String password = new String(passwordChars);

                dbConnect db = new dbConnect();
                boolean loginSuccessful = false;

                try {
                    ResultSet rs = db.returnResult("select CustomerUsername, Password, AccountType from Accounts " +
                            "where CustomerUsername = '" + login + "'");
                    while (rs.next()) {
                        if (rs.getString(2).equals(password)) {
                            loginSuccessful = true;
                            break;  // Exit the loop once a match is found
                        }
                    }

                    if (loginSuccessful) {
                        setVisible(false);
                        dispose();
                        homePageView.setLoggedIn(true);
                        System.out.println("Successful Login");

                        // Fetch account type from the database
                        String accountType = rs.getString("AccountType");

                        UserModel currentUser = new UserModel(login, password, accountType);
                        UserModel.setCurrentUser(currentUser);
                    } else {
                        System.out.println("Username and/or password does not exist");
                        JOptionPane.showMessageDialog(LoginPanel, "Username/Password incorrect. Try again.");
                    }
                } catch (Exception ee) {
                    System.out.println(ee);
                }
            }
        });
    }
}
