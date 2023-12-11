package login;

import java.util.Scanner;

public class LoginInterface {

    /**
     * This is the default constructor for LoginInterface
     */
    public LoginInterface() {

    }

    public static void main(String[] args) {

        Reader r1 = new Reader("johndoe", "password123", "John Doe",
                2023);
        Author a1 = new Author ("tarawestover", "abc123", "Tara Westover",
                "Random House");
        BookReviewer br1 = new BookReviewer("alicesmith", "book200",
                "Alice Smith", "Kirkus");

        Scanner s = new Scanner(System.in);
        System.out.println("Enter user name: ");
        String username = s.next();
        System.out.println("Enter password: ");
        String password = s.next();
        System.out.println("Enter unique id: ");
        int uniqueID = s.nextInt();

        if (username.equals(r1.getUsername()) && password.equals(r1.getPassword())){
            System.out.println("Reader Authenticated!");
        }
        else if (username.equals(a1.getUsername()) && password.equals(a1.getPassword())){
            System.out.println("Author Authenticated!");
        }
        else if (username.equals(br1.getUsername()) && password.equals(br1.getPassword())) {
            System.out.println("Book Reviewer Authenticated!");
        }
    }

}