package book;

public class BookRequest extends Book {
    private String requestId;
    private String status;
    private static int totalRequests = 0;

    protected BookRequest(String requestId, String title, String author, String genre, String status) {
        super(title + "-" + requestId, title, author, genre, status, "N/A", "Not Assigned");
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

    public String getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public static int getTotalRequests() {
        return totalRequests;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookRequest)) return false;
        BookRequest other = (BookRequest) obj;
        if (requestId == null) {
            return other.requestId == null;
        }
        return requestId.equals(other.requestId);
    }

    @Override
    public String toString() {
        return "Bookrequest{" +
                "requestId='" + requestId + '\'' +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

