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
        return specialApproval;
    }

    public void setSpecialApproval(String specialApproval) {
        this.specialApproval = specialApproval;
    }

    public void renew() {
        setTimesRenewed(getTimesRenewed() + 1);
    }

    @Override
    public void displayReport() {
        super.displayReport();
        System.out.println("Special Approval: " + specialApproval);
    }

    @Override
    public String exportReport() {
        return super.exportReport() + "\nSpecial Approval: " + specialApproval;
    }

    @Override
    public boolean isValid() {
        return super.isValid()
            && specialApproval != null && !specialApproval.isEmpty();
    }
}
