package login;

public class BookReviewer extends UserModel {
    String reviewerName;
    String companyName;

    public BookReviewer(String username, String password,
                        String reviewerName, String companyName) {
        super(username, password);
        this.reviewerName = reviewerName;
        this.companyName = companyName;
    }

    public BookReviewer(String username, String password, String email,
                        String reviewerName, String companyName) {
        super(username, password, email);
        this.reviewerName = reviewerName;
        this.companyName = companyName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
