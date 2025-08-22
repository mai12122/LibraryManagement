package book;

import java.awt.*;
import java.awt.event.*;
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

        public String getId() { 
            return id; 
        }
        protected void setId(String id) {
            if (id != null && !id.trim().isEmpty()) this.id = id;
            else throw new IllegalArgumentException("ID cannot be empty.");
        }

        public String getTitle() { 
            return title; 
        }
        protected void setTitle(String title) {
            if (title != null && !title.trim().isEmpty()) this.title = title;
            else throw new IllegalArgumentException("Title cannot be empty.");
        }

        public String getAuthor() { 
            return author; 
        }
        protected void setAuthor(String author) {
            if (author != null && !author.trim().isEmpty()) this.author = author;
            else throw new IllegalArgumentException("Author cannot be empty.");
        }

        public String getGenre() { 
            return genre; 
        }
        protected void setGenre(String genre) {
            if (genre != null && !genre.trim().isEmpty()) this.genre = genre;
            else throw new IllegalArgumentException("Genre cannot be empty.");
        }

        public String getAvailabilityStatus() { 
            return availabilityStatus; 
        }
        protected void setAvailabilityStatus(String availabilityStatus) {
            if (availabilityStatus != null &&
                (availabilityStatus.equalsIgnoreCase("Available") || availabilityStatus.equalsIgnoreCase("Unavailable")))
                this.availabilityStatus = availabilityStatus;
            else throw new IllegalArgumentException("Status must be 'Available' or 'Unavailable'.");
        }

        public String getIsbn() { 
            return isbn; 
        }
        protected void setIsbn(String isbn) {
            if (isbn != null && isbn.matches("\\d{10}|\\d{13}")) this.isbn = isbn;
            else throw new IllegalArgumentException("Invalid ISBN format.");
        }

        public String getPublishedYear() { 
            return publishedYear; 
        }
        protected void setPublishedYear(String publishedYear) {
            if (publishedYear != null && publishedYear.matches("\\d{4}")) this.publishedYear = publishedYear;
            else throw new IllegalArgumentException("Invalid Published Year.");
        }

        public String getPublishedCountry() { 
            return publishedCountry; 
        }
        protected void setPublishedCountry(String publishedCountry) {
            if (publishedCountry != null && !publishedCountry.trim().isEmpty()) this.publishedCountry = publishedCountry;
            else throw new IllegalArgumentException("Published Country cannot be empty.");
        }

        public String getLanguage() { 
            return language; 
        }
        protected void setLanguage(String language) {
            if (language != null && !language.trim().isEmpty()) this.language = language;
            else throw new IllegalArgumentException("Language cannot be empty.");
        }

        public String getCategory() { 
            return category; 
        }
        protected void setCategory(String category) {
            if (category != null && !category.trim().isEmpty()) this.category = category;
            else throw new IllegalArgumentException("Category cannot be empty.");
        }

    public static void fetchBooksFromDB_GUI() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID", "Title", "Author", "Genre", "ISBN",
            "Year", "Country", "Language", "Category", "Status"
        });

        String query = "SELECT id, title, author, genre, isbn, published_year, " +
                       "published_country, lang_name, b_categories, is_available FROM Books";

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
        if (row == -1) { 
            JOptionPane.showMessageDialog(null, "Select a book first!"); 
            return; 
        }

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
    public int compareTo(Book other) { 
        return this.id.compareTo(other.id); 
    }

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(Book::fetchBooksFromDB_GUI); 
    }
}
