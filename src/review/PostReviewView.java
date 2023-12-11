package review;

import database.dbConnect;
import login.UserModel;
import onlinebookclub.HomePageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostReviewView extends JFrame{
    private JPanel postReviews;
    private JButton homeButton;
    private JButton postButton;
    private JTextField titleInput;
    private JComboBox bookComboBox;
    private JTextArea bodyInput;
    private JTextField ratingInput;
    DefaultComboBoxModel list = new DefaultComboBoxModel();

    private dbConnect db = new dbConnect();

    public PostReviewView(){

        setContentPane(postReviews);
        setTitle("Post Reviews");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);


        populateBookList();


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                HomePageView homePageView = new HomePageView(new ArrayList<>());
                homePageView.setLoggedIn(true);
            }
        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postReview();
            }
        });


    }

    private void populateBookList() {
        String query = "SELECT Title FROM Book";
        ResultSet resultSet = db.returnResult(query);

        try {
            list.removeAllElements();

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                System.out.println("Adding book title: " + title);
                list.addElement(title);
            }

            SwingUtilities.invokeLater(() -> {
                bookComboBox.setModel(list);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void postReview() {
        String selectedBookTitle = (String) bookComboBox.getSelectedItem();
        String title = titleInput.getText();
        String body = bodyInput.getText();
        String rating = ratingInput.getText();
        String author = UserModel.getCurrentUser().getUsername();


        if (!title.isEmpty() && !body.isEmpty() && !rating.isEmpty()) {
            db.insertReview(selectedBookTitle, title, body, rating, author);

            JOptionPane.showMessageDialog(this, "Review posted successfully!");
            titleInput.setText("");
            bodyInput.setText("");
            ratingInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
        }
    }
}
