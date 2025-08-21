package book;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;
import user.MySQLConnection;

public class BookBorrowing extends Book {
    private String borrowingId;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private static int totalBorrowings = 0;

    public BookBorrowing(String borrowingId, String title, String author, String genre,
                         String borrowerName, LocalDate borrowDate, LocalDate returnDate) {
        super(borrowingId, title, author, genre, "Borrowed", "N/A", 
              borrowDate != null ? borrowDate.toString() : "", "Unknown", "Unknown", "Uncategorized");
        setBorrowingId(borrowingId);
        setBorrowerName(borrowerName);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
        synchronized (BookBorrowing.class) {
            totalBorrowings++;
        }
    }

    public BookBorrowing(String borrowingId, String title, String author, String genre, String borrowerName) {
        this(borrowingId, title, author, genre, borrowerName, LocalDate.now(), null);
    }

    protected void setBorrowingId(String borrowingId) {
        if (borrowingId != null && !borrowingId.trim().isEmpty()) this.borrowingId = borrowingId.trim();
    }

    protected void setBorrowerName(String borrowerName) {
        this.borrowerName = (borrowerName == null || borrowerName.trim().isEmpty()) ? "Unknown" : borrowerName.trim();
    }

    protected void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate != null ? borrowDate : LocalDate.now(); }
    protected void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getBorrowingId() { return borrowingId; }
    public String getBorrowerName() { return borrowerName; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public static int getTotalBorrowings() { return totalBorrowings; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookBorrowing)) return false;
        BookBorrowing other = (BookBorrowing) obj;
        return borrowingId != null && borrowingId.equals(other.borrowingId);
    }

    @Override
    public String toString() {
        return "BookBorrowing{" +
                "borrowingId='" + borrowingId + '\'' +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + (returnDate != null ? returnDate : "Not returned") +
                '}';
    }

    // ---------------- GUI ----------------
    public static void fetchBorrowingsFromDB_GUI() {
        String query = "SELECT b.borrowing_id, bk.title, bk.author, bk.genre, " +
                       "b.borrower_name, b.borrow_date, b.return_date " +
                       "FROM Borrowings b JOIN Books bk ON b.book_id = bk.id";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Borrowing ID", "Title", "Author", "Genre",
            "Borrower Name", "Borrow Date", "Return Date"
        });

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("borrowing_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getString("borrower_name"),
                        rs.getDate("borrow_date") != null ? rs.getDate("borrow_date").toLocalDate() : "N/A",
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : "Not returned"
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                return c;
            }
        };

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(new Color(220, 220, 220));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JFrame frame = new JFrame("📚 Book Borrowings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
