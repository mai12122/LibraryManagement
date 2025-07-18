package user;

public class Lecturer extends users {
    private String department;
    private String recommendation;

    public Lecturer(String id, String name, String email, String password, String department) {
        super(id, name, email, password);
        System.out.println(" Called with department: " + department);
        this.department = department;
    }

    public void displayLecturer() {
        System.out.println("Displaying Lecturer info:");
        displayInfo();
        System.out.println("Department: " + department);
        if (recommendation != null && !recommendation.isEmpty()) {
            System.out.println("Recommendation: " + recommendation);
        }
    }

    public String getDepartment() {
        System.out.println("getDepartment() called, returning: " + department);
        return department;
    }

    public void setDepartment(String department) {
        System.out.println("setDepartment() called, setting to: " + department);
        this.department = department;
    }

    public String getRecommendation() {
        System.out.println("getRecommendation() called, returning: " + recommendation);
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        System.out.println("setRecommendation() called, setting to: " + recommendation);
        this.recommendation = recommendation;
    }
}
