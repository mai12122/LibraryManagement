package report;

public abstract class Report {
    private String id;
    private String generated_by;
    private String generated_date;

    public Report(String id, String generated_by, String generated_date) {
        this.id = id;
        this.generated_by = generated_by;
        this.generated_date = generated_date;
    }

    public String getId() {
        System.out.println("getId() called, returning: " + id);
        return id;
    }

    public String getGeneratedBy() {
        System.out.println("getGeneratedBy() called, returning: " + generated_by);
        return generated_by;
    }

    public String getGeneratedDate() {
        System.out.println("getGeneratedDate() called, returning: " + generated_date);
        return generated_date;
    }

    protected void setId(String id) {
        System.out.println("setId() called, setting to: " + id);
        this.id = id;
    }

    protected void setGeneratedBy(String generated_by) {
        System.out.println("setGeneratedBy() called, setting to: " + generated_by);
        this.generated_by = generated_by;
    }

    protected void setGeneratedDate(String generated_date) {
        System.out.println("setGeneratedDate() called, setting to: " + generated_date);
        this.generated_date = generated_date;
    }

    public abstract void displayReport();
}
