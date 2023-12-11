package discussion;
import javax.swing.SwingUtilities;

import login.UserModel;


public class DiscussionBoardController {
    private DiscussionBoardInterface discussionBoardInterface;

    public DiscussionBoardController() {
        this.discussionBoardInterface = new DiscussionBoardInterface();
        this.discussionBoardInterface.getDiscussionBoard();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiscussionBoardController());
    }
}