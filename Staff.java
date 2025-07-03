import java.util.HashSet;
import java.util.Set;

public class Staff {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String shift;

    public static int totalStaff = 0;
    public Set<String> acceptedRequests;

    public Staff(String id, String name, String phone, String email, String shift) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.shift = shift;
        this.acceptedRequests = new HashSet<>();
        totalStaff++;
    }

    public Staff(String id, String name) {
        this(id, name, "No Phone", "noemail@example.com", "Unknown");
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getShift() { return shift; }

    public void setShift(String shift) { this.shift = shift; }

    public void acceptBookRequest(String requestTitle) {
        acceptedRequests.add(requestTitle);
        System.out.println(name + " accepted book request: " + requestTitle);
    }

    public void showAcceptedRequests() {
        System.out.println("Accepted book requests for " + name + ":");
        if (acceptedRequests.isEmpty()) {
            System.out.println("  No accepted requests.");
        } else {
            for (String request : acceptedRequests) {
                System.out.println("  - " + request);
            }
        }
    }

    public void updateBookStatus(String bookTitle, String newStatus) {
        System.out.println(name + " updated status of '" + bookTitle + "' to: " + newStatus);
    }

    public void addNewBook(Book book) {
        System.out.println(name + " added new book: " + book.getTitle() + " by " + book.getAuthor());
    }

    public static int getTotalStaff() {
        return totalStaff;
    }

    public void displayInfo() {
        System.out.println("Staff ID : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Phone    : " + phone);
        System.out.println("Email    : " + email);
        System.out.println("Shift    : " + shift);
    }

    public static void main(String[] args) {
        Staff s1 = new Staff("S001", "Mr. Vannak", "012345678", "vannak@library.com", "Morning");
        Staff s2 = new Staff("S002", "Ms. Dany");

        s1.acceptBookRequest("Request for 'Atomic Habits'");
        s1.updateBookStatus("Atomic Habits", "Approved");
        s1.showAcceptedRequests();

        Book newBook = new Book("B005", "Design Patterns", "Erich Gamma", "Tech", "Available", "1122334455", "Shelf C1");
        s1.addNewBook(newBook);

        System.out.println("------------------------");

        s1.displayInfo();
        s2.displayInfo();

        System.out.println("Total Staff Members: " + Staff.getTotalStaff());
    }
}
