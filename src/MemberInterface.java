import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class MemberInterface {
    private Catalog catalog;
    private PendingRequestsQueue requestsQueue;

    // Constructor
    public MemberInterface(Catalog catalog, PendingRequestsQueue requestsQueue) {
        this.catalog = catalog;
        this.requestsQueue = requestsQueue;
    }

    // Method to start the member interface
    public void startInterface(LibraryMember member) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            try {
                System.out.println("\nMember Interface Menu:");
                System.out.println("1. Browse Catalog");
                System.out.println("2. Borrow a Book");
                System.out.println("3. Return a Book");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        browseCatalog();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 0:
                        System.out.println("Exiting Member Interface. Goodbye!");
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

    // Method to browse the catalog
    private void browseCatalog() {
        List<Book> availableBooks = catalog.getAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No available books in the catalog.");
        } else {
            System.out.println("Available Books:");
            for (Book book : availableBooks) {
                System.out.println(book);
            }
        }
    }

    // Method to borrow a book
    private void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID to borrow: ");
        int bookId = scanner.nextInt();

        Book bookToBorrow = catalog.findBookById(bookId);

        if (bookToBorrow != null && bookToBorrow.isAvailable()) {
            bookToBorrow.setAvailabilityStatus(false);
            System.out.println("You have successfully borrowed the book: " + bookToBorrow.getTitle());
        } else {
            if (bookToBorrow == null) {
                System.out.println("Book not found in the catalog.");
            } else {
                System.out.println("Book is not available for borrowing.");
                // Add to pending requests
                requestsQueue.enqueueRequest(bookToBorrow);
            }
        }
    }

    // Method to return a book
    private void returnBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();

        Book bookToReturn = catalog.findBookById(bookId);

        if (bookToReturn != null && !bookToReturn.isAvailable()) {
            bookToReturn.setAvailabilityStatus(true);
            System.out.println("You have successfully returned the book: " + bookToReturn.getTitle());
            // Remove from pending requests
            requestsQueue.dequeueRequest();
        } else {
            System.out.println("Book not found or already available.");
        }
    }
}
