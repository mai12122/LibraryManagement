import java.util.HashSet;
import java.util.Set;

public class Bookrequest {
    // Fields (Attributes)
    private String requestId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private String status;

    // Static field to track total requests
    public static int totalRequests = 0;

    // Constructor with all fields
    public Bookrequest(String requestId, String bookTitle, String bookAuthor, String bookGenre, String status) {
        this.requestId = requestId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.status = status;
        totalRequests++;
    }

    // Overloaded constructor with default status
    public Bookrequest(String requestId, String bookTitle, String bookAuthor, String bookGenre) {
        this(requestId, bookTitle, bookAuthor, bookGenre, "Pending");
    }

    // Getters
    public String getRequestId() {
        return requestId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Static method to get total requests
    public static int getTotalRequests() {
        return totalRequests;
    }

    // Method to display book request information
    public void displayInfo() {
        System.out.println("Request ID: " + requestId);
        System.out.println("Title     : " + bookTitle);
        System.out.println("Author    : " + bookAuthor);
        System.out.println("Genre     : " + bookGenre);
        System.out.println("Status    : " + status);
    }

    // Override equals and hashCode for proper HashSet behavior
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bookrequest that = (Bookrequest) obj;
        return requestId.equals(that.requestId);
    }

    @Override
    public int hashCode() {
        return requestId.hashCode();
    }

    public static void main(String[] args) {
        // Creating a HashSet to store unique book requests
         Set<Bookrequest> requests = new HashSet<Bookrequest>();
        Bookrequest r1 = new Bookrequest("R001", "Harry Potter", "J.K. Rowling", "Fantasy");
        Bookrequest r2 = new Bookrequest("R002", "The Hobbit", "J.R.R. Tolkien", "Adventure", "Approved");
        Bookrequest r3 = new Bookrequest("R003", "1984", "George Orwell", "Dystopian");
        requests.add(r1);
        requests.add(r2);
        requests.add(r3);
        for (Bookrequest request : requests) {
            request.displayInfo();
            System.out.println("------------------------");
        }
        System.out.println("Total Book Requests: " + Bookrequest.getTotalRequests());
    }
}
