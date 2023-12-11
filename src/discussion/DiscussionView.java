package discussion;

import database.dbConnect;
import login.UserModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscussionView extends JFrame {
    private JPanel DiscussionPost;
    private JLabel DiscussionPostTitle;
    private JTextField DiscussionPostTitleText;
    private JLabel Post;
    private JTextArea PostText;
    private JButton SubmitButton;
    private JButton ClearButton;
    private JButton ViewPostsButton;
    private JButton BackButton;

    private String searchTerm;
    private dbConnect db = new dbConnect();
    private DiscussionPostModel dpm;
    private UserModel um;

    public DiscussionView(UserModel user) {
        um = user;
        setContentPane(DiscussionPost);
        setTitle("Discussion Post");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discTitle = DiscussionPostTitleText.getText();
                String discPost = PostText.getText();

                System.out.println("Username: " + um.getUsername());
                System.out.println("Title: " + discTitle);
                System.out.println("Post: " + discPost);

                try {
                    String sql = "INSERT INTO DiscussionPost (Username, Title, Post) VALUES ('" +
                            um.getUsername() + "', '" + discTitle + "', '" + discPost + "')";
                    int row = db.updateData(sql);

                    if (row > 0) {
                        System.out.println("The discussion post added successfully.");
                        JOptionPane.showMessageDialog(null, "Post added successfully!");
                    } else {
                        System.out.println("Failed to add the discussion post.");
                    }
                } catch (Exception ee) {
                    System.out.println("Error: " + ee.getMessage());
                    ee.printStackTrace();
                }
            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                DiscussionBoardInterface discussionBoardInterface = new DiscussionBoardInterface();
            }
        });

        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostText.setText("");
                DiscussionPostTitleText.setText("");
            }
        });

    }

    public DiscussionPostModel getDiscussionPost() {
        return dpm;
    }
}
