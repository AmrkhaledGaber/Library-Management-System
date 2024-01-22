import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianInterfaceLayer extends JFrame {
    private Catalog catalog;
    private PendingRequestsQueue requestsQueue;

    public LibrarianInterfaceLayer(Catalog catalog, PendingRequestsQueue requestsQueue) {
        this.catalog = catalog;
        this.requestsQueue = requestsQueue;

        setTitle("Librarian Interface");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton displayCatalogButton = new JButton("Display Catalog");
        JButton processRequestsButton = new JButton("Process Requests");
        JButton addBookButton = new JButton("Add Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton exitButton = new JButton("Exit");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(displayCatalogButton);
        add(processRequestsButton);
        add(addBookButton);
        add(removeBookButton);
        add(exitButton);

        displayCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalog.displayCatalog();
            }
        });

        processRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processRequests();
            }
        });

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void processRequests() {
        // Implement logic to process pending requests
        // For example, dequeue requests and update book availability
        while (!requestsQueue.isQueueEmpty()) {
            Book requestedBook = requestsQueue.dequeueRequest();
            System.out.println("Processing request for Book ID " + requestedBook.getBookId());
            // Implement further processing logic as needed
        }
    }

    private void addBook() {
        // Implement logic to add a new book to the catalog
        // For example, show a dialog to input book details and add it to the catalog
        // You can use JOptionPane for input dialogs
        String title = JOptionPane.showInputDialog("Enter book title:");
        String author = JOptionPane.showInputDialog("Enter book author:");
        String genre = JOptionPane.showInputDialog("Enter book genre:");

        Book newBook = new Book(catalog.getNextBookId(), title, author, genre);
        catalog.addBook(newBook);
        System.out.println("Book added to catalog: " + newBook);
    }

    private void removeBook() {
        // Implement logic to remove a book from the catalog
        // For example, show a dialog to select a book for removal
        // You can use JOptionPane for selection dialogs
        String bookIdInput = JOptionPane.showInputDialog("Enter Book ID to remove:");
        try {
            int bookId = Integer.parseInt(bookIdInput);
            Book bookToRemove = catalog.findBookById(bookId);

            if (bookToRemove != null) {
                catalog.removeBook(bookToRemove.getBookId());
                System.out.println("Book removed from catalog: " + bookToRemove);
            } else {
                System.out.println("Book not found in catalog.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid Book ID.");
        }
    }

    public void showLibrarianInterface() {
        setVisible(true);
    }
}
