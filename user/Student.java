package user;

import java.time.LocalDate;

public class Student extends users {
    private String studentClass;
    private LocalDate enrollmentDate; 

    public Student(String id, String name, String email, String password, String studentClass, LocalDate enrollmentDate) {
        super();
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
        setStudentClass(studentClass);
        setEnrollmentDate(enrollmentDate);
        accountList.add(this);
    }

    public void displayStudent() {
        System.out.println("Student Information:");
        displayInfo();
        System.out.println("Student Class: " + studentClass);
        System.out.println("Enrollment Date: " + enrollmentDate);
    }

    public String getStudentClass() {
        if (studentClass == null || studentClass.equals("Unknown")) {
            System.out.println("Warning: Student class is not set.");
        }
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        if (studentClass == null || studentClass.trim().isEmpty()) {
            System.out.println("Invalid student class. Setting to 'Unknown'.");
            this.studentClass = "Unknown";
        } else {
            this.studentClass = studentClass;
        }
    }

    public LocalDate getEnrollmentDate() {
        if (enrollmentDate == null) {
            System.out.println("Warning: Enrollment date is not set.");
        }
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        if (enrollmentDate == null) {
            System.out.println("Invalid enrollment date. Setting to current date.");
            this.enrollmentDate = LocalDate.now();
        } else {
            this.enrollmentDate = enrollmentDate;
        }
    }
}
