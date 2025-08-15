package user;

public class Lecturer extends users {
    private String department;
    private String recommendation;

    public Lecturer(String id, String name, String email, String password, String department, String recommendation) {
    super(id, name, email, password);
    setDepartment(department);
    setRecommendation(recommendation);
    accountList.add(this);
}

    public void displayLecturer() {
        System.out.println("Lecturer Information:");
        System.out.println("Department: " + department);
        if (recommendation != null && !recommendation.isEmpty()) {
            System.out.println("Recommendation: " + recommendation);
        }
    }

    public String getDepartment() {
        if (department == null || department.equals("Unknown")) {
            System.out.println("Warning: Department is not set.");
        }
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            System.out.println("Invalid department name. Setting to 'Unknown'.");
            this.department = "Unknown";
        } else {
            this.department = department;
        }
    }

    public String getRecommendation() {
        if (recommendation == null) {
            System.out.println("Warning: Recommendation is not set.");
        }
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        if (recommendation == null || recommendation.trim().isEmpty()) {
            System.out.println("Recommendation cannot be empty. Setting to null.");
            this.recommendation = null;
        } else {
            this.recommendation = recommendation;
        }
    }
}
