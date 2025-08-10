package report;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Report {
    private String id;
    private String generated_by;
    private String generated_date;

    protected Report(String id, String generated_by, String generated_date) {
        setId(id);
        setGeneratedBy(generated_by);
        setGeneratedDate(generated_date);
    }

    public String getId() { 
        System.out.println("getId() called, returning: " + id);
        if (id == null) {
            System.out.println("id is null, returning 'Null'");
            return "Null";
        }
        return id;
    }

    public String getGeneratedBy() {
        System.out.println("getGeneratedBy() called, returning: " + generated_by);
        if (generated_by == null || generated_by.isEmpty()) {
            System.out.println("generated_by is null or empty, returning 'UNKNOWN'");
            return "UNKNOWN";
        }
        return generated_by;
    }

    public String getGeneratedDate() {
        System.out.println("getGeneratedDate() called, returning: " + generated_date);
        if (generated_date == null || generated_date.isEmpty()) {
            System.out.println("generated_date is null or empty, returning 'NOT SET'");
            return "NOT SET";
        }
        return generated_date;
    }

    protected void setId(String id) {
        System.out.println("setId() called, setting to: " + id);
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        } else {
            System.out.println("Invalid ID. It was not set.");
        }
    }

    protected void setGeneratedBy(String generated_by) {
        System.out.println("setGeneratedBy() called, setting to: " + generated_by);
        if (generated_by != null && !generated_by.trim().isEmpty()) {
            this.generated_by = generated_by;
        } else {
            System.out.println("Invalid generated_by. It was not set.");
        }
    }

    protected void setGeneratedDate(String generated_date) {
        System.out.println("setGeneratedDate() called, setting to: " + generated_date);
        if (generated_date != null && !generated_date.trim().isEmpty()) {
            this.generated_date = generated_date;
        } else {
            System.out.println("Invalid generated_date. It was not set.");
        }
    }

    @Override
    public String toString() {
        return "Report{" +
               "id='" + id + '\'' +
               ", generated_by='" + generated_by + '\'' +
               ", generated_date='" + generated_date + '\'' +
               '}';
    }

    public String calculateSummary() {
        LocalDate reportDate = LocalDate.parse(generated_date);
        long daysOld = ChronoUnit.DAYS.between(reportDate, LocalDate.now());

        String urgency = (daysOld <= 3) ? "High" :
                         (daysOld <= 7) ? "Medium" : "Low";
        return "Report is " + daysOld + " days old (" + urgency + " urgency).";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;

        Report other = (Report) obj;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;
        if (generated_by != null ? !generated_by.equals(other.generated_by) : other.generated_by != null) return false;
        if (generated_date != null ? !generated_date.equals(other.generated_date) : other.generated_date != null)
            return false;

        return true;
    }

    public static void printClassInfo() {
        System.out.println("This is the Report superclass.");
    }
}
