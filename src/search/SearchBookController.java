package search;

import database.dbConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class SearchBookController {
    private dbConnect db;

    public SearchBookController(dbConnect db) {
        this.db = db;
    }

    public List<BookModel> performSearch(String searchTerm, String selectedFilter) {
        List<BookModel> results = new ArrayList<>();
        ResultSet rs = null;

        try {
            String query = "";
            if (selectedFilter.equals("Search All")) {
                // To check if searchTerm is numeric
                boolean isNumeric = searchTerm.matches("-?\\d+(\\.\\d+)?");
                String priceCondition = isNumeric ? " OR Price = " + Double.parseDouble(searchTerm) : "";
                query = "SELECT Title, Author, Price, Genre FROM Book WHERE Title LIKE '%" + searchTerm + "%' OR Author LIKE '%" + searchTerm + "%' OR Genre LIKE '%" + searchTerm + "%'" + priceCondition;
            } else if (selectedFilter.equals("Filter by Title")) {
                query = "SELECT Title, Author, Price, Genre FROM Book WHERE Title LIKE '%" + searchTerm + "%'";
            } else if (selectedFilter.equals("Filter by Price")) {
                double maxPrice = Double.parseDouble(searchTerm);
                query = "SELECT Title, Author, Price, Genre FROM Book WHERE Price <= " + maxPrice;
            } else if (selectedFilter.equals("Filter by Genre")) {
                query = "SELECT Title, Author, Price, Genre FROM Book WHERE Genre LIKE '%" + searchTerm + "%'";
            } else if (selectedFilter.equals("Filter by Author")) {
                query = "SELECT Title, Author, Price, Genre FROM Book WHERE Author LIKE '%" + searchTerm + "%'";
            }

            if (!query.isEmpty()) {
                rs = db.returnResult(query);
            }

            if (rs != null) {
                while (rs.next()) {
                    String title = rs.getString(1);
                    String author = rs.getString(2);
                    double price = rs.getDouble(3);
                    String genre = rs.getString(4);
                    BookModel searchedBook = new BookModel(title, author, price, genre);
                    results.add(searchedBook);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for price: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return results;
    }
}
