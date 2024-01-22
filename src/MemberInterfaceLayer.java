import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberInterfaceLayer extends JFrame {
    private Catalog catalog;
    private PendingRequestsQueue requestsQueue;
    private LibraryMember currentMember;

    public MemberInterfaceLayer(Catalog catalog, PendingRequestsQueue requestsQueue) {
        this.catalog = catalog;
        this.requestsQueue = requestsQueue;

        setTitle("Member Interface");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton displayCatalogButton = new JButton("Display Catalog");
        JButton searchBookButton = new JButton("Search Book");
        JButton displayBorrowedBooksButton = new JButton("Display Borrowed Books");
        JButton requestBookButton = new JButton("Request Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton exitButton = new JButton("Exit");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(displayCatalogButton);
        add(searchBookButton);
        add(displayBorrowedBooksButton);
        add(requestBookButton);
        add(returnBookButton);
        add(exitButton);

        displayCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the catalog
                catalog.displayCatalog();
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic for searching a book
                // For example, show a dialog for entering search criteria
                // and display the result
                // You can use JOptionPane for input and display
                String searchTerm = JOptionPane.showInputDialog(MemberInterfaceLayer.this, "Enter book title or author:");
                // Implement search logic using searchTerm
                // Display the search result
            }
        });

        displayBorrowedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display borrowed books by the current member
                if (currentMember != null) {
                    currentMember.displayBorrowedBooks();
                } else {
                    JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Member not logged in.");
                }
            }
        });

        requestBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Request a book
                // You might need to show a dialog for selecting a book from the catalog
                // and then add it to the requestsQueue
                if (currentMember != null) {
                    // Show a dialog to select a book (similar to search)
                    String bookTitle = JOptionPane.showInputDialog(MemberInterfaceLayer.this, "Enter book title:");
                    // Find the book in the catalog
                    Book selectedBook = catalog.findBookByTitle(bookTitle);
                    if (selectedBook != null) {
                        // Add the book to the requestsQueue
                        requestsQueue.enqueueRequest(selectedBook);
                        JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Book request placed.");
                    } else {
                        JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Book not found in the catalog.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Member not logged in.");
                }
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return a book
                // You might need to show a dialog for selecting a borrowed book
                // and then return it
                if (currentMember != null) {
                    // Show a dialog to select a borrowed book
                    String bookTitle = JOptionPane.showInputDialog(MemberInterfaceLayer.this, "Enter book title:");
                    // Find the book in the member's borrowed books
                    Book borrowedBook = currentMember.findBorrowedBookByTitle(bookTitle);
                    if (borrowedBook != null) {
                        // Return the book
                        currentMember.returnBook(borrowedBook);
                        JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Book returned.");
                    } else {
                        JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Book not found in your borrowed books.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MemberInterfaceLayer.this, "Member not logged in.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the member interface
                dispose(); // Close the interface
            }
        });
    }

    public void setCurrentMember(LibraryMember member) {
        this.currentMember = member;
    }

    public void showMemberInterface() {
        setVisible(true);
    }
}
