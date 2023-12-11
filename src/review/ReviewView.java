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

public class ReviewView extends JFrame{
    private JButton backToHomeButton;
    private JTextArea reviewTitle;
    private JTextArea reviewBody;
    private JTextArea reviewRating;
    private JPanel reviews;
    private JLabel Reviews;
    private JTextArea reviewBookName;
    private JButton writeAReviewButton;
    private JTextArea reviewDate;
    DefaultComboBoxModel list = new DefaultComboBoxModel();
    private JComboBox bookList;
    private JTextArea reviewUser;
    private JButton nextReviewButton;
    private JButton previousReviewButton;
    private JLabel AuthorLabel;
    private JLabel DateLabel;
    private int currentReviewIndex = 0;

    private dbConnect db = new dbConnect();

    public ReviewView(){

        setContentPane(reviews);
        setTitle("Reviews");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);


        populateBookList();


        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                HomePageView homePageView = new HomePageView(new ArrayList<>());
                homePageView.setLoggedIn(true);
            }
        });

        writeAReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserModel currentUser = UserModel.getCurrentUser();
                if (currentUser != null && "Reviewer".equals(currentUser.getAccountType())) {
                    setVisible(false);
                    dispose();
                    PostReviewView postReviewView = new PostReviewView();
                } else {
                    JOptionPane.showMessageDialog(reviews, "Only reviewers can post reviews!");
                }
            }
        });


        previousReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentReviewIndex > 0) {
                    navigateReview(-1);
                }
            }
        });

        nextReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentReviewIndex < 10) {
                    navigateReview(1);
                }
            }
        });


        bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReviewDetailsForSelectedBook();
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
                bookList.setModel(list);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void loadReviewDetailsForSelectedBook() {
        String selectedBookTitle = (String) bookList.getSelectedItem();

        String query = "SELECT * FROM Reviews WHERE BookTitle = '" + selectedBookTitle + "'";
        ResultSet resultSet = db.returnResult(query);

        try {

            reviewTitle.setText("");
            reviewBody.setText("");
            reviewRating.setText("");
            reviewDate.setText("");
            reviewUser.setText("");


            if (!resultSet.isBeforeFirst()) {
                currentReviewIndex = -1;
                reviewTitle.setText("No reviews found.");
            } else {

                int reviewCount = 0;
                while (resultSet.next()) {
                    reviewCount++;
                    if (reviewCount == currentReviewIndex + 1) {

                        reviewTitle.append("TITLE: " + resultSet.getString("Title") + "\n");
                        reviewBody.append(resultSet.getString("Body") + "\n");
                        reviewRating.append("RATING: " + resultSet.getString("Rating") + "\n");
                        reviewDate.append(resultSet.getString("DatePublished") + "\n");
                        reviewUser.append("PUBLISHED BY: " + resultSet.getString("Author") + "\n");
                    }
                }

                currentReviewIndex = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void navigateReview(int direction) {
        String selectedBookTitle = (String) bookList.getSelectedItem();

        String query = "SELECT * FROM Reviews WHERE BookTitle = '" + selectedBookTitle + "'";
        ResultSet resultSet = db.returnResult(query);

        try {
            int reviewCount = 0;
            while (resultSet.next()) {
                reviewCount++;
                if (reviewCount == currentReviewIndex + direction) {

                    reviewTitle.setText("TITLE: " + resultSet.getString("Title"));
                    reviewBody.setText(resultSet.getString("Body"));
                    reviewRating.setText("RATING: " + resultSet.getString("Rating"));
                    reviewDate.setText(resultSet.getString("DatePublished"));
                    reviewUser.setText("PUBLISHED BY: " + resultSet.getString("Author"));


                    currentReviewIndex += direction;

                    if (direction > 0 && currentReviewIndex == reviewCount) {
                        currentReviewIndex = 1;
                    }

                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}

