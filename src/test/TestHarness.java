package test;

import login.LoginController;
import review.PostReviewController;
import review.ViewReviewController;
import purchase.PurchaseBookController;

public class TestHarness {
    public static void main (String[] args){
         LoginController lc = new LoginController();
         PostReviewController prc = new PostReviewController();
         ViewReviewController vrc = new ViewReviewController();
         PurchaseBookController pc = new PurchaseBookController();
    }

}
