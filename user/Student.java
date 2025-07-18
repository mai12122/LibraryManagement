package user;

import java.time.LocalDate;

public class Student extends users {
    private String studentClass;
    private LocalDate enrollmentDate; 

    public Student(String id, String name, String email, String password, String studentClass, LocalDate enrollmentDate) {
        super(id, name, email, password);
        System.out.println("Student constructor called with studentClass: " + studentClass + ", enrollmentDate: " + enrollmentDate);
        this.studentClass = studentClass;
        this.enrollmentDate = enrollmentDate;
    }

    public void displayStudent() {
        System.out.println("Displaying Student info:");
        displayInfo();
        System.out.println("Student Class: " + studentClass);
        System.out.println("Enrollment Date: " + enrollmentDate);
    }

    public String getStudentClass() {
        System.out.println("getStudentClass() called, returning: " + studentClass);
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        System.out.println("setStudentClass() called, setting to: " + studentClass);
        this.studentClass = studentClass;
    }

    public LocalDate getEnrollmentDate() {
        System.out.println("getEnrollmentDate() called, returning: " + enrollmentDate);
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        System.out.println("setEnrollmentDate() called, setting to: " + enrollmentDate);
        this.enrollmentDate = enrollmentDate;
    }
}
