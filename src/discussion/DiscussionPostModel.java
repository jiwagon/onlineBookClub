package discussion;
import login.UserModel;

import java.util.ArrayList;
import java.util.Scanner;

public class DiscussionPostModel {
    private String userModelName;
    private String postTitle;
    private String mainPost;
    private String postReplies;
    private ArrayList<String> discussionThreadList;

    Scanner scanner = new Scanner(System.in);

    /**
     * This is the default controller for the DiscussionPostModel
     * @param userModelName username associated with the post
     * @param postTitle title associated with the post
     * @param mainPost main text of the post
     */

    public DiscussionPostModel (String userModelName, String postTitle, String mainPost){
        this.userModelName = userModelName;
        this.postTitle = postTitle;
        this.mainPost = mainPost;
    }

    public DiscussionPostModel (String postTitle, String mainPost){
        this.postTitle = postTitle;
        this.mainPost = mainPost;
    }

    public DiscussionPostModel (){
    }


    /**
     * Returns the username associated with a discussion post
     * @return a string representing the username associated with the post
     **/
    public String getUserName() {
        return userModelName;
    }

    /**
     * Returns the title associated with a discussion post
     * @return a string representing the title associated with the post
     **/
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the title of the post
     **/
    public void setPostTitle() {
        this.postTitle = scanner.nextLine();
    }

    /**
     * Returns the main discussion post
     * @return a string representing the main discussion post
     **/
    public String getMainPost() {
        return mainPost;
    }

    /**
     * Sets the main discussion post
     **/
    public void setMainPost() {
        this.mainPost = scanner.nextLine();
    }

    /**
     * Returns the replies to the main discussion post
     * @return a string representing a reply
     **/
    public String getPostReplies() {
        return postReplies;
    }

    /**
     * Sets the reply to the main discussion post
     * @param postReplies sets the reply
     */
    public void setPostReplies(String postReplies) {
        this.postReplies = postReplies;
    }

    /**
     * Returns the list of discussion posts and replies included in a thread
     * @return list of discussion posts and replies
     */
    public ArrayList<String> getDiscussionThreadList() {
        return discussionThreadList;
    }

    /**
     * Sets the list of discussion posts and replies included in a thread
     * @param discussionThreadList list of discussion posts and replies
     */
    public void setDiscussionThreadList(ArrayList<String> discussionThreadList) {
        this.discussionThreadList = discussionThreadList;
    }
}
