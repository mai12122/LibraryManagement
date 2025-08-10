
package report;
import java.time.LocalDate;

public class recommendationReport extends Report {
    private String recommendedBy;
    private String recommendationDetails;
    private LocalDate recommendationDate;

    public recommendationReport(String id, String generatedBy, String generatedDate,
                                String recommendedBy, String recommendationDetails, LocalDate recommendationDate) {
        super(id, generatedBy, generatedDate);
        setRecommendedBy(recommendedBy);
        setRecommendationDetails(recommendationDetails);
        setRecommendationDate(recommendationDate);
    }

    public String getRecommendedBy() {
        if (recommendedBy == null || recommendedBy.trim().isEmpty()) {
            return "Unknown Recommender";
        }
        return recommendedBy;
    }

   public void setRecommendedBy(String recommendedBy) {
    if (recommendedBy != null && !recommendedBy.trim().isEmpty()) {
        this.recommendedBy = recommendedBy;
    } else {
        System.out.println("Invalid recommendedBy. It was not set.");
    }
}
    public String getRecommendationDetails() {
        if (recommendationDetails == null || recommendationDetails.trim().isEmpty()) {
            return "No details provided";
        }
        return recommendationDetails;
    }

    public void setRecommendationDetails(String recommendationDetails) {
        if (recommendationDetails != null && !recommendationDetails.trim().isEmpty()) {
            this.recommendationDetails = recommendationDetails;
        } else {
            System.out.println("Invalid recommendationDetails. It was not set.");
        }
    }

    public LocalDate getRecommendationDate() {
        if (recommendationDate == null) {
            System.out.println("Warning: Recommendation date not set.");
        }
        return recommendationDate;
    }

    public void setRecommendationDate(LocalDate recommendationDate) {
        if (recommendationDate != null && !recommendationDate.isAfter(LocalDate.now())) {
            this.recommendationDate = recommendationDate;
        } else {
            System.out.println("Invalid recommendation date. It was not set.");
        }
    }
    
    
    @Override
    public String calculateSummary() {
        long daysSinceRecommendation = (recommendationDate == null) ? -1 :
                java.time.temporal.ChronoUnit.DAYS.between(recommendationDate, LocalDate.now());

        String recAge = (recommendationDate == null) ? "Date not set" :
                        daysSinceRecommendation + " days ago";

        return "Recommendation by '" + getRecommendedBy() + "' made " + recAge + ". Details: " + getRecommendationDetails();
    }

    @Override
    public String toString() {
        return "recommendationReport{" +
                "id='" + getId() + '\'' +
                ", generatedBy='" + getGeneratedBy() + '\'' +
                ", generatedDate='" + getGeneratedDate() + '\'' +
                ", recommendedBy='" + recommendedBy + '\'' +
                ", recommendationDetails='" + recommendationDetails + '\'' +
                ", recommendationDate=" + recommendationDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof recommendationReport)) return false;
        recommendationReport other = (recommendationReport) obj;

        boolean dateEquals = (recommendationDate == null) ? other.recommendationDate == null
                : recommendationDate.equals(other.recommendationDate);

        return getRecommendedBy().equals(other.getRecommendedBy()) &&
               getRecommendationDetails().equals(other.getRecommendationDetails()) &&
               dateEquals;
    }

    public static String getReportType() {
        return "Recommendation Report";
    }
}

