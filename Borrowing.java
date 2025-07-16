import java.util.Set;
import java.util.TreeSet;

public class Borrowing implements Comparable<Borrowing> {
    private String borrowingId;
    private String bookTitle;
    private String borrowerId;
    private String dateBorrowed;
    private String dueDate;
    private String dateReturned;
    private String approvedById;
    private int timesRenewed;

    private static int totalBorrowings = 0;
    public Borrowing(String borrowingId, String bookTitle, String borrowerId, String dateBorrowed,
                     String dueDate, String dateReturned, String approvedById, int timesRenewed) {
        this.borrowingId = borrowingId;
        this.bookTitle = bookTitle;
        this.borrowerId = borrowerId;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
        this.approvedById = approvedById;
        this.timesRenewed = timesRenewed;
        totalBorrowings++;
    }

    public Borrowing(String borrowingId, String bookTitle, String borrowerId, String dateBorrowed, String dueDate) {
        this(borrowingId, bookTitle, borrowerId, dateBorrowed, dueDate, "Not Returned", "Unknown", 0);
    }
    public String getBorrowingId() { return borrowingId; }
    public String getBookTitle() { return bookTitle; }
    public String getBorrowerId() { return borrowerId; }
    public String getDateBorrowed() { return dateBorrowed; }
    public String getDueDate() { return dueDate; }
    public String getDateReturned() { return dateReturned; }
    public String getApprovedById() { return approvedById; }
    public int getTimesRenewed() { return timesRenewed; }
    public void setDateReturned(String dateReturned) { this.dateReturned = dateReturned; }
    public void setApprovedById(String approvedById) { this.approvedById = approvedById; }
    public void setTimesRenewed(int timesRenewed) { this.timesRenewed = timesRenewed; }
    public static int getTotalBorrowings() { return totalBorrowings; }

    public void displayInfo() {
        System.out.println("Borrowing ID : " + borrowingId);
        System.out.println("Book Title   : " + bookTitle);
        System.out.println("Borrower ID  : " + borrowerId);
        System.out.println("Borrowed On  : " + dateBorrowed);
        System.out.println("Due Date     : " + dueDate);
        System.out.println("Returned On  : " + dateReturned);
        System.out.println("Approved By  : " + approvedById);
        System.out.println("Times Renewed: " + timesRenewed);
    }

    @Override
    public int compareTo(Borrowing other) {
        return this.borrowingId.compareTo(other.borrowingId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Borrowing)) return false;
        Borrowing other = (Borrowing) obj;
        return borrowingId.equals(other.borrowingId);
    }

    @Override
    public int hashCode() {
        return borrowingId.hashCode();
    }

    // Main method to test
    public static void main(String[] args) {
        Set<Borrowing> borrowings = new TreeSet<>();

        Borrowing b1 = new Borrowing("BR002", "Java Basics", "STU456", "2025-06-03", "2025-06-17", "2025-06-12", "LEC001", 1);
        Borrowing b2 = new Borrowing("BR001", "Clean Code", "STU123", "2025-06-01", "2025-06-15");

        borrowings.add(b1);
        borrowings.add(b2);

        for (Borrowing b : borrowings) {
            b.displayInfo();
            System.out.println("------------------------");
        }

        System.out.println("Total Borrowings: " + Borrowing.getTotalBorrowings());
    }
}
