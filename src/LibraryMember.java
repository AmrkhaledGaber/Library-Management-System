import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private static List<LibraryMember> members = new ArrayList<>();

    private int memberId;
    private String name;
    private String contactInfo;
    private List<Book> booksBorrowed;
    private String username;
    private String password;

    // Constructor for member registration
    public LibraryMember(String name, String contactInfo, String username, String password) {
        this.memberId = generateMemberId();
        this.name = name;
        this.contactInfo = contactInfo;
        this.booksBorrowed = new ArrayList<>();
        this.username = username;
        this.password = password;
        members.add(this); // Add the member to the list
    }

    // Getters

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getUsername() {
        return username;
    }

    // Method to authenticate member login
    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    // Method to generate a unique member ID
    private static int generateMemberId() {
        // Your implementation to generate a unique ID
        return members.size() + 1;
    }

    // Method to display the books currently borrowed by the member
    public void displayBorrowedBooks() {
        if (booksBorrowed.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + "'s Borrowed Books:");
            for (Book book : booksBorrowed) {
                System.out.println(book);
            }
        }
    }

    // Method to borrow a book
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            booksBorrowed.add(book);
            book.setAvailabilityStatus(false);
            System.out.println(name + " has borrowed the book: " + book.getTitle());

            // Create a new loan when a book is borrowed
            Loan loan = new Loan(book.getBookId(), this.getMemberId());
            loan.borrowBook();
        } else {
            System.out.println("Sorry, the book " + book.getTitle() + " is currently not available.");
        }
    }

    // Method to return a book
    public void returnBook(Book book) {
        if (booksBorrowed.contains(book)) {
            booksBorrowed.remove(book);
            book.setAvailabilityStatus(true);
            System.out.println(name + " has returned the book: " + book.getTitle());

            // Find the corresponding loan and update the return date
            Loan loan = Loan.findLoanForBook(book, this); // Replace with your actual method
            if (loan != null) {
                loan.returnBook();
            }
        } else {
            System.out.println("Error: This book was not borrowed by " + name);
        }
    }

    // Method to view all members
    public static List<LibraryMember> getAllMembers() {
        return members;
    }
    public Book findBorrowedBookByTitle(String title) {
        for (Book book : booksBorrowed) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found in borrowed books
    }
}

