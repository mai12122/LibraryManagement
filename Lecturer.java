import java.util.HashSet;
import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String password;
    private String email;
    private Set<String> recommendedBooks;

    public static int totalLecturers = 0;

    public Lecturer(String id, String name, String password, String email, Set<String> recommendedBooks) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.recommendedBooks = new HashSet<>(recommendedBooks);
        totalLecturers++;
    }

    public Lecturer(String id, String name) {
        this(id, name, "123456", "unknown@example.com", new HashSet<>());
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

    public Set<String> getRecommendedBooks() {
        return new HashSet<>(recommendedBooks);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lecturer other = (Lecturer) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static void main(String[] args) {
        Set<Lecturer> lecturers = new HashSet<Lecturer>();
        Set<String> books1 = new HashSet<String>();
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
