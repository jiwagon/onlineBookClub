package discussion;

import database.dbConnect;
import login.UserModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DiscussionReplyInterface extends JFrame {
    private JTextField DiscussionPostTitleText;
    private JLabel Post;
    private JTextArea PostText;
    private JButton SubmitButton;
    private JButton ClearButton;
    private JButton BackButton;
    private JPanel DiscussionReply;

    private dbConnect db = new dbConnect();
    private DiscussionReplyModel dpm;
    private UserModel um;
    private String parentPostTitle;

    public DiscussionReplyInterface(UserModel user, String parentPostTitle) {
        um = user;
        this.parentPostTitle = parentPostTitle;
        setContentPane(DiscussionReply);
        setTitle("Discussion Reply");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discPost = PostText.getText();
                try {
                    String getParentPostIdSql = "SELECT ID FROM DiscussionPost WHERE Title = '" + parentPostTitle + "'";
                    ResultSet parentPostIdResultSet = db.returnResult(getParentPostIdSql);

                    if (parentPostIdResultSet.next()) {
                        int parentPostId = parentPostIdResultSet.getInt("ID");

                        String sql = "INSERT INTO DiscussionReply (Username, ParentPostID, Reply) VALUES ('" +
                                um.getUsername() + "', " + parentPostId + ", '" + discPost + "')";
                        int row = db.updateData(sql);

                        if (row > 0) {
                            System.out.println("The discussion reply added successfully.");
                            JOptionPane.showMessageDialog(null, "Reply added successfully!");
                        }
                    }
                } catch (Exception ee) {
                    System.out.println(ee);
                }
            }
        });

        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostText.setText("");
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
    }

    public DiscussionReplyModel getDiscussionReply() {
        return dpm;
    }
}
