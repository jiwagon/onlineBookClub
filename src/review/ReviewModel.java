package review;

import search.BookModel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewModel {

    public String username;
    public int reviewRating;
    public String reviewBody;
    public BookModel book;
    public String bookName;
    Scanner scanner = new Scanner(System.in);

    static ArrayList<ReviewModel> bookReviews = new ArrayList<>();

    /**
     * This is the default constructor for a review.
     * @param username the username of the review's poster
     * @param reviewRating the rating given by the review's poster
     * @param reviewBody the body written by the review's poster
     */
    public ReviewModel(String username, int reviewRating, String reviewBody, BookModel book) {
        this.username = username;
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.book = book;
    }

    public ReviewModel() {
    }

    /**
     * Gets the body of the review
     * @return Returns the body of the review
     */
    public String getReviewBody() {
        return reviewBody;
    }


    /**
     * Sets the body of the review
     */
    public void setReviewBody() {
        this.reviewBody = scanner.nextLine();
    }

    /**
     * Gets the rating of the review
     * @return Returns the rating of the review
     */
    public int getReviewRating() {
        return reviewRating;
    }

    /**
     * Sets the rating of the review
     */
    public void setReviewRating() {
        this.reviewRating = scanner.nextInt();
        if(this.reviewRating > 5){
            System.out.println("Too high. Pick a number between 1 and 5");
            setReviewRating();
        }
        else if(this.reviewRating < 1){
            System.out.println("Too low. Pick a number between 1 and 5");
            setReviewRating();
        }

    }

    /**
     * Gets book for review
     * @return returns the book for the review
     */
    public BookModel getBook(){
        return book;
    }

    public String getBookName(){
        return this.bookName;
    }
    public void setBookName(){
        this.bookName = scanner.nextLine();
    }

    public static ReviewModel getBookReview(int indexNumber){
        ReviewModel bookReview = bookReviews.get(indexNumber);

        return bookReview;
    }

    public void addBookReviewToArray(ReviewModel review){
        bookReviews.add(review);

    }
}