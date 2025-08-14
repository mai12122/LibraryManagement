package user;

import java.time.LocalDate;
import minterface.Authentication;

public class TestApp {
    public static void main(String[] args) {
         new Student("0001", "Alice", "alice@example.com", "pass1234", "CS101", LocalDate.of(2022, 9, 1));
        new Student("0002", "Charlie", "charlie@example.com", "mypass12", "CS102", LocalDate.of(2023, 1, 15));
        new Student("0003", "Hai", "hai@example.com", "pass1234", "CS103", LocalDate.of(2022, 9, 1));
        new Student("0004", "Justin", "justin@example.com", "pass4567", "CS104", LocalDate.of(2023, 2, 10));
        new Student("0005", "Maria", "maria@example.com", "mypass55", "CS105", LocalDate.of(2023, 3, 5));
        new Student("0006", "Nina", "nina@example.com", "pass6789", "CS106", LocalDate.of(2022, 10, 20));
        new Student("0007", "Oscar", "oscar@example.com", "pass7890", "CS107", LocalDate.of(2023, 1, 12));
        new Student("0008", "Paul", "paul@example.com", "mypass88", "CS108", LocalDate.of(2022, 9, 15));
        new Student("0009", "Quinn", "quinn@example.com", "pass9012", "CS109", LocalDate.of(2023, 2, 28));
        new Student("0010", "Rachel", "rachel@example.com", "mypass10", "CS110", LocalDate.of(2023, 3, 18));

        Lecturer bob = new Lecturer("3", "Bob", "bob@example.com", "passabcd", "Computer Science");
        bob.setRecommendation("Excellent teacher and researcher.");
        Lecturer diana = new Lecturer("4", "Diana", "diana@example.com", "pass1234", "Mathematics");
        diana.setRecommendation(null);
        Lecturer emily = new Lecturer("5", "Emily", "emily@example.com", "pass5678", "Physics");
        emily.setRecommendation("Very approachable and supportive.");
        Lecturer frank = new Lecturer("6", "Frank", "frank@example.com", "pass8765", "Chemistry");
        frank.setRecommendation("Known for innovative teaching methods.");
        Lecturer grace = new Lecturer("7", "Grace", "grace@example.com", "pass4321", "Biology");
        grace.setRecommendation(null);
        Lecturer henry = new Lecturer("8", "Henry", "henry@example.com", "pass6780", "Computer Science");
        henry.setRecommendation("Expert in AI and Machine Learning.");

        System.out.println("All registered users:");
        for (users u : users.accountList) {
            System.out.println(u.toString());
        }

        Authentication.loginGUI();
    }
}
