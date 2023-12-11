package test;

import static org.junit.jupiter.api.Assertions.*;

import database.dbConnect;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;

public class dbConnectTest {

    @Test
    public void testConnectionAndData() {
        dbConnect db = new dbConnect();
        ResultSet rs = null;
        try {
            rs = db.returnResult("SELECT Title, Author, Price, Genre FROM Book");
            int rowCount = 0;
            while (rs.next() && rowCount < 3) {
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                Double price = rs.getDouble("Price");
                String genre = rs.getString("Genre");

                assertNotNull("Title is null", title);
                assertNotNull("Author is null", author);
                assertNotNull("Price is null", String.valueOf(price));
                assertNotNull("Genre is null", genre);

                System.out.println("Title: " + title + ", Author: " + author + ", Price: " + price + ", Genre: " + genre);

                rowCount++;
            }
            assertTrue(rowCount == 3, "Less than 3 rows in the result set");

        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}