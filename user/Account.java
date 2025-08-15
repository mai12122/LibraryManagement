package user;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
    public static void main(String[] args) {
        ArrayList<Student> accountList = new ArrayList<>();

        ResultSet rs = MySQLConnection.executeQuery("SELECT * FROM students");

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate enrollmentDate = rs.getDate("enrollment_date").toLocalDate();
                int accountId = rs.getInt("account_id");

                Student s1 = new Student(id, enrollmentDate, accountId);
                accountList.add(s1); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Student student : accountList) {
            System.out.println(student); 
    }
}
}