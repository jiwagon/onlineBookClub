package onlinebookclub;

import database.dbConnect;
import discussion.DiscussionBoardInterface;
import login.LoginView;
import login.UserModel;
import review.ReviewView;
import search.BookModel;
import search.SearchBookController;
import purchase.ShoppingCartView;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class HomePageView extends JDialog {
    private JPanel contentPane;
    private JButton shoppingCartButton;
    private JButton reviewButton;
    private JButton discussionButton;
    private JButton searchButton;
    private JLabel welcomeLabel;
    private JTextField searchField;
    //private JTextArea resultTextArea;
    private JComboBox filterComboBox;
    private JButton clearButton;
    private JPanel searchPanel;
    private JLabel Filter;
    private JScrollPane resultScrollPane;
    private JButton buttonOK;
    private final ArrayList<BookModel> bookModels;
    private boolean isLoggedIn;
    private LoginView loginView;
    private dbConnect dbConnection;
    private SearchBookController searchController;
    private int titleCounter = 0;

    private JPanel resultsPanel;
    private JButton addToCartButton;
    PreparedStatement statement;

    public HomePageView(ArrayList<BookModel> bookModels) {
        this.bookModels = bookModels;
        this.dbConnection = new dbConnect();

        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Online Book Club");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        filterComboBox.addItem("Search All");
        filterComboBox.addItem("Filter by Title");
        filterComboBox.addItem("Filter by Author");
        filterComboBox.addItem("Filter by Price");
        filterComboBox.addItem("Filter by Genre");

        //resultTextArea.setEditable(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        discussionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                DiscussionBoardInterface discussionBoardInterface = new DiscussionBoardInterface();
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                ReviewView reviewView = new ReviewView();
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                ShoppingCartView shoppingCartView = new ShoppingCartView();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                HomePageView homePageView = new HomePageView(new ArrayList<>());
                homePageView.setLoggedIn(true);
            }
        });

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultScrollPane.setViewportView(resultsPanel);

        isLoggedIn = false;
        loginView = new LoginView(this);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        if (loggedIn) {
            loginView.dispose();
            this.setVisible(true);
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private void performSearch() {
        String searchTerm = searchField.getText();
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        SearchBookController controller = new SearchBookController(dbConnection);
        List<BookModel> results = controller.performSearch(searchTerm, selectedFilter);
        displayResults(results);
    }

    private void displayResults(List<BookModel> results) {
        resultsPanel.removeAll(); // Clear previous results
        if (results.isEmpty()) {
            JTextArea emptyResult = new JTextArea("No results found.");
            emptyResult.setEditable(false);
            resultsPanel.add(emptyResult);
        } else {
            for (BookModel bookModel : results) {
                JTextArea bookTextArea = new JTextArea();
                bookTextArea.setText(
                        "Title: " + bookModel.getTitle() + "\n" +
                                "Author: " + bookModel.getAuthor() + "\n" +
                                "Price: $" + bookModel.getPrice() + "\n" +
                                "Genre: " + bookModel.getGenre() + "\n");
                bookTextArea.setEditable(false);

                JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.addActionListener(e -> addToCart(bookModel));

                JPanel bookPanel = new JPanel();
                bookPanel.setLayout(new BorderLayout());
                bookPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                bookPanel.add(bookTextArea, BorderLayout.CENTER);
                bookPanel.add(addToCartButton, BorderLayout.SOUTH);

                resultsPanel.add(bookPanel);
            }
        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            index += substring.length();
            count++;
        }
        return count;
    }

    private void updateShoppingCartButtonText() {
        if (titleCounter > 0) {
            shoppingCartButton.setText("Shopping Cart (" + titleCounter + ")");
        } else {
            shoppingCartButton.setText("Shopping Cart");
        }
    }

//    private void addToCart(BookModel bookModel) {
//        String username = UserModel.getCurrentUser().getUsername();
//
//        String sql = "INSERT INTO ShoppingCart (CustomerUsername, BookTitle, BookAuthor, BookPrice, BookGenre) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql)) {
//
//            statement.setString(1, username);
//            statement.setString(2, bookModel.getTitle());
//            statement.setString(3, bookModel.getAuthor());
//            statement.setDouble(4, bookModel.getPrice());
//            statement.setString(5, bookModel.getGenre());
//
//            int row = statement.executeUpdate();
//            if (row > 0) {
//                JOptionPane.showConfirmDialog(null, "Book added to cart!", "Notification", JOptionPane.DEFAULT_OPTION);
//                updateShoppingCartButtonText();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    private void addToCart(BookModel bookModel) {
        String username = UserModel.getCurrentUser().getUsername();

        String sql = "INSERT INTO ShoppingCart (CustomerUsername, BookTitle, BookAuthor, BookPrice, BookGenre) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, bookModel.getTitle());
            statement.setString(3, bookModel.getAuthor());
            statement.setDouble(4, bookModel.getPrice());
            statement.setString(5, bookModel.getGenre());

            int row = statement.executeUpdate();
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Book added to cart!");
                // Update any necessary UI components here if needed
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error adding book to cart.");
        }
    }



    public static void main(String[] args) {
        ArrayList<BookModel> bookModels = new ArrayList<>();

        SwingUtilities.invokeLater(() -> {
            HomePageView homePageView = new HomePageView(bookModels);
            homePageView.setVisible(false);
        });
    }
}
