package book;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import user.MySQLConnection;

public class BookRecommendation extends Book {
    private String recommendedBy;
    private String recommendationDetails;

    public BookRecommendation(
            String id, String title, String author, String genre,
            String availabilityStatus, String isbn, String publishedYear,
            String publishedCountry, String language, String category,
            String recommendedBy, String recommendationDetails) {

        super(id, title, author, genre, availabilityStatus, isbn, publishedYear,
              publishedCountry, language, category);
        setRecommendedBy(recommendedBy);
        setRecommendationDetails(recommendationDetails);
    }

    public String getRecommendedBy() { return recommendedBy; }
    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = (recommendedBy == null || recommendedBy.trim().isEmpty())
                ? "Unknown" : recommendedBy.trim();
    }

    public String getRecommendationDetails() { return recommendationDetails; }
    public void setRecommendationDetails(String recommendationDetails) {
        this.recommendationDetails = (recommendationDetails == null || recommendationDetails.trim().isEmpty())
                ? "No details provided" : recommendationDetails.trim();
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Recommended By: " + recommendedBy +
                ", Details: " + recommendationDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookRecommendation)) return false;
        BookRecommendation other = (BookRecommendation) obj;

        return (getId() != null && getId().equals(other.getId())) &&
               (recommendedBy != null ? recommendedBy.equals(other.recommendedBy) : other.recommendedBy == null) &&
               (recommendationDetails != null ? recommendationDetails.equals(other.recommendationDetails) : other.recommendationDetails == null);
    }

    /*** DATABASE METHODS ***/
    public boolean saveToDB() {
        String query = "INSERT INTO BookRecommendations (book_id, recommended_by, recommendation_details) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, getId());
            pstmt.setString(2, recommendedBy);
            pstmt.setString(3, recommendationDetails);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void fetchRecommendationsFromDB_GUI() {
        String query = "SELECT r.id, b.title, b.author, r.recommended_by, r.recommendation_details " +
                       "FROM BookRecommendations r JOIN Books b ON r.book_id = b.id";

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"Rec ID", "Book Title", "Author", "Recommended By", "Details"});

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("recommended_by"),
                        rs.getString("recommendation_details")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("📚 Book Recommendations");
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void showAddRecommendationDialog(String bookId, String bookTitle) {
        JTextField recommenderField = new JTextField();
        JTextArea detailsArea = new JTextArea(5, 20);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Book: " + bookTitle));
        panel.add(new JLabel("Recommended By:"));
        panel.add(recommenderField);
        panel.add(new JLabel("Details:"));
        panel.add(scrollPane);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Recommendation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            BookRecommendation rec = new BookRecommendation(
                    bookId, bookTitle, "", "", "Available", "", "", "", "", "",
                    recommenderField.getText(), detailsArea.getText());
            boolean saved = rec.saveToDB();
            JOptionPane.showMessageDialog(null,
                    saved ? "Recommendation saved!" : "Failed to save recommendation.");
        }
    }
}
