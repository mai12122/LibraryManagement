package book;

public class BookRecommendation extends Book {
    private String recommendedBy;
    private String recommendationDetails;

    public BookRecommendation(
            String id, String title, String author, String genre,
            String availabilityStatus, String isbnNumber, String locationOnShelf,
            String recommendedBy, String recommendationDetails) {

        super(id, title, author, genre, availabilityStatus, isbnNumber, locationOnShelf);
        setRecommendedBy(recommendedBy);
        setRecommendationDetails(recommendationDetails);
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = (recommendedBy == null || recommendedBy.trim().isEmpty())
                ? "Unknown"
                : recommendedBy.trim();
    }

    public String getRecommendationDetails() {
        return recommendationDetails;
    }

    public void setRecommendationDetails(String recommendationDetails) {
        this.recommendationDetails = (recommendationDetails == null || recommendationDetails.trim().isEmpty())
                ? "No details provided"
                : recommendationDetails.trim();
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
        if (obj == null || getClass() != obj.getClass()) return false;

        BookRecommendation other = (BookRecommendation) obj;

        if (getId() == null) {
            if (other.getId() != null) return false;
        } else if (!getId().equals(other.getId())) return false;

        if (recommendedBy == null) {
            if (other.recommendedBy != null) return false;
        } else if (!recommendedBy.equals(other.recommendedBy)) return false;

        if (recommendationDetails == null) {
            return other.recommendationDetails == null;
        } else return recommendationDetails.equals(other.recommendationDetails);
    }
}
