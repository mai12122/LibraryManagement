package users;

import java.util.Set;
import java.util.TreeSet;
import minterface.*;

public class Lecturer implements Authentication {

    private String id;
    private String name;
    private String password;
    private String email;
    private String photo;
    private String position;
    private String department;
    private String phoneNumber;
    private int totalBooksRecommended;
    private Set<String> recommendedBooks;

    private static int totalLecturers = 0;

    public Lecturer(String id, String name, String password, String email, String photo, 
                    String position, String department, String phoneNumber, 
                    int totalBooksRecommended, Set<String> recommendedBooks) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.position = position;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.totalBooksRecommended = totalBooksRecommended;
        this.recommendedBooks = new TreeSet<>(recommendedBooks);
        totalLecturers++; 
    }

    public Lecturer(String id, String name) {
        this(id, name, "123456", "unknown@example.com", "default.jpg", 
             "Lecturer", "General Studies", "000-000-0000", 0, new TreeSet<>());
    }

    public void addRecommendedBook(String title) {
        if (recommendedBooks.add(title)) {
            totalBooksRecommended++; 
        }
    }

    public void displayInfo() {
        System.out.println("Lecturer ID     : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Email           : " + email);
        System.out.println("Phone           : " + phoneNumber);
        System.out.println("Photo           : " + photo);
        System.out.println("Position        : " + position);
        System.out.println("Department      : " + department);
        System.out.println("Recommended Books (" + totalBooksRecommended + "):");
        if (recommendedBooks.isEmpty()) {
            System.out.println("  None");
        } else {
            for (String book : recommendedBooks) {
                System.out.println("  - " + book);
            }
        }
    }

    public static int getTotalLecturers() {
        return totalLecturers;
    }

    @Override
    public void login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println(name + " has logged in successfully.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    @Override
    public void register() {
        System.out.println(name + " has been registered.");
    }

    public static void main(String[] args) {
        Set<String> books = new TreeSet<>();
        books.add("Data Structures");
        books.add("Algorithms");

        Lecturer lec1 = new Lecturer("L001", "Dr. Rith", "pass123", "rith@example.com", 
            "rith.jpg", "Senior Lecturer", "Computer Science", 
            "012345678", 0, books);

        Lecturer lec2 = new Lecturer("L002", "Ms. Sreymom");
        lec2.addRecommendedBook("Clean Code");

        lec1.displayInfo();
        System.out.println("--------------------");
        lec2.displayInfo();
        System.out.println("--------------------");

        System.out.println("Total Lecturers: " + Lecturer.getTotalLecturers());

        lec1.login("rith@example.com", "pass123");
        lec2.register();
    }
}
