package report;

public class ExtendedBorrowing extends Borrowing {
    private String specialApproval;

    public ExtendedBorrowing(String id, String generatedBy, String generatedDate,
                             String bookTitle, String borrowerId, String dateBorrowed,
                             String dueDate, String specialApproval) {
        super(id, generatedBy, generatedDate, bookTitle, borrowerId, dateBorrowed, dueDate);
        this.specialApproval = specialApproval;
    }

    public String getSpecialApproval() {
        System.out.println("getSpecialApproval() called, returning: " + specialApproval);
        return specialApproval;
    }

    public void setSpecialApproval(String specialApproval) {
        System.out.println("setSpecialApproval() called, setting to: " + specialApproval);
        this.specialApproval = specialApproval;
    }

    public void renew() {
        System.out.println("Renewing borrowing record...");
        setTimesRenewed(getTimesRenewed() + 1);
        System.out.println("Times renewed now: " + getTimesRenewed());
    }

    @Override
    public void displayReport() {
        super.displayReport();
        System.out.println("Special Approval: " + specialApproval);
    }
}
