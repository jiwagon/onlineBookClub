package discussion;


import login.UserModel;

public class DiscussionReplyModel {
    private UserModel userModelName;
    private String postReply;

    /**
     * This is the default constructor for DiscussionReplyModel
     * @param userModelName username associated with the reply
     * @param postReply main text of the reply
     */
    public DiscussionReplyModel(UserModel userModelName, String postReply){
        this.userModelName = userModelName;
        this.postReply = postReply;
    }

    public DiscussionReplyModel(){
    }

    public UserModel getUserName() {
        return userModelName;
    }

    public void setUserName(UserModel userModelName) {
        this.userModelName = userModelName;
    }

    public String getPostReply() {
        return postReply;
    }

    public void setPostReply(String postReply) {
        this.postReply = postReply;
    }
}