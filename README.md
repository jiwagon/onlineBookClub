# OnlineBookSubscription
***IST 412 Project***<br>

## Use Cases
**To access our use cases, go to HomePageView under the OnlineBookClub and run the main method at the bottom.** <br><be>

### Use Case 0: Login 
This use case allows users like Reader, Author, or Book Reviewer to log in using their username and passcode to access the book club's homepage. <be>

### Use Case 1: Search 
In the first use case, logged-in users can search for a book using a selected filter based on book title, author, genre, or price, with the default being searching all books without any filter. <be>

### Use Case 2: Purchase
In the second use case, logged-in users can purchase searched books using the search view. They can view their items in the shopping cart where the transaction is done. They can also add their payment information in the purchase view as well.<be>

### Use Case 3: Discussion
In the third use case, logged-in users can discuss books with other users. They can post a main post with a title and body. They can also reply to posts as well. <be>

### Use Case 4: Review 
In the fourth use case, logged-in users can view reviews for various books. Logged-in reviewers can create a review for a book. <be>


## Login Usernames, Passwords, and UniqueIDs
### For Reader: 
- username: johndoe
- password: password123

### For Author:
- username: stevenking
- password: abc123

### For Book Reviewer: 
#### Book Reviewer 1: 
- username: alicesmith
- password: book200
#### Book Reviewer 2: 
- username: bobjoe
- password: password234
  

## Books
ID	Title	Author	Price	IISBN	Genre <br>
1	Eragon	Christopher Paolini	8.99	9780375890369	Fantasy <br>
2	The Da Vinci Code	Dan Brown	9.99	9780307474278	Mystery<br>
3	To Kill a Mockingbird	Harper Lee	9.99	9780061120084	Fiction<br>
4	The Great Gatsby	F. Scott Fitzgerald	12.99	9780743273565	Fiction<br>
5	Pride and Prejudice	Jane Austen	7.99	9780486284736	Romance<br>
6	Brave New World	Aldous Huxley	14.99	9780060850524	Fiction<br>
7	The Road	Cormac McCarthy	16.99	9780307387899	Fiction<br>
8	Educated: A Memoir	Tara Westover	17.5	9780399590504	Non-Fiction<br>
9	Circe	Madeline Miller	12.99	9780316556347	Fantasy<br>
10	Dune	Frank Herbert	9.99	9780441172719	Sci-Fi<br>
11	It	Stephen King	19.99	9780450411434	Horror<br>
12	The Shining	Stephen King	18.99	9780385121675	Horror<br>
13	Outliers	Malcolm Gladwell	29.99	9780316017930	Non-Fiction<br>
14	Blink	Malcolm Gladwell	17	9780316010665	Non-Fiction<br>
15	Harry Potter and the Sorcerer's Stone	J.K. Rowling	24.99	9780439708180	Fantasy<br>
16	Harry Potter and the Chamber of Secrets	J.K. Rowling	24.99	9780439064873	Fantasy<br>
17	11/22/63	Stephen King	35	9781451627282	Sci-Fi<br>
18	The Tipping Point	Malcolm Gladwell	17	9780316346627	Non-Fiction<br>
19	Harry Potter and the Goblet of Fire	J.K. Rowling	29.99	9780439139601	Fantasy<br>
20	Carrie	Stephen King	7.99	9780385086950	Horror<br>
21	Green Eggs and Ham	Dr. Seuss	9.99	9780394800165	Children's<br>
22	The Cat in the Hat	Dr. Seuss	8.99	9780394800011	Children's<br>
23	Oh, the Places You'll Go!	Dr. Seuss	17.99	9780679805274	Children's<br>
24	Emma	Jane Austen	7	9780486406480	Romance<br>
25	Sense and Sensibility	Jane Austen	9	9780141439662	Romance<br>
26	How the Grinch Stole Christmas!	Dr. Seuss	15	9780394800790	Children's<br>
27	Mansfield Park	Jane Austen	6	9781853260322	Fiction<br>
28	Horton Hears a Who!	Dr. Seuss	14.99	9780394800783	Children's<br>
29	Northanger Abbey	Jane Austen	6	9780486414126	Romance<br>

## Team Involvement Log
### Implemented Use Case 4 and Final Implementations

mzc5994 - Mackenzie Cane: <br> 

- **20%:**  Helped to fix the discussion board and shopping cart code. 
  
ake5280 - Amelia Emahizer: <br> 

- **30%:** Created ReviewView and PostReviewView. Wrote the code for users to view various book reviews. Wrote code for book reviews to add a new review for a book. Helped to fix the discussion board and shopping cart code. Updated database with any changes we needed.

ajf6238 - Aidan Fitz: <br> 

- **20%**: Helped to fix shopping cart code.
  
jcf5517 - Jack Fitzgerald: <br> 

- **20%**: Helped to fix discussion board code. 
  
jkz5262 - Ji Zhang: <br>

- **20%:** Added SearchView code into HomePageView. 

### Implemented Use Cases 2 and 3

mzc5994 - Mackenzie Cane: <br> 

- **10%:**  Created LoginView form; Wrote LoginView code to connect the view to the database; Created OnlineBookSubscription class to call LoginController, which contains LoginView Frame; Edited code for others. Contributed to the README.txt file.
  
ake5280 - Amelia Emahizer: <br> 

- **10%:** Updated our database and tables within; Added user and search information into the created tables; Updated HomePageView form to ensure display accuracy; Edited HomePageView code to connect LoginView and SearchView. Edited code for others. 

ajf6238 - Aidan Fitz: <br> 

- **30%**: Created ShoppingCartView and SubscriptionView forms. Connected to the database to display added shopping cart materials into the shopping cart. Allowed users to store payment info on the purchase screen with an add/delete/update table.
  
jcf5517 - Jack Fitzgerald: <br> 

- **30%**:Created DiscussionBoardController that connects the discussion post input box and discussion replies input box and connects it to our database; Created three forms DiscussionView, DiscussionReplyInterface, and DiscussionBoardInterface; DiscussionPostModel and DiscussionReplyModel are utilized in those interfaces 
  
jkz5262 - Ji Zhang: <br>

- **10%:** Updated SearchView form; Moved search algorithm codes into SearchController and refined them; Connected search algorithm to the book database. Edited and updated HomePageView code to seamlessly utilize functions from the Search package. Edited code for others. Contributed to the README.txt file.

### Implemented Use Case 1

mzc5994 - Mackenzie Cane: <br> 

- **30%:**  Created LoginView form; Wrote LoginView code to connect the view to the database; Created OnlineBookSubscription class to call LoginController, which contains LoginView Frame; Added user information for reader, author, and book reviewer. Contributed to the README.txt file.
  
ake5280 - Amelia Emahizer: <br> 

- **30%:** Created our database and tables within; Added user and search information into the created tables; Created a HomePageView form to connect LoginView and SearchView; Wrote HomePageView code to connect LoginView and SearchView. Edited code for others. Added several books to the arraylist.

ajf6238 - Aidan Fitz: <br> 

- **10%:** Supported team members during assignment; Will be responsible for coding one of the next use cases.
  
jcf5517 - Jack Fitzgerald: <br> 

- **10%:** Created DiscussionBoardController that connects the discussion post input box and discussion replies input box and connects it to our database; Created three forms DiscussionView, DiscussionReplyInterface, and DiscussionBoardInterface; DiscussionPostModel and DiscussionReplyModel are utilized in those interfaces.
  
jkz5262 - Ji Zhang: <br>

- **30%:** Created SearchView form; Wrote SearchView & SearchController code to connect the view to the list of books; Added book information that is utilized in the code. Wrote HomePageView code to connect LoginView and SearchView. Edited code for others. Added book data to the arraylist. Finalized the codes and ensured accurate functionalities. Contributed to the README.txt file.


## Refactoring Implementation
* All members were involved in different parts of the implementation. 

1. The database is now connected to all parts of the code. Professor Vora helped us with the query codes. Amelia helped identify data tables and update them. Ji implemented the data connection codes into the SearchController class, and Mackenzie implemented the data connection codes into the LoginView class.
2. We have deleted unused classes such as UserFinance, OnlineBookSubscription, SocialMedia, ExternalReview, and SearchInterface.
3. We removed the unnecessary comments among the codes in HomePageView and those in Search and Login packages. Those were coded heavily previously. 
4. Mackenzie deleted the UniqueID parameter in Login. 
5. All of the view classes are consistent when it comes to code.
6. We added setter methods into model classes that initially only have getters, such as UserModel and BookModel.

## Design Patterns Implementation
mzc5994 - Mackenzie Cane: <br> 

- **Template:** Reader, Author, BookReviewer, LoginInterface
- **Input Feedback:** LoginInterface, LoginView, PurchaseBookController, PostReviewController, DiscussionBoardController, SearchView

ake5280 - Amelia Emahizer: <br> 

ajf6238 - Aidan Fitz: <br> 

- **Decorator:** PaymentDecorator, PaymentImplementation, Payment, DebitCredit, ApplePay, PayPal, ShoppingCart
- **Shopping Cart:** PurchaseBookController 

jcf5517 - Jack Fitzgerald: <br> 

jkz5262 - Ji Zhang: <be> 

- **Strategy:** BookModel, TitleSearch, AuthorSearch, Genre Search, PriceSearch, SearchBookInterface
- **Filter:** SearchBookController

