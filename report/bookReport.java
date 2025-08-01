package report;

import java.time.LocalDate;

public class bookReport extends borrowingReport {
    private String specialApproval;

    public bookReport(String id, String generatedBy, String generatedDate,
                      String bookTitle, String borrowerId,
                      LocalDate dateBorrowed, LocalDate dueDate,
                      String specialApproval) {
        super(id, generatedBy, generatedDate, bookTitle, borrowerId, dateBorrowed, dueDate, 0);
        this.specialApproval = specialApproval;
    }

    public String getSpecialApproval() {
        return specialApproval;
    }

    public void setSpecialApproval(String specialApproval) {
        this.specialApproval = specialApproval;
    }

    public void renew() {
        setTimesRenewed(getTimesRenewed() + 1);
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
}
