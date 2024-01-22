public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private boolean availabilityStatus;

    // Constructor
    public Book(int bookId, String title, String author, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = true; // Set the initial availability status to true
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // toString method to represent the book as a string
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                '}';
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
