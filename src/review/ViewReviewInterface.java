package review;

public class ViewReviewInterface {

    /**
     * This is the default constructor for ViewReviewInterface.
     */
    public ViewReviewInterface() {
    }

    /**
     * This displays the contents of the reviews.
     */
    public void displayReviewInterface(){
        System.out.println("Displaying Review 1...");

        ReviewModel review = ReviewModel.getBookReview(0);

        System.out.println("Book Name: " + review.getBookName() + "\n"
            + "Book Rating: " + review.getReviewRating() + "\n" +
            review.getReviewBody()
        + "\n Test Passed");
    }
}
