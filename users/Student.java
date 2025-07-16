package users;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Student implements Comparable<Student> {

    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate enrollmentDate;
    private String status;

    private static int totalStudents = 0;


    public Student(String id, String name, String email, String password, LocalDate enrollmentDate, String status) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        totalStudents++;
    }

    public Student(String id, String name) {
        this(id, name, "noemail@example.com", "defaultpass", LocalDate.now(), "active");
    }
    @Override
    public int compareTo(Student other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void displayInfo() {
        System.out.println("Student ID     : " + id);
        System.out.println("Name           : " + name);
        System.out.println("Email          : " + email);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Status         : " + status);
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    public static void main(String[] args) {
        Set<Student> students = new TreeSet<>();  

        Student s1 = new Student("ST003", "Sokha", "sokha@example.com", "pass123", LocalDate.of(2024, 9, 1), "active");
        Student s2 = new Student("ST001", "Rithy");
        Student s3 = new Student("ST002", "Sophea");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        for (Student s : students) {
            s.displayInfo();
            System.out.println("--------------------");
        }

        System.out.println("Total Students: " + Student.getTotalStudents());
    }
}
