import java.util.ArrayList;
import java.util.List;

public class Lecturer {

    private String id;
    private String name;
    private String password;
    private String email;
    private List<String> recommendedBooks;


    public static int totalLecturers = 0;

    public Lecturer(String id, String name, String password, String email, List<String> recommendedBooks) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.recommendedBooks = new ArrayList<>(recommendedBooks);
        totalLecturers++;
    }
    public Lecturer(String id, String name) {
        this(id, name, "123456", "unknown@example.com", new ArrayList<>());
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public List<String> getRecommendedBooks() {
        return new ArrayList<>(recommendedBooks);
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addRecommendedBook(String bookTitle) {
        recommendedBooks.add(bookTitle);
    }
    public static int getTotalLecturers() {
        return totalLecturers;
    }
    public void displayInfo() {
        System.out.println("Lecturer ID     : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Email           : " + email);
        System.out.println("Recommended Books:");
        if (recommendedBooks.isEmpty()) {
            System.out.println("  None");
        } else {
            for (String book : recommendedBooks) {
                System.out.println("  - " + book);
            }
        }
    }

    public static void main(String[] args) {
        List<Lecturer> lecturers = new ArrayList<Lecturer>();
        List<String> books1 = new ArrayList<String>();
        books1.add("Clean Code");
        books1.add("Refactoring");

        Lecturer lec1 = new Lecturer("L001", "Mr. Dara", "dara123", "dara@example.com", books1);
        Lecturer lec2 = new Lecturer("L002", "Ms. Srey");
        lec2.addRecommendedBook("Introduction to Algorithms");

        lecturers.add(lec1);
        lecturers.add(lec2);

        for (Lecturer lec : lecturers) {
            lec.displayInfo();
            System.out.println("------------------------");
        }

        System.out.println("Total Lecturers: " + Lecturer.getTotalLecturers());
    }
}
