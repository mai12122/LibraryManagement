import java.util.HashSet;
import java.util.Set;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;
    private String isbnNumber;
    private String locationOnShelf;

    private static int totalBooks = 0;

    public Book(String id, String title, String author, String genre,
                String availabilityStatus, String isbnNumber, String locationOnShelf) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
        this.isbnNumber = isbnNumber;
        this.locationOnShelf = locationOnShelf;
        totalBooks++;
    }

    public Book(String id, String title, String author) {
        this(id, title, author, "Unknown", "Available", "N/A", "Not Assigned");
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public void displayInfo() {
        System.out.println("Book ID           : " + id);
        System.out.println("Title             : " + title);
        System.out.println("Author            : " + author);
        System.out.println("Genre             : " + genre);
        System.out.println("Status            : " + availabilityStatus);
        System.out.println("ISBN              : " + isbnNumber);
        System.out.println("Shelf Location    : " + locationOnShelf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static void main(String[] args) {
        Set<Book> books = new HashSet<Book>();

        Book b1 = new Book("B001", "The Alchemist", "Paulo Coelho", "Fiction", "Available", "123456789", "Shelf A3");
        Book b2 = new Book("B002", "Clean Code", "Robert C. Martin", "Programming", "Borrowed", "987654321", "Shelf B1");
        Book b3 = new Book("B003", "Unknown Book", "Unknown Author");

        books.add(b1);
        books.add(b2);
        books.add(b3);

        for (Book book : books) {
            book.displayInfo();
            System.out.println("------------------------");
        }

        System.out.println("Total Books: " + Book.getTotalBooks());
    }
}
