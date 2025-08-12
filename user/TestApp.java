package user;

import java.time.LocalDate;
import minterface.Authentication;

public class TestApp {
    public static void main(String[] args) {
        new Student("1", "Alice", "alice@example.com", "password1", "CS101", LocalDate.of(2022, 9, 1));
        new Student("2", "Charlie", "charlie@example.com", "mypassword", "CS102", LocalDate.of(2023, 1, 15));

        Lecturer bob = new Lecturer("3", "Bob", "bob@example.com", "password2", "Computer Science");
        bob.setRecommendation("Excellent teacher and researcher.");

        Lecturer diana = new Lecturer("4", "Diana", "diana@example.com", "pass1234", "Mathematics");
        diana.setRecommendation(null);

        System.out.println("All registered users:");
        for (users u : users.accountList) {
            System.out.println(u.toString());
        }

        Authentication.loginGUI();
    }
}
