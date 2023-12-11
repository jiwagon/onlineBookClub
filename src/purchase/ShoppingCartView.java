package purchase;

import database.dbConnect;
import onlinebookclub.HomePageView;
import login.UserModel;
import search.BookModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

public class ShoppingCartView extends JFrame{
    private JTextArea textArea1;
    private JButton purchaseWithCardButton;
    private JPanel ShoppingCartView;
    private JButton clearCartButton;
    private JButton homeButton;


    public ShoppingCartView(){
        setContentPane(ShoppingCartView);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Shopping Cart");
        setSize(1000, 600);
        setVisible(true);
        textArea1.setEditable(false);
        dbConnect db = new dbConnect();

//        try {
//            ResultSet rs = db.returnResult("select Contents from ShoppingCart");
//            while (rs.next()) {
//                String contents = rs.getString(1);
//                textArea1.append(contents);
//
//            }
//        }catch(Exception ee) {
//            System.out.println(ee);
//        }

        // Inside ShoppingCartView constructor
//        try {
//            ResultSet rs = db.returnResult("select Contents from ShoppingCart");
//            String sql = "SELECT * FROM ShoppingCart WHERE CustomerUsername = '" + UserModel.getCurrentUser().getUsername() + "'";
//            //ResultSet rs = db.returnResult(sql);
//            while (rs.next()) {
//                String title = rs.getString("BookTitle"); // Assuming the column name is BookTitle
//                String author = rs.getString("BookAuthor"); // Assuming the column name is BookAuthor
//                double price = rs.getDouble("BookPrice"); // Assuming the column name is BookPrice
//                String genre = rs.getString("BookGenre"); // Assuming the column name is BookGenre
//                textArea1.append("Title: " + title + "\nAuthor: " + author + "\nPrice: $" + price + "\nGenre: " + genre + "\n\n");
//            }
//        } catch (Exception ee) {
//            System.out.println(ee);
//        }

        try {
            String sql = "SELECT BookTitle, BookAuthor, BookPrice, BookGenre FROM ShoppingCart WHERE CustomerUsername = '" + UserModel.getCurrentUser().getUsername() + "'";
            ResultSet rs = db.returnResult(sql);
            if (rs != null) {
                try {
                    while (rs.next()) {
                        // Process the result set
                    }
                } catch (Exception e) {
                    System.out.println("Error processing result set: " + e.getMessage());
                }
            }
            while (rs.next()) {
                String title = rs.getString("BookTitle");
                String author = rs.getString("BookAuthor");
                double price = rs.getDouble("BookPrice");
                String genre = rs.getString("BookGenre");
                //String contents = rs.getString(1);
                //textArea1.append(contents);
                textArea1.append("Title: " + title + "\nAuthor: " + author + "\nPrice: $" + price + "\nGenre: " + genre + "\n\n");
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }




        purchaseWithCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserModel.getCurrentUser().getUsername();
                String shoppingCartContent = textArea1.getText();

                try{
                    String sql = "insert into BookPurchased (Contents, Username) values ('"+ shoppingCartContent +"', '"+ username +"')";
                    int row = db.updateData(sql);
                    if (row > 0) {
                        System.out.println("Purchase");
                        JOptionPane.showConfirmDialog(null, "Purchase Complete!", "Confirmation", JOptionPane.CLOSED_OPTION);
                        clearShoppingCartContents();


                    }
                }catch (Exception ee){
                    System.out.println(ee);
                }
            }
        });

        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbConnect db = new dbConnect();
                try{
                    String sql = "update ShoppingCart set Contents = NULL";
                    int row = db.updateData(sql);
                    if (row > 0) {
                        System.out.println("Cart contents removed successfully.");
                        textArea1.setText("");

                    }
                }catch(Exception ee){
                    System.out.println(ee);
                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                HomePageView homePageView = new HomePageView(new ArrayList<>());
                homePageView.setLoggedIn(true);
            }
        });


    }

    private void clearShoppingCartContents() {
        dbConnect db = new dbConnect();
        try {
            String clearCartSql = "update ShoppingCart set Contents = NULL";
            int row = db.updateData(clearCartSql);
            if (row > 0) {
                System.out.println("Cart contents removed successfully.");
                textArea1.setText("");
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

}
