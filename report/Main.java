package report;

import java.util.TreeSet;

public class Main{
    static class ConcreteReport extends Report {
        public ConcreteReport(String id, String generatedBy, String dateCreated) {
            super(id, generatedBy, dateCreated);
        }

        @Override
        public void displayReport() {
            System.out.println("Generic Report ID: " + getId());
            System.out.println("  Created By: " + getGeneratedBy());
            System.out.println("  Date Created: " + getGeneratedDate());
        }
    }

    public static void main(String[] args) {
        Report r1 = new ConcreteReport("R001", "Admin", "2025-07-25");

        Borrowing b1 = new Borrowing("B001", "Librarian", "2025-07-20",
                "Harry Potter", "STU123", "2025-07-20", "2025-08-03");

        ExtendedBorrowing eb1 = new ExtendedBorrowing("EB001", "Admin", "2025-07-15",
                "LOTR", "STU456", "2025-07-10", "2025-08-01", "High-Level Approval");

        TreeSet<Report> reports = new TreeSet<>((rpt1, rpt2) -> rpt1.getId().compareTo(rpt2.getId()));

        reports.add(r1);
        reports.add(b1);
        reports.add(eb1);

        System.out.println("--- Reports in TreeSet (Sorted by ID) ---");
        for (Report report : reports) {
            System.out.println("--------");
            report.displayReport();
        }

        b1.setDueDate("2025-08-10");
        System.out.println("\n--- After updating B001 due date ---");
        b1.displayReport();

        eb1.renew();
        System.out.println("\n--- After renewing EB001 ---");
        eb1.displayReport();
    }
}
