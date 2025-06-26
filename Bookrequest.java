import java.util.ArrayList;
import java.util.List;

public class Bookrequest {
     String requestId;
     String bookTitle;
     String bookAuthor;
     String bookGenre;
     String status;
    public static int totalRequests = 0;
    public Bookrequest(String requestId, String bookTitle, String bookAuthor, String bookGenre, String status) {
        this.requestId = requestId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.status = status;
        totalRequests++;
    }
    public Bookrequest(String requestId, String bookTitle, String bookAuthor, String bookGenre) {
        this(requestId, bookTitle, bookAuthor, bookGenre, "Pending");
    }

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
    public void setStatus(String status) {
        this.status = status;
    }
    public static int getTotalRequests() {
        return totalRequests;
    }
    public void displayInfo() {
        System.out.println("Request ID: " + requestId);
        System.out.println("Title     : " + bookTitle);
        System.out.println("Author    : " + bookAuthor);
        System.out.println("Genre     : " + bookGenre);
        System.out.println("Status    : " + status);
    }

    public static void main(String[] args) {
        List<Bookrequest> requests = new ArrayList<>();

        Bookrequest r1 = new Bookrequest("R001", "Harry Potter", "J.K. Rowling", "Fantasy");
        Bookrequest r2 = new Bookrequest("R002", "The Hobbit", "J.R.R. Tolkien", "Adventure", "Approved");
        Bookrequest r3 = new Bookrequest("R003", "1984", "George Orwell", "Dystopian");

        requests.add(r1);
        requests.add(r2);
        requests.add(r3);

        for (Bookrequest request : requests) {
            request.displayInfo();
        }

        System.out.println("Total Book Requests: " + Bookrequest.getTotalRequests());
    }
}

