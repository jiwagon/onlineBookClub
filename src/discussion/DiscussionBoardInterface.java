package discussion;

import database.dbConnect;
import login.UserModel;
import onlinebookclub.HomePageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DiscussionBoardInterface extends JFrame {
    private JPanel DiscussionBoard;
    private JButton createADiscussionPostButton;
    private JButton replyToAPostButton;
    private JPanel DiscussionInterface;
    private JButton BackToHome;
    private JComboBox CBdiscussionPosts;
    private JTextArea discussionPostBody;
    private JTextArea discussionReplysDisplay;
    private JTextArea discussionPostTitle;

    private DiscussionView discussionView;
    private DiscussionReplyInterface discussionReplyInterface;
    private dbConnect dbc = new dbConnect();
    private DiscussionPostModel dpm;
    private ArrayList<DiscussionPostModel> dps = new ArrayList<>();
    private UserModel um = UserModel.getCurrentUser();

    /**
     * This is the default constructor for the DiscussionBoard class
     **/
    public DiscussionBoardInterface() {
        setContentPane(DiscussionInterface);
        setTitle("Discussion Board");
        setSize(850, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            String postSql = "SELECT dp.Post, dp.Title, dp.ID FROM DiscussionPost dp";
            ResultSet postResultSet = dbc.returnResult(postSql);

            while (postResultSet.next()) {
                String title = postResultSet.getString(2);
                CBdiscussionPosts.addItem(title);

                System.out.println("Added title to JComboBox: " + title);
            }
        } catch (Exception ee) {
            System.out.println("Exception while populating JComboBox: " + ee.getMessage());
            ee.printStackTrace();
        }

        CBdiscussionPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedTitle = CBdiscussionPosts.getSelectedItem().toString();

                try {

                    String getIdSql = "SELECT Username, ID, Title FROM DiscussionPost WHERE Title = '" + selectedTitle + "'";
                    ResultSet idResultSet = dbc.returnResult(getIdSql);

                    if (idResultSet.next()) {
                        int postId = idResultSet.getInt("ID");
                        String postTitle = idResultSet.getString("Title");
                        String username = idResultSet.getString("Username");

                        // Display the selected post's title and body
                        discussionPostTitle.setText("(" + username + ")" + postTitle);
                        String getBodySql = "SELECT Post FROM DiscussionPost WHERE ID = " + postId;
                        ResultSet bodyResultSet = dbc.returnResult(getBodySql);
                        if (bodyResultSet.next()) {
                            String postBody = bodyResultSet.getString("Post");
                            discussionPostBody.setText(postBody);
                        }

                        String replySql = "SELECT dr.Username, dr.Reply FROM DiscussionReply dr " +
                                "WHERE dr.ParentPostID = " + postId;
                        ResultSet replyResultSet = dbc.returnResult(replySql);

                        // Display replies
                        discussionReplysDisplay.setText("");
                        while (replyResultSet.next()) {
                            String replyUsername = replyResultSet.getString("Username");
                            String reply = replyResultSet.getString("Reply");
                            discussionReplysDisplay.append("(" + replyUsername + ") " + reply + "\n");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });


        createADiscussionPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                dispose();
                discussionView = new DiscussionView(um);
                ///discussionView.getDiscussionPost();
            }
        });

        replyToAPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = CBdiscussionPosts.getSelectedItem().toString();

                setVisible(false);
                dispose();
                discussionReplyInterface = new DiscussionReplyInterface(um, selectedTitle);
                discussionReplyInterface.getDiscussionReply();
            }
        });

        BackToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                HomePageView homePageView = new HomePageView(new ArrayList<>());
                homePageView.setLoggedIn(true);
            }
        });
    }

    public DiscussionPostModel getDiscussionBoard() {
        return dpm;
    }
}
