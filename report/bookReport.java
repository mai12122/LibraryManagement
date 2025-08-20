package report;

import java.time.LocalDate;

public class bookReport extends borrowingReport {
    private String specialApproval;

    public bookReport(String id, String generatedBy, String generatedDate,String description,
                      String bookTitle, String borrowerId,
                      LocalDate dateBorrowed, LocalDate dueDate,
                      String specialApproval) {
        super(id, generatedBy, generatedDate, description, bookTitle, borrowerId, dateBorrowed, dueDate, 0);
        setSpecialApproval(specialApproval);
    }

    public String getSpecialApproval() {
        if (specialApproval != null && !specialApproval.isEmpty()) {
            return specialApproval;
        } else {
            return "No special approval granted";
        }
    }

    public void setSpecialApproval(String specialApproval) {
        if (specialApproval != null && !specialApproval.trim().isEmpty()) {
            this.specialApproval = specialApproval;
        } else {
            System.out.println("Invalid special approval: cannot be null or empty.");
        }
    }

    public void renew() {
        int maxRenewals = 3;
        if (getTimesRenewed() < maxRenewals) {
            setTimesRenewed(getTimesRenewed() + 1);
        } else {
            System.out.println("Renewal limit reached. Cannot renew anymore.");
        }
    }

    @Override
    public String toString() {
        return "bookReport {" +
               "\n  id='" + getId() + '\'' +
               ",\n  generatedBy='" + getGeneratedBy() + '\'' +
               ",\n  generatedDate='" + getGeneratedDate() + '\'' +
               ",\n  bookTitle='" + getBookTitle() + '\'' +
               ",\n  borrowerId='" + getBorrowerId() + '\'' +
               ",\n  dateBorrowed=" + getDateBorrowed() +
               ",\n  dueDate=" + getDueDate() +
               ",\n  timesRenewed=" + getTimesRenewed() +
               ",\n  totalTimesBorrowed=" + getTotalTimesBorrowed() +
               ",\n  specialApproval='" + specialApproval + '\'' +
               "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        bookReport other = (bookReport) obj;
        return (specialApproval == null ? other.specialApproval == null
                : specialApproval.equals(other.specialApproval));
    }

    public static String getReportType() {
        return "Book Report";
    }
}
