package report;

import java.time.LocalDate;

public class borrowingReport extends Report {
    private String bookTitle;
    private String borrowerId;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private int timesRenewed;
    private int totalTimesBorrowed;

    public borrowingReport(String id, String generatedBy, String generatedDate,
                           String bookTitle, String borrowerId, LocalDate dateBorrowed,
                           LocalDate dueDate, int totalTimesBorrowed) {
        super(id, generatedBy, generatedDate);
        this.bookTitle = bookTitle;
        this.borrowerId = borrowerId;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.totalTimesBorrowed = totalTimesBorrowed;
        this.timesRenewed = 0;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getTimesRenewed() {
        return timesRenewed;
    }

    public void setTimesRenewed(int timesRenewed) {
        this.timesRenewed = timesRenewed;
    }

    public int getTotalTimesBorrowed() {
        return totalTimesBorrowed;
    }

    public void setTotalTimesBorrowed(int totalTimesBorrowed) {
        this.totalTimesBorrowed = totalTimesBorrowed;
    }

    public void incrementTimesBorrowed() {
        this.totalTimesBorrowed++;
    }

    public void extendDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
        this.timesRenewed++;
    }
    
    public String calculateSummary() {
        return String.format(
            "Book: '%s' has been borrowed %d times. Last borrowed by ID '%s' on %s (due on %s). Renewed %d times.",
            bookTitle,
            totalTimesBorrowed,
            borrowerId,
            dateBorrowed,
            dueDate,
            timesRenewed
        );
    }

    @Override
    public String toString() {
        return "borrowingReport{" +
                "id='" + getId() + '\'' +
                ", generatedBy='" + getGeneratedBy() + '\'' +
                ", generatedDate='" + getGeneratedDate() + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", borrowerId='" + borrowerId + '\'' +
                ", dateBorrowed=" + dateBorrowed +
                ", dueDate=" + dueDate +
                ", timesRenewed=" + timesRenewed +
                ", totalTimesBorrowed=" + totalTimesBorrowed +
                '}';
    }
}
