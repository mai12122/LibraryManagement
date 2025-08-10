package report;

import java.time.LocalDate;
import java.util.TreeSet;

public class Main {

    static class ConcreteReport extends Report {
        public ConcreteReport(String id, String generatedBy, String generatedDate) {
            super(id, generatedBy, generatedDate);
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
        Report r1 = new ConcreteReport("R001", "Admin", "2025-07-25");

        borrowingReport b1 = new borrowingReport(
                "B001", "Librarian", "2025-07-20",
                "Harry Potter", "STU123",
                LocalDate.parse("2025-07-20"),
                LocalDate.parse("2025-08-03"),
                5
        );

        TreeSet<Report> reports = new TreeSet<>((rpt1, rpt2) -> {
            if (rpt1.getId() == null) return -1;
            if (rpt2.getId() == null) return 1;
            return rpt1.getId().compareTo(rpt2.getId());
        });

        reports.add(r1);
        reports.add(b1);

        System.out.println("--- Reports in TreeSet (Sorted by ID) ---");
        for (Report report : reports) {
            System.out.println(report);
        }

        b1.setDueDate(LocalDate.parse("2025-08-10"));
        System.out.println("\n--- After updating B001 due date ---");
        System.out.println(b1);
    }
}
