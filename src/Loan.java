import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {
    private static int nextLoanId = 1;
    private static List<Loan> loans = new ArrayList<>();

    private int loanId;
    private int bookId;
    private int memberId;
    private Date issueDate;
    private Date returnDate;
    private Date dueDate;

    // Constructor for loan initiation
    public Loan(int bookId, int memberId) {
        this.loanId = nextLoanId++;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = new Date(); // Set the issue date to the current date
        this.returnDate = null; // Return date initially set to null
        this.dueDate = calculateDueDate(); // Calculate the due date

        loans.add(this); // Add the loan to the list
    }

    // Method to calculate the due date
    private Date calculateDueDate() {
        // Improved logic to calculate the due date based on your requirements
        long loanDurationMillis = 14 * 24 * 60 * 60 * 1000; // 14 days in milliseconds
        return new Date(issueDate.getTime() + loanDurationMillis);
    }

    // Method to handle borrowing a book
    public void borrowBook() {
        // Additional logic to handle borrowing process
        System.out.println("Book with ID " + bookId + " has been borrowed by Member ID " + memberId);
    }

    // Method to handle returning a book
    public void returnBook() {
        returnDate = new Date(); // Set the return date to the current date
        System.out.println("Book with ID " + bookId + " has been returned by Member ID " + memberId);
    }


    // Getters and Setters

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Static method to get the list of loans
    public static List<Loan> getLoans() {
        return loans;
    }

    // Static method to find a loan for a specific book and member
    public static Loan findLoanForBook(Book book, LibraryMember member) {
        for (Loan loan : loans) {
            if (loan.getBookId() == book.getBookId() && loan.getMemberId() == member.getMemberId()) {
                return loan;
            }
        }
        return null;
    }
}
