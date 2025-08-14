package book;

import java.time.LocalDate;

public class BookBorrwoing extends Book {
    private String borrowingId;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private static int totalBorrowings = 0;

    public BookBorrwoing(String borrowingId, String title, String author, String genre,
                         String borrowerName, LocalDate borrowDate, LocalDate returnDate) {
        super(title + "-" + borrowingId, title, author, genre, "Borrowed", "N/A", "Not Assigned");
        setBorrowingId(borrowingId);
        setBorrowerName(borrowerName);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
        synchronized (BookBorrwoing.class) {
            totalBorrowings++;
        }
    }

    public BookBorrwoing(String borrowingId, String title, String author, String genre, String borrowerName) {
        this(borrowingId, title, author, genre, borrowerName, LocalDate.now(), null);
    }

    protected void setBorrowingId(String borrowingId) {
        if (borrowingId != null && !borrowingId.trim().isEmpty()) {
            this.borrowingId = borrowingId.trim();
        }
    }

    protected void setBorrowerName(String borrowerName) {
        if (borrowerName != null && !borrowerName.trim().isEmpty()) {
            this.borrowerName = borrowerName.trim();
        } else {
            this.borrowerName = "Unknown";
        }
    }

    protected void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate != null ? borrowDate : LocalDate.now();
    }

    protected void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public static int getTotalBorrowings() {
        return totalBorrowings;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookBorrwoing)) return false;
        BookBorrwoing other = (BookBorrwoing) obj;
        if (borrowingId == null) {
            return other.borrowingId == null;
        }
        return borrowingId.equals(other.borrowingId);
    }

    @Override
    public String toString() {
        return "BookBorrwoing{" +
                "borrowingId='" + borrowingId + '\'' +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + (returnDate != null ? returnDate : "Not returned") +
                '}';
    }
}
