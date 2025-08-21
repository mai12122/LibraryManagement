package book;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;
import user.MySQLConnection;

public abstract class Book implements Comparable<Book> {
    private String id, title, author, genre, availabilityStatus, isbn, publishedYear, publishedCountry, language, category;

    protected Book(String id, String title, String author, String genre, String status,
                   String isbn, String publishedYear, String publishedCountry,
                   String language, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = status;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.publishedCountry = publishedCountry;
        this.language = language;
        this.category = category;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public static void fetchBooksFromDB_GUI() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID", "Title", "Author", "Genre", "ISBN",
            "Year", "Country", "Language", "Category", "Status"
        });

        String query = "SELECT id, title, author, genre, isbn, published_year, published_country, lang_name, b_categories, is_available FROM Books";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
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
                    c.setBackground(row % 2 == 0 ? new Color(245, 248, 250, 200) : new Color(255, 255, 255, 200));
                } else {
                    c.setBackground(new Color(184, 207, 229, 230));
                }
                return c;
            }
        };

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JLabel title = new JLabel("📚 Library Books", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonsPanel.setOpaque(false);

        JButton borrowBtn = createStyledButton("📖 Borrow", new Color(46, 204, 113));
        JButton requestBtn = createStyledButton("Request", new Color(52, 152, 219));
        JButton recommendBtn = createStyledButton("Recommend", new Color(241, 196, 15));

        buttonsPanel.add(borrowBtn);
        buttonsPanel.add(requestBtn);
        buttonsPanel.add(recommendBtn);

        borrowBtn.addActionListener(e -> handleBorrow(table));
        requestBtn.addActionListener(e -> handleRequest(table));
        recommendBtn.addActionListener(e -> handleRecommend(table));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        0, getHeight(), new Color(58, 213, 176)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 10));
        backgroundPanel.add(title, BorderLayout.NORTH);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(buttonsPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Library Books");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { button.setBackground(bgColor.darker()); }
            @Override
            public void mouseExited(MouseEvent e) { button.setBackground(bgColor); }
        });
        return button;
    }

    private static void handleBorrow(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(null, "Select a book first!"); return; }

        int bookId = Integer.parseInt(table.getValueAt(row, 0).toString());
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(14);

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Borrowings (account_id, book_id, borrow_date, due_date) VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, 1); 
            ps.setInt(2, bookId);
            ps.setDate(3, Date.valueOf(today));
            ps.setDate(4, Date.valueOf(dueDate));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "📖 Book borrowed successfully!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "❌ Cannot borrow book: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void handleRequest(JTable table) {
        JOptionPane.showMessageDialog(null, "Request feature placeholder");
    }

    private static void handleRecommend(JTable table) {
        JOptionPane.showMessageDialog(null, "Recommend feature placeholder");
    }

    @Override
    public int compareTo(Book other) { return this.id.compareTo(other.id); }

    public static void main(String[] args) { SwingUtilities.invokeLater(Book::fetchBooksFromDB_GUI); }
}
