package book;

public abstract class Book implements Comparable<Book> {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;
    private String isbnNumber;
    private String locationOnShelf;
    private static int totalBooks = 0;

    protected Book(String id, String title, String author, String genre, String status, String isbn, String location) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setAvailabilityStatus(status);
        setIsbnNumber(isbn);
        setLocationOnShelf(location);
        synchronized (Book.class) {
            totalBooks++;
        }
    }

    protected Book(String id, String title, String author) {
        this(id, title, author, "Unknown", "Available", "N/A", "Not Assigned");
    }

    protected void setId(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.id = var1.trim();
        }
    }

    protected void setTitle(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.title = var1.trim();
        }
    }

    protected void setAuthor(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.author = var1.trim();
        }
    }

    protected void setGenre(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.genre = var1.trim();
        } else {
            this.genre = "Unknown";
        }
    }

    protected void setAvailabilityStatus(String var1) {
        if (var1 != null && (var1.equalsIgnoreCase("Available") || var1.equalsIgnoreCase("Borrowed"))) {
            this.availabilityStatus = var1;
        } else {
            this.availabilityStatus = "Available";
        }
    }

    protected void setIsbnNumber(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.isbnNumber = var1.trim();
        } else {
            this.isbnNumber = "N/A";
        }
    }

    protected void setLocationOnShelf(String var1) {
        if (var1 != null && !var1.trim().isEmpty()) {
            this.locationOnShelf = var1.trim();
        } else {
            this.locationOnShelf = "Not Assigned";
        }
    }

    public String getId() {
        return id != null ? id : "Unknown";
    }

    public String getTitle() {
        return title != null ? title : "Untitled";
    }

    public String getAuthor() {
        return author != null ? author : "Unknown Author";
    }

    public String getGenre() {
        return genre != null ? genre : "Unknown Genre";
    }

    public String getAvailabilityStatus() {
        return availabilityStatus != null ? availabilityStatus : "Available";
    }

    public String getIsbnNumber() {
        return isbnNumber != null ? isbnNumber : "N/A";
    }

    public String getLocationOnShelf() {
        return locationOnShelf != null ? locationOnShelf : "Not Assigned";
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book other = (Book) obj;

        if (id == null) {
            return other.id == null;
        }
        return id.equals(other.id);
    }

    @Override
    public int compareTo(Book other) {
        if (other == null) return 1;
        if (this.id == null && other.id == null) return 0;
        if (this.id == null) return -1;
        if (other.id == null) return 1;
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Book[ID=" + getId() +
               ", Title=" + getTitle() +
               ", Author=" + getAuthor() +
               ", Genre=" + getGenre() +
               ", Status=" + getAvailabilityStatus() +
               ", ISBN=" + getIsbnNumber() +
               ", Location=" + getLocationOnShelf() + "]";
    }
}
