package review;

import database.dbConnect;

import java.sql.ResultSet;

public class ViewReviewController {

    dbConnect db;

    /**
     * This is the default constructor for ViewReviewController.
     */
    public ViewReviewController() {
       // ViewReviewInterface viewReview = new ViewReviewInterface();

       // viewReview.displayReviewInterface();

        getReview();
    }

    public void getReview(){

        ResultSet rs = null;

        //set body, date, title, get book, etc



    }
}
