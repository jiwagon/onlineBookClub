package review;

public class PostReviewInterface {

    /**
     * default constructor for PostReviewInterface
     */
    public PostReviewInterface(){

    }

    /**
     * displays interface for posting reviews
     */
    public static void displayPostReviewInterface(ReviewModel review){

        System.out.println("Created Review: \n" +

                "Book Name: "  + review.getBookName() + "\n"
                + "Rating: " + review.getReviewRating() + "\n" +
                review.getReviewBody() +"\n Test Passed");
    }
}
