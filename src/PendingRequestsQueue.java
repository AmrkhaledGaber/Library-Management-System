import java.util.LinkedList;
import java.util.Queue;

public class PendingRequestsQueue {
    private Queue<Book> requestsQueue;

    // Constructor
    public PendingRequestsQueue() {
        this.requestsQueue = new LinkedList<>();
    }

    // Method to enqueue a new request when a book is not available
    public void enqueueRequest(Book book) {
        requestsQueue.add(book);
        System.out.println("Book request added to the queue for Book ID " + book.getBookId());
    }

    // Method to dequeue a request when the book becomes available
    public Book dequeueRequest() {
        if (!requestsQueue.isEmpty()) {
            Book requestedBook = requestsQueue.poll();
            System.out.println("Book request dequeued for Book ID " + requestedBook.getBookId());
            return requestedBook;
        } else {
            System.out.println("No pending book requests in the queue.");
            return null;
        }
    }

    // Method to check if the queue is empty
    public boolean isQueueEmpty() {
        return requestsQueue.isEmpty();
    }


}
