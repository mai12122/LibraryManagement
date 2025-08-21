package book;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import user.MySQLConnection;

public class BookRequest extends Book {
    private String requestId;
    private String status;
    private static int totalRequests = 0;

    public BookRequest(String requestId, String title, String author, String genre, String status) {
        super(title + "-" + requestId, title, author, genre, status, "N/A", "Not Assigned", "Unknown", "Unknown", "Uncategorized");
        setRequestId(requestId);
        setStatus(status);
        synchronized (BookRequest.class) {
            totalRequests++;
        }
    }

    protected void setRequestId(String requestId) {
        if (requestId != null && !requestId.trim().isEmpty()) {
            this.requestId = requestId.trim();
        }
    }

    protected void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status.trim();
        } else {
            this.status = "Pending";
        }
    }

    public String getRequestId() { return requestId; }
    public String getStatus() { return status; }
    public static int getTotalRequests() { return totalRequests; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookRequest)) return false;
        BookRequest other = (BookRequest) obj;
        return requestId != null && requestId.equals(other.requestId);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "requestId='" + requestId + '\'' +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean saveToDB() {
        String query = "INSERT INTO BookRequests (book_id, status) VALUES (?, ?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, getId());
            pstmt.setString(2, status);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void fetchRequestsFromDB_GUI() {
        String query = "SELECT r.id, b.title, b.author, b.genre, r.status " +
                       "FROM BookRequests r JOIN Books b ON r.book_id = b.id";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] { "Request ID", "Title", "Author", "Genre", "Status" });

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("📚 Book Requests");
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void showAddRequestDialog(String bookId, String bookTitle) {
        JTextField statusField = new JTextField("Pending", 20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Book:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(bookTitle), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(statusField, gbc);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book Request",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            BookRequest req = new BookRequest(
                    bookId + System.currentTimeMillis(),
                    bookTitle, "", "", statusField.getText()
            );
            boolean saved = req.saveToDB();
            JOptionPane.showMessageDialog(null, saved ? "Request saved!" : "Failed to save request.");
        }
    }
}
