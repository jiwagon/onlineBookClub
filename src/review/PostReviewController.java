package review;

public class PostReviewController {

    private PostReviewInterface reviewInterface;

    private final ReviewModel review = new ReviewModel();


    /**
     * default constructor for PostReviewController
     */
    public PostReviewController(){
        createReview();
    }

    /**
     * @return the finished product of the review
     */
    public ReviewModel createReview(){
        this.reviewInterface = new PostReviewInterface();

        System.out.println("What is the title of the book?");
        review.setBookName();

        System.out.println("What would you rate the book out of 5?");
        review.setReviewRating();

        System.out.println("Describe your review of the book.");
        review.setReviewBody();
        review.setReviewBody();

        System.out.println("Review successfully posted");
        PostReviewInterface.displayPostReviewInterface(review);

        review.addBookReviewToArray(review);
        return review;
    }
}
