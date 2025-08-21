package book;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;
import user.MySQLConnection;

public abstract class Book implements Comparable<Book> {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;
    private String isbn;
    private String publishedYear;
    private String publishedCountry;
    private String language;
    private String category;

    protected Book(String id, String title, String author, String genre, String status,
                   String isbn, String publishedYear, String publishedCountry,
                   String language, String category) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setAvailabilityStatus(status);
        setIsbn(isbn);
        setPublishedYear(publishedYear);
        setPublishedCountry(publishedCountry);
        setLanguage(language);
        setCategory(category);
    }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublishedYear(String publishedYear) { this.publishedYear = publishedYear; }
    public void setPublishedCountry(String publishedCountry) { this.publishedCountry = publishedCountry; }
    public void setLanguage(String language) { this.language = language; }
    public void setCategory(String category) { this.category = category; }
    protected void setId(String id) { this.id = id; }
    protected void setAvailabilityStatus(String status) { this.availabilityStatus = status; }

    protected String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public String getIsbn() { return isbn; }
    public String getPublishedYear() { return publishedYear; }
    public String getPublishedCountry() { return publishedCountry; }
    public String getLanguage() { return language; }
    public String getCategory() { return category; }

    public static void fetchBooksFromDB_GUI() {
        String query = "SELECT id, title, author, genre, isbn, published_year, " +
                       "published_country, lang_name, b_categories, is_available FROM Books";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
            "ID", "Title", "Author", "Genre", "ISBN",
            "Year", "Country", "Language", "Category", "Status"
        });

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"),
                    rs.getString("isbn"),
                    rs.getInt("published_year"),
                    rs.getString("published_country"),
                    rs.getString("lang_name"),
                    rs.getString("b_categories"),
                    rs.getBoolean("is_available") ? "Available" : "Borrowed"
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                }
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

        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton borrowBtn = new JButton("Borrow");
        JButton requestBtn = new JButton("Request");
        JButton recommendBtn = new JButton("Recommend");

        buttonsPanel.add(borrowBtn);
        buttonsPanel.add(requestBtn);
        buttonsPanel.add(recommendBtn);

        // Add action listeners
        borrowBtn.addActionListener(e -> handleBorrow(table));
        requestBtn.addActionListener(e -> handleRequest(table));
        recommendBtn.addActionListener(e -> handleRecommend(table));

        JFrame frame = new JFrame("📚 Library Books");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Borrowing handler
    private static void handleBorrow(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Select a book first!");
            return;
        }
        String bookId = table.getValueAt(row, 0).toString();
        String title = table.getValueAt(row, 1).toString();
        String author = table.getValueAt(row, 2).toString();
        String genre = table.getValueAt(row, 3).toString();
        LocalDate today = LocalDate.now();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO BookBorrowing (book_id, borrower_name, borrow_date) VALUES (?, ?, ?)")) {
            ps.setString(1, bookId);
            ps.setString(2, "Default User"); // Can be replaced with login
            ps.setDate(3, Date.valueOf(today));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book borrowed successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Request handler
    private static void handleRequest(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Select a book first!");
            return;
        }
        String bookId = table.getValueAt(row, 0).toString();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO BookRequest (book_id, requester_name, request_date) VALUES (?, ?, ?)")) {
            ps.setString(1, bookId);
            ps.setString(2, "Default User");
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book request submitted!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Recommendation handler
    private static void handleRecommend(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Select a book first!");
            return;
        }
        String bookId = table.getValueAt(row, 0).toString();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO BookRecommendation (book_id, recommended_by, details) VALUES (?, ?, ?)")) {
            ps.setString(1, bookId);
            ps.setString(2, "Default User");
            ps.setString(3, "Great Book!"); // Can prompt user for input
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book recommended!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int compareTo(Book other) {
        if (other == null) return 1;
        if (this.id == null && other.id == null) return 0;
        if (this.id == null) return -1;
        if (other.id == null) return 1;
        return this.id.compareTo(other.id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Book::fetchBooksFromDB_GUI);
    }
}
