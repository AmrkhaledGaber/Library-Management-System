import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibrarianInterface {
    private Catalog catalog;
    private PendingRequestsQueue requestsQueue;
    private MemberRepository memberRepository;

    // Constructor
    public LibrarianInterface(Catalog catalog, PendingRequestsQueue requestsQueue) {
        this.catalog = catalog;
        this.requestsQueue = requestsQueue;
    }

    // Method to start the librarian interface
    public void startInterface() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            try {
                System.out.println("\nLibrarian Interface Menu:");
                System.out.println("1. Add New Book");
                System.out.println("2. Update Book Details");
                System.out.println("3. View Book Loans Status");
                System.out.println("4. Display Pending Requests");
                System.out.println("5. Manage Book Returns");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        updateBookDetails();
                        break;
                    case 3:
                        viewBookLoansStatus();
                        break;
                    case 4:
                        displayPendingRequests();
                        break;
                    case 5:
                        manageBookReturns();
                        break;
                    case 0:
                        System.out.println("Exiting Librarian Interface. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set an invalid choice to continue the loop
            }

        } while (choice != 0);
    }

    // Method to add a new book
    private void addNewBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        Book newBook = new Book(bookId, title, author, genre);
        catalog.addBook(newBook);
        System.out.println("Book added to catalog: " + newBook.getTitle());
    }

    // Method to update book details
    private void updateBookDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID to update: ");
        int bookId = scanner.nextInt();

        Book bookToUpdate = catalog.findBookById(bookId);

        if (bookToUpdate != null) {
            scanner.nextLine(); // Consume newline

            System.out.print("Enter New Title: ");
            String newTitle = scanner.nextLine();
            bookToUpdate.setTitle(newTitle);

            System.out.print("Enter New Author: ");
            String newAuthor = scanner.nextLine();
            bookToUpdate.setAuthor(newAuthor);

            System.out.print("Enter New Genre: ");
            String newGenre = scanner.nextLine();
            bookToUpdate.setGenre(newGenre);

            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found in the catalog.");
        }
    }

    // Method to display pending requests
    private void displayPendingRequests() {
        if (requestsQueue.isQueueEmpty()) {
            System.out.println("No pending book requests at the moment.");
        } else {
            System.out.println("Pending Book Requests:");
            while (!requestsQueue.isQueueEmpty()) {
                Book requestedBook = requestsQueue.dequeueRequest();
                System.out.println("Book ID " + requestedBook.getBookId() + " requested.");
            }
        }
    }

    // Method to manage book returns
    private void manageBookReturns() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();

        Book returnedBook = catalog.findBookById(bookId);

        if (returnedBook != null) {
            requestsQueue.dequeueRequest(); // Remove from pending requests
            returnedBook.setAvailabilityStatus(true);
            System.out.println("Book with ID " + bookId + " has been successfully returned.");
        } else {
            System.out.println("Book not found in the catalog.");
        }
    }

    // Method to view book loans status
    private void viewBookLoansStatus() {
        List<Loan> loans = Loan.getLoans();

        if (loans.isEmpty()) {
            System.out.println("No book loans at the moment.");
        } else {
            System.out.println("Book Loans Status:");
            for (Loan loan : loans) {
                Book book = catalog.findBookById(loan.getBookId());
                Member member = memberRepository.findMemberById(loan.getMemberId());

                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Borrower: " + member.getName());
                System.out.println("Issue Date: " + loan.getIssueDate());
                System.out.println("Due Date: " + loan.getDueDate());
                System.out.println("Return Date: " + (loan.getReturnDate() != null ? loan.getReturnDate() : "Not returned"));
                System.out.println("--------");
            }
        }
    }
}
