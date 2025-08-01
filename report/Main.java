package report;

import java.time.LocalDate;
import java.util.TreeSet;

public class Main {

    static class ConcreteReport extends Report {
        public ConcreteReport(String id, String generatedBy, String dateCreated) {
            super(id, generatedBy, dateCreated);
        }

        @Override
        public String toString() {
            return "ConcreteReport{" +
                    "id='" + getId() + '\'' +
                    ", generatedBy='" + getGeneratedBy() + '\'' +
                    ", generatedDate='" + getGeneratedDate() + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Report r1 = new ConcreteReport("R001", 
                                        "Admin", 
                                        "2025-07-25");

        borrowingReport b1 = new borrowingReport(
                "B001", "Librarian", "2025-07-20",
                "Harry Potter", "STU123",
                LocalDate.parse("2025-07-20"),
                LocalDate.parse("2025-08-03"),
                5
        );

        bookReport eb1 = new bookReport(
                "EB001", "Admin", "2025-07-15",
                "LOTR", "STU456",
                LocalDate.parse("2025-07-10"),
                LocalDate.parse("2025-08-01"),
                "High-Level Approval"
        );

        TreeSet<Report> reports = new TreeSet<>((rpt1, rpt2) -> rpt1.getId().compareTo(rpt2.getId()));

        reports.add(r1);
        reports.add(b1);
        reports.add(eb1);

        System.out.println("--- Reports in TreeSet (Sorted by ID) ---");
        for (Report report : reports) {
            System.out.println(report); 
        }
        b1.setDueDate(LocalDate.parse("2025-08-10"));
        System.out.println("\n--- After updating B001 due date ---");
        System.out.println(b1);

        eb1.renew();
        System.out.println("\n--- After renewing EB001 ---");
        System.out.println(eb1);
    }
}
