package report;

import java.time.LocalDate;

public class borrowingReport extends Report {
    private String bookTitle;
    private String borrowerId;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private int timesRenewed;
    private int totalTimesBorrowed;

    public borrowingReport(String id, String generatedBy, String generatedDate, String description,
                           String bookTitle, String borrowerId, LocalDate dateBorrowed,
                           LocalDate dueDate, int totalTimesBorrowed) {
        super(id, generatedBy, generatedDate, description);
        setBookTitle(bookTitle);
        setBorrowerId(borrowerId);
        setDateBorrowed(dateBorrowed);
        setDueDate(dueDate);
        setTotalTimesBorrowed(totalTimesBorrowed);
        setTimesRenewed(0);
    }

    public String getBookTitle() {
        return bookTitle != null && !bookTitle.isEmpty() ? bookTitle : "Title not available";
    }

    public void setBookTitle(String bookTitle) {
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            this.bookTitle = bookTitle.trim();
        }
    }

    public String getBorrowerId() {
        return borrowerId != null && !borrowerId.trim().isEmpty() ? borrowerId : "No borrower assigned";
    }

    public void setBorrowerId(String borrowerId) {
        if (borrowerId != null && !borrowerId.trim().isEmpty()) {
            this.borrowerId = borrowerId.trim();
        }
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        if (dateBorrowed != null && !dateBorrowed.isAfter(LocalDate.now())) {
            this.dateBorrowed = dateBorrowed;
        }
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate != null && (dateBorrowed == null || !dueDate.isBefore(dateBorrowed))) {
            this.dueDate = dueDate;
        }
    }

    public int getTimesRenewed() {
        return timesRenewed;
    }

    public void setTimesRenewed(int timesRenewed) {
        if (timesRenewed >= 0) {
            this.timesRenewed = timesRenewed;
        }
    }

    public int getTotalTimesBorrowed() {
        return totalTimesBorrowed;
    }

    public void setTotalTimesBorrowed(int totalTimesBorrowed) {
        if (totalTimesBorrowed >= 0) {
            this.totalTimesBorrowed = totalTimesBorrowed;
        }
    }

    public void incrementTimesBorrowed() {
        this.totalTimesBorrowed++;
    }

    public void extendDueDate(LocalDate newDueDate) {
        if (newDueDate != null && (dueDate == null || newDueDate.isAfter(dueDate))) {
            this.dueDate = newDueDate;
            this.timesRenewed++;
        }
    }

    @Override
    public String calculateSummary() {
        long daysUntilDue = (dueDate == null) ? -1 : java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);

        String dueInfo = (dueDate == null) ? "Due date not set"
                : (daysUntilDue < 0) ? "Overdue"
                : (daysUntilDue == 0) ? "Due today"
                : "Due in " + daysUntilDue + " day" + (daysUntilDue > 1 ? "s" : "");

        return "Book '" + getBookTitle() + "' is " + dueInfo + ". Borrowed " + getTotalTimesBorrowed() +
                " time" + (getTotalTimesBorrowed() == 1 ? "" : "s") + ", renewed " + getTimesRenewed() +
                " time" + (getTimesRenewed() == 1 ? "" : "s") + ".";
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof borrowingReport)) return false;
        borrowingReport other = (borrowingReport) obj;
        return getBookTitle().equals(other.getBookTitle())
                && getBorrowerId().equals(other.getBorrowerId())
                && getDateBorrowed().equals(other.getDateBorrowed())
                && ((getDueDate() == null && other.getDueDate() == null) || (getDueDate() != null && getDueDate().equals(other.getDueDate())))
                && getTimesRenewed() == other.getTimesRenewed()
                && getTotalTimesBorrowed() == other.getTotalTimesBorrowed();
    }

    public static String getReportType() {
        return "Borrowing Report";
    }
}
