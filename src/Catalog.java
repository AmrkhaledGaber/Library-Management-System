import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Catalog {
    private final List<Book> bookCatalog;
    private int nextBookId;

    // Constructor
    public Catalog() {
        this.bookCatalog = new LinkedList<>();
        this.nextBookId = 1; // Initialize the nextBookId
    }

    // Method to generate the next unique book ID
    int getNextBookId() {
        return nextBookId++;
    }

    // Method to add a new book to the catalog
    public void addBook(Book book) {
        // Set the book ID using the getNextBookId method
        book.setBookId(getNextBookId());
        bookCatalog.add(book);
        System.out.println("Book added to catalog: " + book.getTitle());
    }
    // Method to remove a book from the catalog by its ID
    public void removeBook(int bookId) {
        Iterator<Book> iterator = bookCatalog.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove();
                System.out.println("Book removed from catalog: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found in the catalog.");
    }


    // Method to display the catalog
    public void displayCatalog() {
        if (bookCatalog.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            System.out.println("\nCatalog:");

            for (Book book : bookCatalog) {
                System.out.println(book);
            }
        }
    }

    // Method to get a list of available books
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new LinkedList<>();
        for (Book book : bookCatalog) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    // Method to find a book by its ID
    public Book findBookById(int bookId) {
        for (Book book : bookCatalog) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Method to search for a book by title
    public Book findBookByTitle(String title) {
        for (Book book : bookCatalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }


    // toString method to represent the catalog as a string
    @Override
    public String toString() {
        return "Catalog{" +
                "bookCatalog=" + bookCatalog +
                '}';
    }
}
